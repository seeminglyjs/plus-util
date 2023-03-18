package com.source.plusutil.enums.returnUrl;

public enum HomeReturnUrl {
    HOME_MAIN("/home/homeMain"),
    NEXT_HOME_MAIN("http://localhost:3000/");

    String url;
    HomeReturnUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}
