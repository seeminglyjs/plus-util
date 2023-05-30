package com.source.plusutil.encrypt;

import com.source.plusutil.encrypt.dto.*;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

@SpringBootTest
public class RsaEncryptTest {

    @Autowired
    EncryptService encryptService;

    @Test
    public void makeRsaKey() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("============ makeRsaKey Start =================");
        System.out.println(encryptService.makeRsaKey());
        System.out.println("============ makeRsaKey end =================");
        System.out.println();
        System.out.println();
        System.out.println();
    }


    @Test
    public void RsaEncryptAndDecrypt() {
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("============ RsaEncryptAndDecrypt Start =================");
        RsaKeyMakeResponseDto rsaKeyMakeResponseDto = encryptService.makeRsaKey();

        MatcherAssert.assertThat("rsaKeyMakeResponseDto is null", rsaKeyMakeResponseDto, is(not(nullValue())));

        RsaContentEncryptRequestDto rsaContentEncryptRequestDto = new RsaContentEncryptRequestDto(rsaKeyMakeResponseDto.getPublicKey(), "test");
        RsaContentEncryptResponseDto rsaContentEncryptResponseDto = encryptService.makeRsaEncryptContent(rsaContentEncryptRequestDto);
        System.out.println(rsaContentEncryptResponseDto);
        MatcherAssert.assertThat("rsaContentEncryptResponseDto is null", rsaContentEncryptResponseDto, is(not(nullValue())));

        RsaContentDecryptRequestDto rsaContentDecryptRequestDto = new RsaContentDecryptRequestDto(rsaKeyMakeResponseDto.getPrivateKey(), rsaContentEncryptResponseDto.getEncryptContent());
        RsaContentDecryptResponseDto rsaContentDecryptResponseDto =encryptService.makeRsaDecryptContent(rsaContentDecryptRequestDto);
        System.out.println(rsaContentDecryptResponseDto);
        MatcherAssert.assertThat("rsaContentDecryptResponseDto is null", rsaContentDecryptResponseDto, is(not(nullValue())));

        MatcherAssert.assertThat("rsaContentDecryptResponseDto.getDecryptContent is not test", rsaContentDecryptResponseDto.getDecryptContent(), is("test"));

        System.out.println("============ RsaEncryptAndDecrypt end =================");
        System.out.println();
        System.out.println();
        System.out.println();
    }

}
