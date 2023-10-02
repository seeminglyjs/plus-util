package com.source.plusutil.encrypt

import com.source.plusutil.encrypt.dto.*
import com.source.plusutil.utils.encrypt.AesUtil
import com.source.plusutil.utils.encrypt.RsaUtil
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import java.util.*
import io.github.oshai.KotlinLogging

private val logger = KotlinLogging.logger {}
@Service
@RequiredArgsConstructor
class EncryptServiceImpl(private val rsa: RsaUtil) : EncryptService {

    override fun makeAseEncryptContent(aesEncryptRequestDto: AesEncryptRequestDto?): AesEncryptResponseDto {
        logger.info("AesEncryptRequestDto info -> [" + Objects.requireNonNull(aesEncryptRequestDto) + "]")
        val aesMap : MutableMap<String, String> =  AesUtil.aes256Encrypt(aesEncryptRequestDto!!.aesKey, aesEncryptRequestDto.aesIv, aesEncryptRequestDto.aesContent)
        return AesEncryptResponseDto(
                aesEncryptRequestDto.aesKey,
                aesEncryptRequestDto.aesIv,
                aesEncryptRequestDto.aesContent,
                aesEncryptRequestDto.type,
                when{aesMap["result"] == "y" -> aesMap["encryptContent"]!!else -> aesMap["message"]!! }
        )
    }

    override fun makeAesDecryptContent(aesDecryptRequestDto: AesDecryptRequestDto): AesDecryptResponseDto {
        logger.info("AesDecryptRequestDto info -> [" + Objects.requireNonNull(aesDecryptRequestDto) + "]")
        val aesMap : MutableMap<String, String> =  AesUtil.aes256Decrypt(aesDecryptRequestDto.aesKey, aesDecryptRequestDto.aesIv, aesDecryptRequestDto.aesContent)
        return AesDecryptResponseDto(
                aesDecryptRequestDto.aesKey,
                aesDecryptRequestDto.aesIv,
                aesDecryptRequestDto.aesContent,
                aesDecryptRequestDto.type,
                when{aesMap["result"] == "y" -> aesMap["decryptContent"]!!else -> aesMap["message"]!! }
        )
    }

    override fun makeRsaKey(): RsaKeyMakeResponseDto {
        val keyInfo = rsa.createKeypairAsString()
        return if (keyInfo != null) {
            RsaKeyMakeResponseDto(keyInfo["publicKey"]!!, keyInfo["privateKey"]!!)
        } else {
            RsaKeyMakeResponseDto("공캐키 생성 실패", "비밀키 생성 실패")
        }
    }

    override fun makeRsaEncryptContent(rsaContentEncryptRequestDto: RsaContentEncryptRequestDto): RsaContentEncryptResponseDto {
        return if (rsaContentEncryptRequestDto.rsaPublicKey != "empty") {
            RsaContentEncryptResponseDto(rsaContentEncryptRequestDto.rsaPublicKey, rsa.encode(rsaContentEncryptRequestDto.rsaBeforeContent, rsaContentEncryptRequestDto.rsaPublicKey))
        } else RsaContentEncryptResponseDto("RSA 암호화 실패", "RSA 암호화 실패")
    }

    override fun makeRsaDecryptContent(rsaContentDecryptRequestDto: RsaContentDecryptRequestDto): RsaContentDecryptResponseDto {
        return if (rsaContentDecryptRequestDto.rsaPrivateKey != "empty") {
            RsaContentDecryptResponseDto(
                    rsaContentDecryptRequestDto.rsaPrivateKey, rsa.decode(rsaContentDecryptRequestDto.rsaAfterContent, rsaContentDecryptRequestDto.rsaPrivateKey))
        } else {
            RsaContentDecryptResponseDto("RSA 복호화 실패", "RSA 복호화 실패")
        }
    }
}