package com.source.plusutil.enums.regex;

public enum RegexExpressionEnum {
    ALL_PERMIT("[ㄱ-ㅎㅏ-ㅣ가-힣A-Za-z0-9`~!-_\\{|\\}\\<|\\>.\\,\\)\\(\\[|\\]@#$%^&*|\\\\\\'\\\";:\\/?\\s]") //모든 문자 허용 정규식

    ,DIGIT_4_PERMIT("\\d{4}") //숫자 4자리 허용 정규식 보통 연도에 사용
    ,DIGIT_8_UNDER_PERMIT("\\d{1,8}") //숫자 8자리 이하 허용 정규식

    ,DATE_DEFAULT_PERMIT("\\d{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])") //기본날짜 포맷 20221202
    ,DATE_MONTH_SLASH_DAY_PERMIT("(0[1-9]|1[012])\\/(0[1-9]|[12][0-9]|3[01])"); //월일 하고 슬러시 12/05
    String regex;
    RegexExpressionEnum(String regex) {
        this.regex = regex;
    }
    public String getRegex() {
        return regex;
    }
}
