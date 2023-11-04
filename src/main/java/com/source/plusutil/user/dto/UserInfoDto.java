package com.source.plusutil.user.dto;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.*;

import com.source.plusutil.user.enums.Role;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.source.plusutil.enums.UserRoleEnum;

import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name="tb_user_info") //db상에 테이블명을 명시한다. [다른 dto / vo 객체에 똑같은 entity 선언하면 충돌남]
public class UserInfoDto implements UserDetails{
	
	//UserDetails spring security 에서 사용자의 정보를 담는 인터페이스 객체
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	private static final String PREFIX_ROLE = "ROLE_";//접두사를 권한에 붙여주어야 동작한다.

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_no") //명시적으로 적어두는게 좋다.
	private Integer userNo;
	
	@Column(name="user_email")
	private String userEmail;
	
	@Column(name="user_role")
//	@Enumerated(EnumType.STRING)
	private String userRole; //유저권한 USER / ADMIN
	
	@Column(name="user_password")
	private String userPassword;
	
	@Column(name="user_name")
	private String userName;

	@Column(name="user_phone")
	private String userPhone;
	
	@Column(name="user_lock_yn")
	private String userLockYN; //계정잠김 여부 
	
	@Column(name="sub_info1")
	private String subInfo1;
	
	@Column(name="sub_info2")
	private String subInfo2;
	
	@Column(name="sub_info3")
	private String subInfo3;
	
	@Builder //빌더형식으로 객체를 생성할건지에 대한 어노테이션 [기본 /로그인]
	public UserInfoDto(String userEmail, String userPassword, String userName, String userRole, String userLockYN) {
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userRole = userRole;
		this.userLockYN = userLockYN;
	}
	
	//회원가입 관련
    public static UserInfoDto makeUser(UserJoinDto userJoinDto, PasswordEncoder passwordEncoder) {
		return UserInfoDto.builder()
				.userEmail(userJoinDto.getUserEmail())
				.userPassword(passwordEncoder.encode(userJoinDto.getUserPassword()))
				.userName("unKnown") //미설정 값
				.userLockYN("N")  //잠금처리 N
				.userRole(String.valueOf(UserRoleEnum.USER)) //기본룰
				.build();
    }


	@Override //유저의 권한을 리턴해준다.
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> auth = new ArrayList<>();
		auth.add(new SimpleGrantedAuthority(PREFIX_ROLE+userRole));
		return auth;
	}
	
	@Override //계정의 패스워드를 리턴한다.
	public String getPassword() {
		return userPassword;
	}
	
	@Override //계정의 이름(아이디)를 리턴한다.
	public String getUsername() {
		return userEmail;
	}

	@Override //계정이 만료되었는지 체크 (true: 만료안됨)
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override //계정이 잠겼는지 체크 (true: 잠기지 않음)
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override //비밀번호 만료 여부를 체크 (true: 만료안됨)
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override //계정이 활성화(사용가능)인 지 리턴한다. (true: 활성화)
	public boolean isEnabled() {
		return true;
	}

	public Integer getUserNo() {
		return userNo;
	}

	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserLockYN() {
		return userLockYN;
	}

	public void setUserLockYN(String userLockYN) {
		this.userLockYN = userLockYN;
	}

	public String getSubInfo1() {
		return subInfo1;
	}

	public void setSubInfo1(String subInfo1) {
		this.subInfo1 = subInfo1;
	}

	public String getSubInfo2() {
		return subInfo2;
	}

	public void setSubInfo2(String subInfo2) {
		this.subInfo2 = subInfo2;
	}

	public String getSubInfo3() {
		return subInfo3;
	}

	public void setSubInfo3(String subInfo3) {
		this.subInfo3 = subInfo3;
	}
}
