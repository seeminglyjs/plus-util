package com.source.plusutil.utils.stringUtil

class StringEncodingUtil {

    fun romanNumeralsEncoding(year : Int) : String{
        var n = year; var result = ""
        while (true) {
            if (n <= 0) break;
            if (n >= 1000) {
                n -= 1000;
                result += "M"
            } else if (n >= 900) {
                n-=900
                result += "CM"
            } else if (n >= 500) {
                n-= 500
                result += "D";
            } else if (n >= 400) {
                n-= 400
                result += "CD"
            } else if (n >= 100) {
                n-= 100
                result += "C"
            }else if (n >= 90) {
                n-= 90
                result += "XC"
            }else if (n >= 50) {
                n-= 50
                result += "L"
            }else if (n >= 40) {
                n-= 40
                result += "XL"
            }else if (n >= 10) {
                n-= 10
                result += "X"
            }else if (n >= 9) {
                n -= 9
                result += "IX";
            }else if (n >= 5) {
                n-= 5
                result += "V";
            }else if (n >= 4) {
                n-= 4
                result += "IV";
            }else if (n >= 1) {
                n-= 1
                result += "I";
            }
        }
        return  result;
    }
}