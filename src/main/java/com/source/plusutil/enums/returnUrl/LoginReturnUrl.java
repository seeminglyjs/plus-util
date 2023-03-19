package com.source.plusutil.enums.returnUrl;

public enum LoginReturnUrl {
    LOGIN_MAIN("/login/loginMain"),
    NEXT_LOGIN_MAIN("http://ec2-13-209-117-215.ap-northeast-2.compute.amazonaws.com:3000/login/");
    String url;
    LoginReturnUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}
