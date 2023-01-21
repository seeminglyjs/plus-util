package com.source.plusutil.utils.protect;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class XSSUtils {
    private XSSUtils() {}

    public static String stripXSS(String value) {
        return value == null ? value : value.replaceAll("<","&lt;").replaceAll(">","&gt;");
    }

    public static String unStripXSS(String value) {
        return value == null ? value : value.replaceAll("&lt;","<").replaceAll("&gt;",">");
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
