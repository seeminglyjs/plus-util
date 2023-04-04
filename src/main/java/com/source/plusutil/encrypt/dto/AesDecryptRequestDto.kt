package com.source.plusutil.encrypt.dto

class AesDecryptRequestDto(val aesKey: String, val aesIv:String, val aesContent :String, val type : String = "256") {
    override fun toString(): String {
        return "AesDecryptRequestDto(aes256Key='$aesKey', aes256Iv='$aesIv', aes256Content='$aesContent', type='$type')"
    }
}