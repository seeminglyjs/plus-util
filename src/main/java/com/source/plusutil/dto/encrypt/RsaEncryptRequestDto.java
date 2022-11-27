package com.source.plusutil.dto.encrypt;

import lombok.Data;

@Data
public class RsaEncryptRequestDto {

	private String rsaPublicKey;
	private String content;
}
