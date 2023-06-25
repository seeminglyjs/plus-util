package com.source.plusutil.menu.repository;

import com.source.plusutil.menu.dto.HeadDto;
import com.source.plusutil.menu.dto.MenuDto;
import com.source.plusutil.menu.dto.NavDto;
import com.source.plusutil.menu.dto.NavInfoDto;

import java.util.List;

public interface QueryDSLMenuRepository {

    List<NavDto> findNavList();
    List<HeadDto> findAllHeadMenus();
    List<NavInfoDto> findAllMenuList();
    List<HeadDto> findHeadList();
    List<MenuDto> findMenuList();
}
