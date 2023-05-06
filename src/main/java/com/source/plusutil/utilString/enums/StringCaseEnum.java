package com.source.plusutil.utilString.enums;

import java.util.Arrays;

public enum StringCaseEnum {
    UPPER("upper"),
    LOWER("lower");
    final String str;
    StringCaseEnum(String str) {
        this.str = str;
    }
    public String getStr() {
        return str;
    }

    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
