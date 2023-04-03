package com.source.plusutil.encrypt.dto

class AesEncryptRequestDto(val aes256Key: String, val aes256Iv:String, val aes256Content :String, val type : String = "256") {
    override fun toString(): String {
        return "AesEncryptRequestDto(aes256Key='$aes256Key', aes256Iv='$aes256Iv', aes256Content='$aes256Content', type='$type')"
    }
}