package com.source.plusutil.menu;

import com.source.plusutil.menu.dto.*;

import java.util.List;

public interface MenuService {
    List<NavDto> getNavList();
    List<HeadDto> getHeadList();
    List<MenuDto> getMenuList();
    List<MenuResponseDto> getAllMenuList();
    MenuEnrollResponseDto enrollMenuInfo(MenuEnrollRequestDto menuEnrollRequestDto);
}
