package com.source.plusutil.encrypt.dto

data class RsaContentDecryptResponseDto(val rsaPrivateKey: String = "empty", val decryptContent:String) {
}