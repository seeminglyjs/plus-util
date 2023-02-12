package com.source.plusutil.enums.returnUrl;

public enum HomeReturnUrl {
    HOME_MAIN("/home/homeMain");
    String url;
    HomeReturnUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}
