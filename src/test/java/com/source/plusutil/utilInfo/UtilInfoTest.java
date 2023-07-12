package com.source.plusutil.utilInfo;

import com.source.plusutil.utilInfo.dto.*;
import com.source.plusutil.utilInfo.dto.entity.UtilInfoDto;
import com.source.plusutil.utilInfo.dto.entity.UtilViewsDto;
import com.source.plusutil.utilInfo.repository.UtilInfoRepository;
import com.source.plusutil.utilInfo.repository.UtilViewRepository;
import com.source.plusutil.utils.etc.DateUtil;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.mock.web.MockHttpServletRequest;


import javax.transaction.Transactional;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class UtilInfoTest {

    @Autowired
    UtilInfoSimpleService utilInfoSimpleService;
    @Autowired
    UtilInfoService utilInfoService;
    @Autowired
    UtilViewRepository utilViewRepository;
    @Autowired
    UtilInfoRepository utilInfoRepository;

    public UtilInfoDto makeUtilInfoDto(){
        UtilInfoDto utilInfoDto = UtilInfoDto.builder()
                .utilName("Test" + UUID.randomUUID().toString().split("-")[2])
                .utilDescription("utilDescription" + UUID.randomUUID().toString().split("-")[2])
                .utilLikes(0)
                .utilViews(0)
                .utilEnrollDate("20230614")
                .urlPath("/test/test123/test")
                .category("test")
                .subject("test")
                .build();
        UtilInfoDto utilInfoDtoSave =  utilInfoRepository.save(utilInfoDto);
        MatcherAssert.assertThat("==== getUtilNo is null  !!!!",utilInfoDtoSave.getUtilNo(), is(not(nullValue())));
        MatcherAssert.assertThat("==== getUtilName is null  !!!!",utilInfoDtoSave.getUtilName(), is(not(nullValue())));
        MatcherAssert.assertThat("==== Category is null  !!!!",utilInfoDtoSave.getCategory(), is(not(nullValue())));
        return utilInfoDtoSave;

    }

    @Test
    @Transactional //클래스보다 메소드 단위의 Transactional 보다 우선순위가 높다
    public void utilInfoFindAllTest() {
        Page<UtilInfoDto> utilList1 = utilInfoSimpleService.getUtilList(1);
        MatcherAssert.assertThat("utilList1.size() is not 1", utilList1.getSize(), is(1));
        System.out.println(utilList1.get());
    }

    /*
        유틸 조회정보 테스트
     */
    @Test
    @Transactional //클래스보다 메소드 단위의 Transactional 보다 우선순위가 높다
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
//        utilViewsDto = utilViewRepository.findByUtilNoAndViewIpAndViewDate(utilNo, ip, dateInfo);
//        System.out.println("utilViewsDto is ->>>>>>" + utilViewsDto);
//        MatcherAssert.assertThat("utilInfoDto is null", utilViewsDto, is(not(nullValue())));
//        MatcherAssert.assertThat("utilInfoDto utilNo is not 1", utilViewsDto.getUtilNo(), is(1L));
        System.out.println("======= 존재하는 정보 조회 테스트 [종료] =======\n ");
    }

    /*
     조회수 등록 실패건에 대한 테스트 작업
     */
    @Test
    public void addUtilView() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRemoteAddr("111.11.11.111");

        System.out.println("======= addUtilView 롤백 테스트 [시작] =======\n ");
        //해당 건은 실패 할 수 밖에 없다.
        assertThrows(RuntimeException.class, () -> {
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


    /*
        유틸 정보를 단순 등록 하는 것을 호출한다.
        class : utilInfoSimpleService
     */
    @Test
    @Transactional //클래스보다 메소드 단위의 Transactional 보다 우선순위가 높다
    public void utilInfoInsertTest() throws Exception {
        UtilInfoInsertRequestDto utilInfoInsertRequestDto1 = UtilInfoInsertRequestDto.builder()
                .utilNo(-1)
                .utilName("utilInfoInsertTest")
                .utilDescription("it is test1 util")
                .utilLikes(0)
                .utilViews(0)
                .urlPath("/test/test1")
                .category("test")
                .subject("test")
                .build();
        UtilInfoDto utilInfoDto = utilInfoSimpleService.addUtilInfo(utilInfoInsertRequestDto1);
        System.out.println(utilInfoDto);

        MatcherAssert.assertThat("utilInfoDto is null Error", utilInfoDto, is(not(nullValue())));
        MatcherAssert.assertThat("utilInfoDto Name is Not Equals Error", utilInfoDto.getUtilName(), is(utilInfoInsertRequestDto1.getUtilName()));
        MatcherAssert.assertThat("utilInfoDto Description is Not Equals Error", utilInfoDto.getUtilDescription(), is(utilInfoInsertRequestDto1.getUtilDescription()));
    }


    /*
        유틸 정보 등록을 테스트 한다.
        class : UtilInfoService
     */
    @Test
    @Transactional //클래스보다 메소드 단위의 Transactional 보다 우선순위가 높다
    public void enrollUtilInfoTest() {
        UtilInfoInsertRequestDto utilInfoInsertRequestDto1 = UtilInfoInsertRequestDto.builder()
                .utilNo(-1)
                .utilName("enrollUtilInfoTest")
                .utilDescription("it is test2 util")
                .urlPath("/test/test2")
                .category("test")
                .subject("test")
                .utilLikes(0)
                .utilViews(0)
                .build();
        UtilInfoInsertResponseDto utilInfoInsertResponseDto = utilInfoService.enrollUtilInfo(utilInfoInsertRequestDto1);
        MatcherAssert.assertThat("utilInfoInsertResponseDto is null Error", utilInfoInsertResponseDto, is(not(nullValue())));
    }

    @Test
    @Transactional //클래스보다 메소드 단위의 Transactional 보다 우선순위가 높다
    public void getUtilInfoTest() {
        String utilName = "";
        List<UtilInfoDto> utilInfoDtoList = utilInfoSimpleService.getUtilInfoList(utilName);
        MatcherAssert.assertThat("utilInfoDtoList is null Error", utilInfoDtoList, is(not(nullValue()))); //단순 동작여부 체크
        //리스트 null 체크

        //
    }

    @Test
    @Transactional
    public void getUtilInfoDetailTest(){
        UtilInfoDto utilInfoDto =  makeUtilInfoDto();
        if(utilInfoDto == null) return;
        else {
            UtilInfoDto utilInfoDtoDetail = utilInfoService.getUtilInfoDetail(utilInfoDto.getUtilNo());
            MatcherAssert.assertThat("utilInfoDtoDetail is Null", utilInfoDtoDetail, is(not(nullValue())));
            MatcherAssert.assertThat("utilInfoDtoDetail getUtilNo is Null", utilInfoDtoDetail.getUtilNo(), is(not(nullValue())));
        }
    }

    @Test
    @Transactional
    public void getTopList(){
        UtilInfoGetResponseDto utilInfoGetResponseDto = null;
        utilInfoGetResponseDto = utilInfoService.getUtilTopList();
        MatcherAssert.assertThat("utilInfoGetResponseDto is Null", utilInfoGetResponseDto, is(not(nullValue())));
        System.out.println(utilInfoGetResponseDto.getUtilInfoDtoList().size());
        MatcherAssert.assertThat("utilInfoGetResponseDto.getUtilInfoDtoList is  < 1", utilInfoGetResponseDto.getUtilInfoDtoList().size(), is(greaterThan(0)));
    }


}
