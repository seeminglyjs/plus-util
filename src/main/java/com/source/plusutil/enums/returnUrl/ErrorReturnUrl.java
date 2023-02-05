package com.source.plusutil.enums.returnUrl;

public enum ErrorReturnUrl {
    ERROR_MAIN("/error/errorMain.html");
    String url;
    ErrorReturnUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}
