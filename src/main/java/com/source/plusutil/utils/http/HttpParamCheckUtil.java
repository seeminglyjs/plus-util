package com.source.plusutil.utils.http;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
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

}
