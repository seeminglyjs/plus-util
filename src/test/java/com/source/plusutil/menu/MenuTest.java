package com.source.plusutil.menu;

import com.source.plusutil.menu.dto.HeadDto;
import com.source.plusutil.menu.dto.NavInfoDto;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;


@SpringBootTest
public class MenuTest {

    @Autowired
    QueryDSLMenuRepository queryDSLMenuRepository;

    @Test
    public void getHeadListAll(){
        List<HeadDto> headDtoList = queryDSLMenuRepository.findAllHeadMenus();
        MatcherAssert.assertThat("headDtoList is empty", headDtoList, is(not(nullValue())));
    }

    @Test
    public void getAllTotalMenuList(){
        List<NavInfoDto> navInfoList = queryDSLMenuRepository.findAllMenuList();
        MatcherAssert.assertThat("navInfoList is empty", navInfoList, is(not(nullValue())));
    }
}
