package com.source.plusutil.encrypt.dto

data class AesDecryptRequestDto(val aesKey: String, val aesIv:String, val aesContent :String, val type : String = "256") {
}