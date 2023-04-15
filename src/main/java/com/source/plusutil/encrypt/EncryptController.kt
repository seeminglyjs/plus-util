package com.source.plusutil.encrypt

import com.source.plusutil.encrypt.dto.*
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.*

@RequestMapping("/plus/enc")
@RestController
@RequiredArgsConstructor
class EncryptController(private val encryptService: EncryptServiceImpl) {

    /*
	 * 요청 받은 정보를 aes256으로 암호화한다.
	 */
    @PostMapping("/aes/encrypt")
    @ResponseBody
    fun ase256Encrypt(@RequestBody aesEncryptRequestDto: AesEncryptRequestDto?): AesEncryptResponseDto {
        return encryptService.makeAseEncryptContent(aesEncryptRequestDto)
    }

    /*
	 * 요청 받은 정보를 aes256으로 복호화한다.
	 */
    @PostMapping("/aes/decrypt")
    @ResponseBody
    fun ase256Decrypt(@RequestBody aesDecryptRequestDto: AesDecryptRequestDto?): AesDecryptResponseDto {
        return encryptService.makeAesDecryptContent(aesDecryptRequestDto!!)
    }

    @GetMapping("/rsa/key/make")
    @ResponseBody
    fun rsaKeyMake(): RsaKeyMakeResponseDto {
        return encryptService.makeRsaKey()
    }

    /*
	 * RSA 암호화를 진행하는 컨트롤러
	 */
    @PostMapping("/rsa/content/encrypt")
    @ResponseBody
    fun rsaContentEncrypt(@RequestBody rsaContentEncryptRequestDto: RsaContentEncryptRequestDto?): RsaContentEncryptResponseDto {
        return encryptService.makeRsaEncryptContent(rsaContentEncryptRequestDto!!)
    }

    /*
	 * RSA 복호화를 진행하는 컨트롤러
	 */
    @PostMapping("/rsa/content/decrypt")
    @ResponseBody
    fun rsaContentDecrypt(@RequestBody rsaContentDecryptRequestDto: RsaContentDecryptRequestDto?): RsaContentDecryptResponseDto {
        return encryptService.makeRsaDecryptContent(rsaContentDecryptRequestDto!!)
    }
}