package com.source.plusutil.enums.returnUrl;

public enum FunReturnUrl {
    FUN_MAIN("/fun/funMain"),
    FUN_NUMBER_MAIN("/fun/api/funNumberMain");
    String url;
    FunReturnUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}

