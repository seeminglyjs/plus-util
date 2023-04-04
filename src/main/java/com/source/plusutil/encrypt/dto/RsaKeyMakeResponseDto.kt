package com.source.plusutil.encrypt.dto

class RsaKeyMakeResponseDto(val publicKey: String, val privateKey: String) {
    override fun toString(): String {
        return "RsaKeyMakeResponseDto(publicKey='$publicKey', privateKey='$privateKey')"
    }
}