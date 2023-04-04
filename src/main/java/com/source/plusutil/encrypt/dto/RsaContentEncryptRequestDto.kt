package com.source.plusutil.encrypt.dto

data class RsaContentEncryptRequestDto(val rsaPublicKey: String = "empty", val rsaBeforeContent : String) {
}