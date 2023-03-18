package com.source.plusutil.enums.returnUrl;

public enum LoginReturnUrl {
    LOGIN_MAIN("/login/loginMain"),
    NEXT_LOGIN_MAIN("http://localhost:3000/login/main");
    String url;
    LoginReturnUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}
