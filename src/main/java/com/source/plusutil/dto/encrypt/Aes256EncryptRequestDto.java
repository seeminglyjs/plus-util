package com.source.plusutil.dto.encrypt;

import lombok.Data;

@Data
public class Aes256EncryptRequestDto {//aes256암호화 요청을 감싸는 객체

	private String aes256Key;
	private String aes256Iv;
	private String aes256Content;
}
