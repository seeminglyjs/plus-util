package com.source.plusutil.menu;

import com.source.plusutil.menu.dto.MenuEnrollRequestDto;
import com.source.plusutil.menu.dto.MenuEnrollResponseDto;
import com.source.plusutil.menu.dto.MenuResponseDto;

import java.util.List;

public interface MenuService {
    List<MenuResponseDto> getMenuList();
    MenuEnrollResponseDto enrollMenuInfo(MenuEnrollRequestDto menuEnrollRequestDto);
}
