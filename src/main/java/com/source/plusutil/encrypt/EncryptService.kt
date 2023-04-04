package com.source.plusutil.encrypt

import com.source.plusutil.encrypt.dto.*

interface EncryptService {
    fun makeAseEncryptContent(aesEncryptRequestDto: AesEncryptRequestDto?) : AesEncryptResponseDto
    fun makeAesDecryptContent(aesDecryptRequestDto: AesDecryptRequestDto) : AesDecryptResponseDto
    fun makeRsaKey() : RsaKeyMakeResponseDto
    fun makeRsaEncryptContent(rsaContentEncryptRequestDto : RsaContentEncryptRequestDto): RsaContentEncryptResponseDto
    fun makeRsaDecryptContent(rsaContentDecryptRequestDto : RsaContentDecryptRequestDto): RsaContentDecryptResponseDto
}