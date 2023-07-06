package com.source.plusutil.notice;

import com.source.plusutil.enums.UserRolePlusEnum;
import com.source.plusutil.notice.dto.*;
import com.source.plusutil.utils.auth.AuthObjectUtil;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;


@SpringBootTest
public class NoticeTest {

    @Autowired
    NoticeService noticeService;

    @Autowired
    NoticeRepository noticeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    /*
       테스트 용도의 게시글을 만드는 메소드
     */
    public NoticeDto makeNoticeWrtieDto(){
        String randomTitle = "Title" + UUID.randomUUID().toString().split("-")[2];
        String randomContent = "Content" + UUID.randomUUID().toString().split("-")[2];
        NoticeWriteRequestDto noticeWriteRequestDto = new NoticeWriteRequestDto(randomTitle, randomContent, "notice",0);
        MatcherAssert.assertThat("==== Title is null  !!!!",noticeWriteRequestDto.getNoticeTitle(), is(not(nullValue())));
        MatcherAssert.assertThat("==== Content is null  !!!!",noticeWriteRequestDto.getNoticeContent(), is(not(nullValue())));
        MatcherAssert.assertThat("==== Category is null  !!!!",noticeWriteRequestDto.getCategory(), is(not(nullValue())));
        return NoticeDto.writeNotice(Objects.requireNonNull(noticeService.makeNoticeWriteDto("test123@123.123", noticeWriteRequestDto)));
    }

    @Test
    @Transactional //클래스보다 메소드 단위의 Transactional 우선순위가 높다
    public void noticeDetailTest(){
        System.out.println("========noticeDetailTest Start!!!============");
        NoticeDto writeNoticeDto = makeNoticeWrtieDto();
        entityManager.persist(writeNoticeDto);

        Long noticeNo = writeNoticeDto.getNoticeNo();
        System.out.println("noticeNo ->" + noticeNo);
        noticeRepository.save(writeNoticeDto);

        NoticeDetailDto noticeDetailDto = noticeService.getNoticeDetailInfo(null,noticeNo);
        MatcherAssert.assertThat("noticeDetailDto is null !!!!", is(not(nullValue())));
        System.out.println(noticeDetailDto);

        System.out.println("========noticeDetailTest Success!!!============");
        System.out.println();
        System.out.println();
        System.out.println();
    }



    @Test
    @Transactional //클래스보다 메소드 단위의 Transactional 우선순위가 높다
    public void noticeWriteTest(){
        System.out.println("========noticeWriteTest Start!!!============");

        NoticeDto writeNoticeDto = makeNoticeWrtieDto();
        entityManager.persist(writeNoticeDto);

        Long noticeNo = writeNoticeDto.getNoticeNo();
        System.out.println(noticeNo);
        noticeRepository.save(writeNoticeDto);

        Optional<NoticeDto> afterWriteNoticeDtoOp = noticeRepository.findById(noticeNo);

        MatcherAssert.assertThat("==== afterWriteNoticeDtoOp is null  !!!!",afterWriteNoticeDtoOp.isPresent(), is(true));
        System.out.println("========Save OK!!!============");
        NoticeDto afterWriteNoticeDto = afterWriteNoticeDtoOp.get();
        System.out.println("Save Notice info -> " + afterWriteNoticeDto.toString());
        if(Objects.equals(afterWriteNoticeDto.getNoticeNo(), noticeNo)){
            System.out.println("notice Save Ok noticeNo is -> " + noticeNo);
            noticeRepository.deleteById(noticeNo); //공지사항 번호 기준으로 해당 게시글 삭제
            Optional<NoticeDto> afterDeleteNoticeDtoOp = noticeRepository.findById(noticeNo);
            MatcherAssert.assertThat("==== afterDeleteNoticeDtoOp is still exist  !!!!",afterDeleteNoticeDtoOp.isPresent(), is(false));
            System.out.println("========Delete OK!!!============");
        }else{
            MatcherAssert.assertThat("==== noticeNo findById Fail  !!!!",noticeNo, is(true));
        }
        System.out.println("========noticeWriteTest Success!!!============");
        System.out.println();
        System.out.println();
        System.out.println();
    }


    @Test
    @Transactional //클래스보다 메소드 단위의 Transactional 우선순위가 높다 테스트가 붙은 transaction은 항상 Rolled back transaction for test 가 호출되어 롤백 된다..!!!!!
//    @Rollback(false) // rollback 되지 않도록 설정
    public void noticeDeleteByNoticeNo(){
        System.out.println("=========noticeDeleteByNoticeNo start =============");
        NoticeDto writeNoticeDto = makeNoticeWrtieDto();
        entityManager.persist(writeNoticeDto);

        Long noticeNo = writeNoticeDto.getNoticeNo();
        System.out.println(noticeNo);
        noticeRepository.save(writeNoticeDto);
        System.out.println("save noticeNo ->" + noticeNo);

        NoticeDeleteRequestDto noticeDeleteRequestDto = new NoticeDeleteRequestDto(noticeNo,0);

        NoticeDeleteResponseDto noticeDeleteResponseDto = noticeService.deleteNoticeInfo(noticeDeleteRequestDto);
        assert noticeDeleteResponseDto != null : "noticeDeleteResponseDto 는 Null 값이 될 수 없습니다. Test Case를 확인하세요. [TestName -> noticeDeleteByNoticeNo]";
        MatcherAssert.assertThat("==== noticeDeleteResponseDto is getDeleteOk -> It shouldn't return true . Because it is an unauthorized request. !!!!",noticeDeleteResponseDto.getDeleteOk(), is(false));

        System.out.println("====== 단순 DELETE noticeRepository 호출 테스트 ======");
        noticeRepository.deleteById(noticeNo);
        Optional<NoticeDto> afterDeleteNoticeDtoOp = noticeRepository.findById(noticeNo);
        MatcherAssert.assertThat("==== afterDeleteNoticeDtoOp is still exist  !!!!",afterDeleteNoticeDtoOp.isPresent(), is(false));
        System.out.println("========Delete OK!!!============");
        System.out.println("=========noticeDeleteByNoticeNo end =============");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    @Test
    @Transactional //클래스보다 메소드 단위의 Transactional 우선순위가 높다 테스트가 붙은 transaction은 항상 Rolled back transaction for test 가 호출되어 롤백 된다..!!!!!
    @WithMockUser(username = "test", roles = "ADMIN") //임의로 어드민 계정 만들어서 테스트 하기
    public void noticeUpdateByNoticeNo(){
        System.out.println("=========noticeUpdateByNoticeNo start =============");
        NoticeDto writeNoticeDto = makeNoticeWrtieDto();
        String title = writeNoticeDto.getTitle();
        entityManager.persist(writeNoticeDto);

        Long noticeNo = writeNoticeDto.getNoticeNo();
        System.out.println(noticeNo);
        noticeRepository.save(writeNoticeDto);
        System.out.println("save noticeNo ->" + noticeNo);

        NoticeUpdateRequestDto noticeUpdateRequestDto = new NoticeUpdateRequestDto(noticeNo,"updateTitle99999", "UpdateContent", "notice");
        System.out.println("noticeService.updateNoticeInfo Call !!!");
        noticeService.updateNoticeInfo(noticeUpdateRequestDto);

        Optional<NoticeDto> afterUpdateNoticeDtoOp = noticeRepository.findById(noticeNo);
        assert afterUpdateNoticeDtoOp.isPresent(): "afterUpdateNoticeDtoOp is Null!!!!!!!!!";
        NoticeDto noticeDto = afterUpdateNoticeDtoOp.get();
        System.out.println("noticeDto -> " + noticeDto.toString());
        System.out.println("Before title -> " + title);
        MatcherAssert.assertThat("noticeService updateNoticeInfo 시도했지만 변경되지 않았습니다. ",noticeDto.getTitle(), is(not(title)));

        System.out.println("=========noticeUpdateByNoticeNo Success =============");
        System.out.println();
        System.out.println();
        System.out.println();
    }

}
