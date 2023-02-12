package com.source.plusutil.enums.returnUrl;

public enum LoginReturnUrl {
    LOGIN_MAIN("/login/loginMain");
    String url;
    LoginReturnUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}
