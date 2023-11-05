package com.source.plusutil.user.auth;


import com.source.plusutil.utils.auth.dto.AuthObject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {
    AuthObject makeAuthObject(Cookie token, Cookie userName, HttpServletResponse response) ;
}
