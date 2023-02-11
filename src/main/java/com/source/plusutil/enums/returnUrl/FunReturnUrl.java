package com.source.plusutil.enums.returnUrl;

public enum FunReturnUrl {
    FUN_MAIN("/error/errorMain.html");
    String url;
    FunReturnUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}

