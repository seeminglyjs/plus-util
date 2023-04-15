package com.source.plusutil.encrypt.dto

data class AesEncryptResponseDto(val aesKey: String, val aesIv:String, val aesContent :String, val type : String = "256", val encryptContent : String) {

}