package com.source.plusutil.test.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="tb_test") //db상에 테이블명을 명시한다.
public class TestInfo {

	@Id
	@Column(name="user_id") //명시적으로 적어두는게 좋다.
	private String userId;
	
	@Column(name="info")
	private String info;
}
