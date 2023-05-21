package com.source.plusutil.utilInfo;

import com.source.plusutil.utilInfo.dto.UtilInfoDto;
import com.source.plusutil.utilInfo.dto.UtilInfoInsertRequestDto;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.transaction.Transactional;

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
        UtilInfoDto utilInfoDto = utilInfoService.insertUtilInfo(utilInfoInsertRequestDto1);
        System.out.println(utilInfoDto);

        MatcherAssert.assertThat("utilInfoDto is null Error", utilInfoDto, is(not(nullValue())));
        MatcherAssert.assertThat("utilInfoDto Name is Not Equals Error", utilInfoDto.getUtilName(), is(utilInfoInsertRequestDto1.getUtilName()));
        MatcherAssert.assertThat("utilInfoDto Description is Not Equals Error", utilInfoDto.getUtilDescription(), is(utilInfoInsertRequestDto1.getUtilDescription()));

    }

}
