package com.source.plusutil.notice;

import com.source.plusutil.dto.notice.NoticeDto;
import com.source.plusutil.dto.notice.NoticeWriteDto;
import com.source.plusutil.dto.notice.NoticeWriteRequestDto;
import com.source.plusutil.repository.noticeRepository.NoticeRepository;
import com.source.plusutil.service.noticeService.NoticeServiceImpl;
import com.source.plusutil.utils.etc.DateUtil;
import com.source.plusutil.utils.html.HtmlUtil;
import com.source.plusutil.utils.protect.XSSUtils;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;


@SpringBootTest
public class NoticeTest {

    @Autowired
    NoticeServiceImpl noticeService;

    @Autowired
    NoticeRepository noticeRepository;


//    @Test
    public void noticeWriteTest(){
        String randomTitle = "Title" + UUID.randomUUID().toString().split("-")[2];
        String randomContent = "Content" + UUID.randomUUID().toString().split("-")[2];
        NoticeWriteRequestDto noticeWriteRequestDto = new NoticeWriteRequestDto(randomTitle, randomContent, "notice",0);
        MatcherAssert.assertThat("==== Title is null  !!!!",noticeWriteRequestDto.getNoticeTitle(), is(not(nullValue())));
        MatcherAssert.assertThat("==== Content is null  !!!!",noticeWriteRequestDto.getNoticeContent(), is(not(nullValue())));
        MatcherAssert.assertThat("==== Category is null  !!!!",noticeWriteRequestDto.getCategory(), is(not(nullValue())));

        NoticeDto writeNoticeDto = NoticeDto.writeNotice(noticeService.makeNoticeWriteDto("test123@123.123",noticeWriteRequestDto));


        Long noticeNo = writeNoticeDto.getNoticeNo();
        System.out.println(noticeNo);
        noticeRepository.save(writeNoticeDto);

        Optional<NoticeDto> afterWriteNoticeDtoOp = noticeRepository.findById(noticeNo);

        MatcherAssert.assertThat("==== afterWriteNoticeDtoOp is null  !!!!",afterWriteNoticeDtoOp.isPresent(), is(true));
        NoticeDto afterWriteNoticeDto = afterWriteNoticeDtoOp.get();
        if(Objects.equals(afterWriteNoticeDto.getNoticeNo(), noticeNo)){
            System.out.println("notice Save Ok noticeNo is -> " + noticeNo);
            noticeRepository.deleteById(noticeNo); //공지사항 번호 기준으로 해당 게시글 삭제
            Optional<NoticeDto> afterDeleteNoticeDtoOp = noticeRepository.findById(noticeNo);
            MatcherAssert.assertThat("==== afterDeleteNoticeDtoOp is still exist  !!!!",afterDeleteNoticeDtoOp.isPresent(), is(false));
        }else{
            MatcherAssert.assertThat("==== noticeNo findById Fail  !!!!",noticeNo, is(true));
        }
    }







    @Test
    public void noticeDeleteByNoticeNo(){

    }

}
