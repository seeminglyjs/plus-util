package com.source.plusutil.encrypt

import com.source.plusutil.encrypt.dto.RsaContentDecryptRequestDto
import com.source.plusutil.encrypt.dto.RsaContentEncryptRequestDto
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.hamcrest.core.Is
import org.hamcrest.core.IsNot
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RsaEncryptTest {
    @Autowired
    var encryptService: EncryptService? = null
    @Test
    fun makeRsaKey() {
        println()
        println()
        println()
        println("============ makeRsaKey Start =================")
        println(encryptService!!.makeRsaKey())
        println("============ makeRsaKey end =================")
        println()
        println()
        println()
    }

    @Test
    fun rsaEncryptAndDecrypt() {
        println()
        println()
        println()
        println("============ RsaEncryptAndDecrypt Start =================")
        val rsaKeyMakeResponseDto = encryptService!!.makeRsaKey()
        MatcherAssert.assertThat("rsaKeyMakeResponseDto is null", rsaKeyMakeResponseDto, Is.`is`(IsNot.not(Matchers.nullValue())))
        val rsaContentEncryptRequestDto = RsaContentEncryptRequestDto(rsaKeyMakeResponseDto.publicKey, "test")
        val rsaContentEncryptResponseDto = encryptService!!.makeRsaEncryptContent(rsaContentEncryptRequestDto)
        println(rsaContentEncryptResponseDto)
        MatcherAssert.assertThat("rsaContentEncryptResponseDto is null", rsaContentEncryptResponseDto, Is.`is`(IsNot.not(Matchers.nullValue())))
        val rsaContentDecryptRequestDto = RsaContentDecryptRequestDto(rsaKeyMakeResponseDto.privateKey, rsaContentEncryptResponseDto.encryptContent)
        val rsaContentDecryptResponseDto = encryptService!!.makeRsaDecryptContent(rsaContentDecryptRequestDto)
        println(rsaContentDecryptResponseDto)
        MatcherAssert.assertThat("rsaContentDecryptResponseDto is null", rsaContentDecryptResponseDto, Is.`is`(IsNot.not(Matchers.nullValue())))
        MatcherAssert.assertThat("rsaContentDecryptResponseDto.getDecryptContent is not test", rsaContentDecryptResponseDto.decryptContent, Is.`is`("test"))
        println("============ RsaEncryptAndDecrypt end =================")
        println()
        println()
        println()
    }
}