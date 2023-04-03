package com.source.plusutil.encrypt;

import com.source.plusutil.encrypt.dto.AesDecryptRequestDto;
import com.source.plusutil.encrypt.dto.AesDecryptResponseDto;
import com.source.plusutil.encrypt.dto.AesEncryptRequestDto;
import com.source.plusutil.encrypt.dto.AesEncryptResponseDto;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

@SpringBootTest
public class AesEncryptTest {

    @Autowired
    EncryptService encryptService;

    @Test
    public void aesEncryptTest(){
        System.out.println("================ AesEncryptTest Start =====================");
        AesEncryptRequestDto aes256RequestDto = new AesEncryptRequestDto(
                "asdqwe123sdasczxvsdgfqwer234234e"
                ,"asdqwe123sdasc33"
                ,"test"
                ,"256");
        AesEncryptResponseDto aesEncryptResponseDto =  encryptService.makeAseEncryptContent(aes256RequestDto);

        MatcherAssert.assertThat("aesEncryptResponseDto is null", aesEncryptResponseDto, is(not(nullValue())));
        MatcherAssert.assertThat("aesEncryptResponseDto.getAes256Key is null", aesEncryptResponseDto.getAes256Key(), is(not(nullValue())));
        MatcherAssert.assertThat("aesEncryptResponseDto.getAes256Iv is null", aesEncryptResponseDto.getAes256Iv(), is(not(nullValue())));
        MatcherAssert.assertThat("aesEncryptResponseDto.getAes256Content is null", aesEncryptResponseDto.getAes256Content(), is(not(nullValue())));
        MatcherAssert.assertThat("aesEncryptResponseDto.getEncryptContent is null", aesEncryptResponseDto.getEncryptContent(), is(not(nullValue())));

        System.out.println("aesEncryptResponseDto->" + aesEncryptResponseDto);
        System.out.println("================ AesEncryptTest Success =====================");
        System.out.println();
        System.out.println();
        System.out.println();
    }

    @Test
    public void aesDecryptTest() throws Exception {
        System.out.println("================ AesDecryptTest Start =====================");
        AesDecryptRequestDto aesDecryptRequestDto = new AesDecryptRequestDto(
                "asdqwe123sdasczxvsdgfqwer234234e"
                ,"asdqwe123sdasc33"
                ,"FgQQBEmLSmVQnDDYT/MWNA=="
                ,"256");

        AesDecryptResponseDto aesDecryptResponseDto =  encryptService.makeAesDecryptContent(aesDecryptRequestDto);

        MatcherAssert.assertThat("aesDecryptResponseDto is null", aesDecryptResponseDto, is(not(nullValue())));
        MatcherAssert.assertThat("aesDecryptResponseDto.getAes256Key is null", aesDecryptResponseDto.getAes256Key(), is(not(nullValue())));
        MatcherAssert.assertThat("aesDecryptResponseDto.getAes256Iv is null", aesDecryptResponseDto.getAes256Iv(), is(not(nullValue())));
        MatcherAssert.assertThat("aesDecryptResponseDto.getAes256Content is null", aesDecryptResponseDto.getAes256Content(), is(not(nullValue())));
        MatcherAssert.assertThat("aesDecryptResponseDto.getDecryptContent is null", aesDecryptResponseDto.getDecryptContent(), is(not(nullValue())));

        System.out.println("aesDecryptResponseDto->" + aesDecryptResponseDto);
        System.out.println("================ AesDecryptTest Success =====================");
        System.out.println();
        System.out.println();
        System.out.println();
    }

}
