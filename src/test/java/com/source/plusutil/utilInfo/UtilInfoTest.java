package com.source.plusutil.utilInfo;

import com.source.plusutil.utilInfo.dto.UtilInfoDto;
import com.source.plusutil.utilInfo.dto.UtilInfoInsertRequestDto;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;


import javax.transaction.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

@SpringBootTest
public class UtilInfoTest {

    @Autowired
    UtilInfoService utilInfoService;

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
    @Transactional
    public void utilInfoFindAllTest(){
        Page<UtilInfoDto> utilList1 = utilInfoService.getUtilList(1);
        MatcherAssert.assertThat("utilList1.size() is not 1", utilList1.getSize(), is(1));
        System.out.println(utilList1.get());
    }
}
