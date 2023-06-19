package com.source.plusutil.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NavInfoDto {
    private Long navNo;
    private String navName;
    private Long headNo;
    private String headName;
    private Long menuNo;
    private String menuName;
    private String url;

    public Long getNavNo() {
        return navNo;
    }

    public void setNavNo(Long navNo) {
        this.navNo = navNo;
    }

    public String getNavName() {
        return navName;
    }

    public void setNavName(String navName) {
        this.navName = navName;
    }

    public Long getHeadNo() {
        return headNo;
    }

    public void setHeadNo(Long headNo) {
        this.headNo = headNo;
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName;
    }

    public Long getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(Long menuNo) {
        this.menuNo = menuNo;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
