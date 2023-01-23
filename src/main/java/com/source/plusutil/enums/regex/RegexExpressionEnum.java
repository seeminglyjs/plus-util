package com.source.plusutil.enums.regex;

public enum RegexExpressionEnum {
    ALL_PERMIT("[ㄱ-ㅎㅏ-ㅣ가-힣A-Za-z0-9`~!-_\\{|\\}\\<|\\>.\\,\\)\\(\\[|\\]@#$%^&*|\\\\\\'\\\";:\\/?\\s]");
    String regex;
    RegexExpressionEnum(String regex) {
        this.regex = regex;
    }
    public String getRegex() {
        return regex;
    }
}
