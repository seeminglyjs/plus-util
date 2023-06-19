package com.source.plusutil.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MenuResponseDto {

    String name;
    boolean existSubMenu; //서브 메뉴 존재여부
    List<NavLinkDto> navLinkDtoList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isExistSubMenu() {
        return existSubMenu;
    }

    public void setExistSubMenu(boolean existSubMenu) {
        this.existSubMenu = existSubMenu;
    }

    public List<NavLinkDto> getNavLinkDtoList() {
        return navLinkDtoList;
    }

    public void setNavLinkDtoList(List<NavLinkDto> navLinkDtoList) {
        this.navLinkDtoList = navLinkDtoList;
    }
}
