package com.source.plusutil.encrypt

import com.source.plusutil.encrypt.dto.AesDecryptRequestDto
import com.source.plusutil.encrypt.dto.AesEncryptRequestDto
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.hamcrest.core.Is
import org.hamcrest.core.IsNot
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class AesEncryptTest {
    @Autowired
    var encryptService: EncryptService? = null
    @Test
    fun aesEncryptTest() {
        println()
        println()
        println()
        println("================ AesEncryptTest Start =====================")
        val aesEncryptRequestDto = AesEncryptRequestDto(
                "asdqwe123sdasczxvsdgfqwer234234e", "asdqwe123sdasc33", "test", "256")
        val aesEncryptResponseDto = encryptService!!.makeAseEncryptContent(aesEncryptRequestDto)
        MatcherAssert.assertThat("aesEncryptResponseDto is null", aesEncryptResponseDto, Is.`is`(IsNot.not(Matchers.nullValue())))
        MatcherAssert.assertThat("aesEncryptResponseDto.getAes256Key is null", aesEncryptResponseDto.aesKey, Is.`is`(IsNot.not(Matchers.nullValue())))
        MatcherAssert.assertThat("aesEncryptResponseDto.getAes256Iv is null", aesEncryptResponseDto.aesIv, Is.`is`(IsNot.not(Matchers.nullValue())))
        MatcherAssert.assertThat("aesEncryptResponseDto.getAes256Content is null", aesEncryptResponseDto.aesContent, Is.`is`(IsNot.not(Matchers.nullValue())))
        MatcherAssert.assertThat("aesEncryptResponseDto.getEncryptContent is null", aesEncryptResponseDto.encryptContent, Is.`is`(IsNot.not(Matchers.nullValue())))
        println("aesEncryptResponseDto->$aesEncryptResponseDto")
        println("================ AesEncryptTest Success =====================")
        println()
        println()
        println()
    }

    @Test
    fun aesDecryptTest() {
        println()
        println()
        println()
        println("================ AesDecryptTest Start =====================")
        val aesDecryptRequestDto = AesDecryptRequestDto(
                "asdqwe123sdasczxvsdgfqwer234234e", "asdqwe123sdasc33", "FgQQBEmLSmVQnDDYT/MWNA==", "256")
        val aesDecryptResponseDto = encryptService!!.makeAesDecryptContent(aesDecryptRequestDto)
        MatcherAssert.assertThat("aesDecryptResponseDto is null", aesDecryptResponseDto, Is.`is`(IsNot.not(Matchers.nullValue())))
        MatcherAssert.assertThat("aesDecryptResponseDto.getAes256Key is null", aesDecryptResponseDto.aes256Key, Is.`is`(IsNot.not(Matchers.nullValue())))
        MatcherAssert.assertThat("aesDecryptResponseDto.getAes256Iv is null", aesDecryptResponseDto.aes256Iv, Is.`is`(IsNot.not(Matchers.nullValue())))
        MatcherAssert.assertThat("aesDecryptResponseDto.getAes256Content is null", aesDecryptResponseDto.aes256Content, Is.`is`(IsNot.not(Matchers.nullValue())))
        MatcherAssert.assertThat("aesDecryptResponseDto.getDecryptContent is null", aesDecryptResponseDto.decryptContent, Is.`is`(IsNot.not(Matchers.nullValue())))
        println("aesDecryptResponseDto->$aesDecryptResponseDto")
        println("================ AesDecryptTest Success =====================")
        println()
        println()
        println()
    }
}