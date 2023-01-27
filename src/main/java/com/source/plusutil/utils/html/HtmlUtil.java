package com.source.plusutil.utils.html;

public class HtmlUtil {

    public static String escapeDataPlusSpan(String data){
     String result = "";
     StringBuilder sb = new StringBuilder();
     result = data.replaceAll("&lt;br&gt;","<br>")
             .replaceAll("&lt;","<span><</span>")
             .replaceAll("&gt;","<span>></span>");

     return  result;
    }

    public static String escapeDataMinusSpan(String data){
        String result = "";
        StringBuilder sb = new StringBuilder();
        result = data.replaceAll("<br>",System.lineSeparator())
                .replaceAll("<span><</span>","<")
                .replaceAll("<span>></span>",">");

        return  result;
    }

    public static String containLineSeparatorDataPlusBr(String data){
        String result = "";
        result = data.replaceAll(System.lineSeparator(),"<br>");
        return  result;
    }
}
