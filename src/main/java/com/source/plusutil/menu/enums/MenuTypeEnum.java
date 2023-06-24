package com.source.plusutil.menu.enums;

import java.util.Arrays;

public enum MenuTypeEnum {
    NAV("nav"), //최상단 네비게이션명
    HEAD("head"), //네비게이션 하단 부제목들
    MENU("menu"); //부제목의 메뉴들
    String type;
    MenuTypeEnum(String type) {
        this.type = type;
    }
    public String getMenuType() {
        return type;
    }
    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
