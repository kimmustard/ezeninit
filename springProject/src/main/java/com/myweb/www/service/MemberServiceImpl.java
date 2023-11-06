package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.repository.MemberDAO;
import com.myweb.www.security.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	private final MemberDAO mdao;

	@Transactional
	@Override
	public int register(MemberVO mvo) {
		int isOk = mdao.insert(mvo);
		
		return mdao.insertAuthInit(mvo.getEmail()); 
	}

	@Override
	public boolean updateLastLogin(String authEmail) {
		
		return mdao.updateLastLogin(authEmail) > 0 ? true : false;
	}

	@Override
	public List<MemberVO> MemberList() {
		
		return mdao.memberList();
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

		mdao.authRemove(email);
		return mdao.remove(email);
	}




}
