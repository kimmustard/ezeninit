package com.myweb.www.service;

import java.util.List;

import com.myweb.www.security.MemberVO;

public interface MemberService {

	boolean updateLastLogin(String authEmail);

	int register(MemberVO mvo);

	MemberVO getUser(String email);

	int noPwdMod(MemberVO mvo);
	int pwdMod(MemberVO mvo);

	int userDel(String email);

	List<MemberVO> memberList();

}
