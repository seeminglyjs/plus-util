package com.source.plusutil.utils.protect;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.unbescape.html.HtmlEscape.escapeHtml4;

public class XSSUtils {
    private XSSUtils()
    {

    }

    public static String stripXSS(String value) {
        return value == null ? value : escapeHtml4(value);
    }

    public static Map<String,String> stripXSSToMap(Map<String,String> requestMap){
        Map<String, String> changeMap = new HashMap<>();
        Set<String> mapKey =  requestMap.keySet();
        mapKey.forEach(key ->{
            String tempKey = "";
            String tempValue = "";
            tempKey = XSSUtils.stripXSS(key);
            tempValue = XSSUtils.stripXSS(requestMap.get(key));
            changeMap.put(tempKey, tempValue);
        });
        return changeMap;
    }
}
