package com.source.plusutil.utils.html;

public class HtmlUtil {

    public static String escapeDataPlusSpan(String data){
     String result = "";
     result = data.replaceAll("&lt;","<span><</span>")
             .replaceAll("&gt;","<span>></span>");
     return  result;
    }
}
