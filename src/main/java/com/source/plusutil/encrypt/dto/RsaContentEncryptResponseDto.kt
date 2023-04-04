package com.source.plusutil.encrypt.dto

class RsaContentEncryptResponseDto(val rsaPublicKey: String, val encryptContent : String) {
    override fun toString(): String {
        return "RsaContentEncryptResponseDto(rsaPublicKey='$rsaPublicKey', encryptContent='$encryptContent')"
    }
}