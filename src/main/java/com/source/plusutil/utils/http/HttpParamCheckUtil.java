package com.source.plusutil.utils.http;

import lombok.extern.slf4j.Slf4j;
import net.sf.javainetlocator.InetAddressLocator;
import net.sf.javainetlocator.InetAddressLocatorException;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
public class HttpParamCheckUtil {

    public static Map<String, String> httpRequestParamToMap(HttpServletRequest request){
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<String> enumber = request.getParameterNames();

        while (enumber.hasMoreElements()) {
            String key = enumber.nextElement();
            String value = request.getParameter(key);
            map.put(key, value);
        }

        return map;
    }

    public static Map<String, String> getHttpRequestIpInfo(HttpServletRequest request) throws InetAddressLocatorException {
        Map<String, String> map = new HashMap<String, String>();
        String ip = "";
        if (request.getHeader("X-Forwarded-For") == null) {
            ip = request.getRemoteAddr();
        }else{
            ip = request.getHeader("X-Forwarded-For");
        }
        map.put("ip", ip);
        if(ip == null){return null;}
        Locale locale = InetAddressLocator.getLocale(ip);
        if(locale == null){
            map.put("country", "알수없음");
            map.put("code", "알수없음");
        }else{
            map.put("country", locale.getDisplayCountry());
            map.put("code", locale.getCountry());
        }
        return map;
    }

    public static Map<String, String> localeCheck(HttpServletRequest request) {
        Map<String, String> myIpMap = new HashMap<>();
        try {
            myIpMap = HttpParamCheckUtil.getHttpRequestIpInfo(request);
        } catch (InetAddressLocatorException e) {
            myIpMap.put("ip", "확인되지 않음");
            myIpMap.put("country", "확인되지 않음");
            myIpMap.put("code", "확인되지 않음");
        }
        return myIpMap;
    }
}
