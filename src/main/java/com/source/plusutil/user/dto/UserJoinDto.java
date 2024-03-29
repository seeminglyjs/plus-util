package com.source.plusutil.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinDto {
	
	@NotBlank(message = "이메일은 필수값이며, 비어둘 수 없습니다.")
	@Email(message = "이메일 형식으로 입력해주세요.")
	private String userEmail;
	
	@NotEmpty(message = "비밀번호는 필수값이며, 비어둘 수 없습니다.")
	@Length(min = 8, max = 20, message = "비밀번호는 8자 이상, 20자 이하로 입력해주세요.")
	private String userPassword;

	@Override
	public String toString() {
		return "UserJoinDto{" +
				"userEmail='" + userEmail + '\'' +
				", userPassword='" + userPassword + '\'' +
				'}';
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
