package com.source.plusutil.enums.returnUrl;

public enum StringUtilReturnUrl {
    STRING_MAIN("/util/string/stringMain.html")
    ,STRING_GET_BYTE_MAIN("/util/string/getByteMain.html")
    ,STRING_GET_STRING_INITIAL_MAIN("/util/string/getStringInitial.html")
    ,STRING_GET_LENGTH_MAIN("/util/string/getLengthMain.html")
    ,STRING_CONVERT_UPPER_AND_LOWER_MAIN("/util/string/convertUpperAndLower.html")
    ,STRING_CHECK_SIMILARITY("/util/string/checkSimilarity.html");

    String url;
    StringUtilReturnUrl(String url) {
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}
