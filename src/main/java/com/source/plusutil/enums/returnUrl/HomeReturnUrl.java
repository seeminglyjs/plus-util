package com.source.plusutil.enums.returnUrl;

public enum HomeReturnUrl {
    HOME_MAIN("/home/homeMain"),
    NEXT_HOME_MAIN("http://ec2-13-209-117-215.ap-northeast-2.compute.amazonaws.com:3000/");

    String url;
    HomeReturnUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}
