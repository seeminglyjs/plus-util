package com.source.plusutil.encrypt.dto

class AesEncryptResponseDto(val aes256Key: String, val aes256Iv:String, val aes256Content :String, val type : String = "256", val encryptContent : String) {
    override fun toString(): String {
        return "AesEncryptResponseDto(aes256Key='$aes256Key', aes256Iv='$aes256Iv', aes256Content='$aes256Content', type='$type', encryptContent='$encryptContent')"
    }
}