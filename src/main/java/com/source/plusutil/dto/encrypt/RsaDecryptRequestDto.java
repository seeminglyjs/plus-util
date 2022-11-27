package com.source.plusutil.dto.encrypt;

import lombok.Data;

@Data
public class RsaDecryptRequestDto {

	private String rsaPrivateKey;
	private String content;
}
