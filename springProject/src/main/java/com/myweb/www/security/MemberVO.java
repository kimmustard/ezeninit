package com.myweb.www.security;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberVO {
	
	@NotEmpty(message = "아이디를 입력하세요.")
	private String email;
	@NotEmpty(message = "비밀번호를 입력하세요.")
	private String pwd;
	@NotEmpty(message = "닉네임을 입력하세요.")
	private String nickName;
	
	private String regAt;
	private String lastLogin;
	private String isDel;
	private List<AuthVO> authList;	//여러개의 권한을 리스트로 관리
	
}
