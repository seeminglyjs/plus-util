package com.source.plusutil.menu;

import com.source.plusutil.menu.dto.HeadDto;
import com.source.plusutil.menu.dto.NavDto;
import com.source.plusutil.menu.dto.NavInfoDto;

import java.util.List;

public interface QueryDSLMenuRepository {

    List<NavDto> findAllNavList();
    List<HeadDto> findAllHeadMenus();
    List<NavInfoDto> findAllMenuList();

}
