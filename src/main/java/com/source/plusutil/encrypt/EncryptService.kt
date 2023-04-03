package com.source.plusutil.encrypt

import com.source.plusutil.encrypt.dto.AesDecryptRequestDto
import com.source.plusutil.encrypt.dto.AesDecryptResponseDto
import com.source.plusutil.encrypt.dto.AesEncryptRequestDto
import com.source.plusutil.encrypt.dto.AesEncryptResponseDto

interface EncryptService {
    fun makeAseEncryptContent(aes256RequestDto: AesEncryptRequestDto?) : AesEncryptResponseDto
    fun makeAesDecryptContent(aesDecryptRequestDto: AesDecryptRequestDto) : AesDecryptResponseDto
}