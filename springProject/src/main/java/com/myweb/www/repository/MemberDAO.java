package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.security.AuthVO;
import com.myweb.www.security.MemberVO;

public interface MemberDAO {

	MemberVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	int updateLastLogin(String authEmail);

	int insert(MemberVO mvo);

	int insertAuthInit(String email);

	MemberVO getUser(String email);

	int noPwdMod(MemberVO mvo);
	int pwdMod(MemberVO mvo);

	int remove(String email);

	List<MemberVO> memberList();

}
