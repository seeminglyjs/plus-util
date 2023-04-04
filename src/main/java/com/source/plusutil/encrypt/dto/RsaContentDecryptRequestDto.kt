package com.source.plusutil.encrypt.dto

data class RsaContentDecryptRequestDto(val rsaPrivateKey: String = "empty", val rsaAfterContent:String) {
}