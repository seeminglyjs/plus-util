package com.source.plusutil.enums.urlPattern;

import java.util.Arrays;

public enum UrlPatternEnum {
    HOME("/home/*"),
    LOGIN("/login/*"),
    JOIN("/join/*"),
    UTIL("/util/*"),
    ALGORITHM("/algorithm/*"),
    NOTICE("/notice/*"),
    ENCRYPT("/encrypt/*"),
    ERROR("/error/*");

    String pattern;
    UrlPatternEnum(String url) {
    }
    public String getPattern() {
        return pattern;
    }
    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }
}
