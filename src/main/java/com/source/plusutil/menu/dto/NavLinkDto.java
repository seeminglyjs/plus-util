package com.source.plusutil.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NavLinkDto {

    String navName; //메인 메뉴명
    List<SubLinkDto> subLinkDtoList; //메인 메뉴 자식 메뉴

    public String getNavName() {
        return navName;
    }

    public void setNavName(String navName) {
        this.navName = navName;
    }

    public List<SubLinkDto> getSubLinkDtoList() {
        return subLinkDtoList;
    }

    public void setSubLinkDtoList(List<SubLinkDto> subLinkDtoList) {
        this.subLinkDtoList = subLinkDtoList;
    }
}
