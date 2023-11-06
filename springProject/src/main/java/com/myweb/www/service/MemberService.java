package com.myweb.www.service;

import java.util.List;


import com.myweb.www.security.MemberVO;

public interface MemberService {

	int register(MemberVO mvo);

	boolean updateLastLogin(String authEmail);

	List<MemberVO> MemberList();

	MemberVO getUser(String email);

	int noPwdMod(MemberVO mvo);

	int pwdMod(MemberVO mvo);

	int userDel(String email);



	

	

}
