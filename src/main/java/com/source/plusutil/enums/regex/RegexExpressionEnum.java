package com.source.plusutil.enums.regex;

public enum RegexExpressionEnum {
    ALL_PERMIT("[ㄱ-ㅎㅏ-ㅣ가-힣A-Za-z0-9`~!-_\\{|\\}\\<|\\>.\\,\\)\\(\\[|\\]@#$%^&*|\\\\\\'\\\";:\\/?\\s]")
    ,DATE_DEFAULT_PERMIT("\\d{4}(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])");
    String regex;
    RegexExpressionEnum(String regex) {
        this.regex = regex;
    }
    public String getRegex() {
        return regex;
    }
}
