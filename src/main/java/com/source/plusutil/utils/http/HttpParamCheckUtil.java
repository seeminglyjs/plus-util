package com.source.plusutil.utils.http;

import net.sf.javainetlocator.InetAddressLocator;
import net.sf.javainetlocator.InetAddressLocatorException;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class HttpParamCheckUtil {

    public static Map<String, String> httpRequestParamToMap(HttpServletRequest request){
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<String> enumber = request.getParameterNames();

        while (enumber.hasMoreElements()) {
            String key = enumber.nextElement().toString();
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
        }else{
            map.put("country", locale.getDisplayCountry());
        }
        return map;
    }

}
