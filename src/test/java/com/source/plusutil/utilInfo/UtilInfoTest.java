package com.source.plusutil.utilInfo;

import com.source.plusutil.utilInfo.dto.UtilInfoDto;
import com.source.plusutil.utilInfo.dto.UtilInfoInsertRequestDto;
import com.source.plusutil.utilInfo.dto.UtilViewRequestDto;
import com.source.plusutil.utilInfo.dto.UtilViewsDto;
import com.source.plusutil.utilInfo.repository.UtilViewRepository;
import com.source.plusutil.utils.etc.DateUtil;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.Rollback;


import javax.transaction.Transactional;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class UtilInfoTest {

    @Autowired
    UtilInfoSimpleService utilInfoSimpleService;
    @Autowired
    UtilInfoSimpleService utilInfoService;
    @Autowired
    UtilViewRepository utilViewRepository;

    @Test
    @Transactional //클래스보다 메소드 단위의 Transactional 우선순위가 높다
    public void utilInfoInsertTest() throws Exception {
        UtilInfoInsertRequestDto utilInfoInsertRequestDto1 = UtilInfoInsertRequestDto.builder()
                .utilName("test2")
                .utilDescription("it is test2 util")
                .utilLikes(0)
                .utilViews(0)
                .build();
        UtilInfoDto utilInfoDto = utilInfoService.addUtilInfo(utilInfoInsertRequestDto1);
        System.out.println(utilInfoDto);

        MatcherAssert.assertThat("utilInfoDto is null Error", utilInfoDto, is(not(nullValue())));
        MatcherAssert.assertThat("utilInfoDto Name is Not Equals Error", utilInfoDto.getUtilName(), is(utilInfoInsertRequestDto1.getUtilName()));
        MatcherAssert.assertThat("utilInfoDto Description is Not Equals Error", utilInfoDto.getUtilDescription(), is(utilInfoInsertRequestDto1.getUtilDescription()));
    }

    @Test
    @Transactional //클래스보다 메소드 단위의 Transactional 우선순위가 높다
    public void utilInfoFindAllTest() {
        Page<UtilInfoDto> utilList1 = utilInfoService.getUtilList(1);
        MatcherAssert.assertThat("utilList1.size() is not 1", utilList1.getSize(), is(1));
        System.out.println(utilList1.get());
    }

    @Test
    @Transactional //클래스보다 메소드 단위의 Transactional 우선순위가 높다
    public void getUtilInfoViewHistToday() {
        System.out.println("======= 존재하지 않는 정보 조회 테스트 [시작] =======\n ");
        long utilNo = 99999;
        String ip = "111.11.11.111";
        String dateInfo = "20230529";//조회시점의 날짜 정보 저장 찰나의 순간 날짜 변경을 예방하기 위해 변수화

        UtilViewsDto utilViewsDto = utilViewRepository.findByUtilNoAndViewIpAndViewDate(utilNo, ip, dateInfo);
        System.out.println("utilViewsDto is ->>>>>>" + utilViewsDto);
        MatcherAssert.assertThat("utilInfoDto is Not null", utilViewsDto, is(nullValue()));
        System.out.println("======= 존재하지 않는 정보 조회 테스트 [종료] =======\n ");

        System.out.println("======= 존재하는 정보 조회 테스트 [시작] =======\n ");
        utilNo = 1;
        utilViewsDto = utilViewRepository.findByUtilNoAndViewIpAndViewDate(utilNo, ip, dateInfo);
        System.out.println("utilViewsDto is ->>>>>>" + utilViewsDto);
        MatcherAssert.assertThat("utilInfoDto is null", utilViewsDto, is(not(nullValue())));
        MatcherAssert.assertThat("utilInfoDto utilNo is not 1", utilViewsDto.getUtilNo(), is(1L));
        System.out.println("======= 존재하는 정보 조회 테스트 [종료] =======\n ");
    }

    @Test
    public void addUtilView(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("111.11.11.111");

        System.out.println("======= addUtilView 롤백 테스트 [시작] =======\n ");
        //해당 건은 실패 할 수 밖에 없다.
        assertThrows(RuntimeException.class, ()->{
            utilInfoSimpleService.addUtilView(request,
                    UtilViewRequestDto.builder()
                            .utilNo(999999)
                            .build());
        });

        UtilViewsDto utilViewsDto = utilViewRepository.findByUtilNoAndViewIpAndViewDate(999999, "111.11.11.111", DateUtil.getDateStryyyyMMdd());
        System.out.println("utilViewsDto is ->>>>>>" + utilViewsDto);
        MatcherAssert.assertThat("utilInfoDto is not null Transactional not working", utilViewsDto, is((nullValue())));
        System.out.println("======= addUtilView 롤백 테스트 [종료] =======\n ");
    }

    @Test
    public void utilInfoViewTest() {

    }

}
