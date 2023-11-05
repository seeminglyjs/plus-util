package com.source.plusutil.user.auth;

import com.source.plusutil.utils.auth.dto.AuthObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/plus")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth")
    @ResponseBody
    public AuthObject loginView(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        Cookie userToken = null;
        Cookie userName = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userToken".equals(cookie.getName())) {
                    userToken = cookie;
                }else if("userName".equals(cookie.getName())){
                    userName = cookie;
                }
            }
        }
        return authService.makeAuthObject(userToken, userName, response);
    }
}
