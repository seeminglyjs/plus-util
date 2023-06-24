package com.source.plusutil.menu;

import com.source.plusutil.menu.dto.MenuEnrollRequestDto;
import com.source.plusutil.menu.dto.MenuEnrollResponseDto;
import com.source.plusutil.menu.dto.MenuResponseDto;
import com.source.plusutil.menu.dto.NavDto;

import java.util.List;

public interface MenuService {
    List<NavDto> getNavList();
    List<MenuResponseDto> getMenuList();
    MenuEnrollResponseDto enrollMenuInfo(MenuEnrollRequestDto menuEnrollRequestDto);
}
