package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myweb.www.repository.MemberDAO;
import com.myweb.www.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MemberDAO mdao;
	
	@Override
	public boolean updateLastLogin(String authEmail) {
		return mdao.updateLastLogin(authEmail) > 0 ? true : false;
	}

	@Override
	public int register(MemberVO mvo) {
		int isOk = mdao.insert(mvo);	//가입 Mapper
		return mdao.insertAuthInit(mvo.getEmail());	//가입하면서 권한부여까지 동시에
	}

	@Override
	public MemberVO getUser(String email) {
		return mdao.getUser(email);
	}

	@Override
	public int noPwdMod(MemberVO mvo) {
		return mdao.noPwdMod(mvo);
	}

	@Override
	public int pwdMod(MemberVO mvo) {
		return mdao.pwdMod(mvo);
	}

	@Override
	public int userDel(String email) {
		return mdao.remove(email);
	}

	@Override
	public List<MemberVO> memberList() {
		
		return mdao.memberList();
	}
}
