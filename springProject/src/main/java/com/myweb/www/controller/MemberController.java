package com.myweb.www.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.repository.MemberDAO;
import com.myweb.www.security.MemberVO;
import com.myweb.www.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/member/**")
@RequiredArgsConstructor
public class MemberController {

	
	private final MemberService msv;
	private final MemberDAO mdao;
	
	private final BCryptPasswordEncoder bcEncoder;
	
	@GetMapping("/register")
	public String register(@ModelAttribute("mvo")MemberVO mvo, Model model) {
		
		
 		model.addAttribute("mvo", mvo);
		return "/member/register";
	}
	
	@PostMapping("/register")
	public String register(@Validated @ModelAttribute("mvo") MemberVO mvo, BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			return "/member/register";
		}
		
		log.info("mvo ={}" , mvo);
		
		//암호화
		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
		int isOk = msv.register(mvo);
		
		return "index";
	}
	
	@GetMapping("/login")
	public String loginForm() {
		return "/member/login";
	}
	
	@PostMapping("/login")
	public String loginPost(HttpServletRequest request, RedirectAttributes re) {
		
		//로그인 실패시 다시 로그인페이지로 돌아와 오류 메시지 전송
		//다시 로그인 유도
		re.addAttribute("email", request.getAttribute("email"));
		re.addAttribute("errMsg", request.getAttribute("errMsg"));
		
		return "redirect:/member/login";
	}
	
	
	@GetMapping("/member/list")
	public String memberList(Model model) {
		List<MemberVO> list = msv.MemberList();
		
		for (MemberVO mvo : list) {
			mvo.setAuthList(mdao.selectAuths(mvo.getEmail()));
		}
		model.addAttribute("list",list);
		return "/member/list";
	}
	
	@GetMapping("/member/detail")
	public String detailForm(@RequestParam("email") String email, Model model) {
		MemberVO mvo = msv.getUser(email);
		model.addAttribute("mvo", mvo);
		return "/member/detail";
	}
	
	@PostMapping("/member/detail")
	public String detail(MemberVO mvo, HttpServletRequest request, HttpServletResponse response){
		log.info("detail mvo = {}", mvo);
		if(mvo.getPwd().isEmpty()) {
			int isOk = msv.noPwdMod(mvo);
			log.info("비밀번호 null일시 저장완료!!!!!!!");
			logout(request, response);
			
			return "index";
			
		}
		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
		int isOk = msv.pwdMod(mvo);
		log.info("정상 저장완료!!!!!!!");
		logout(request, response);
		
		return "index";
	}
	
	@GetMapping("/member/remove")
	public String remove(@RequestParam("email")String email, HttpServletRequest request, HttpServletResponse response) {
		
		int isOk = msv.userDel(email);
		
		
		logout(request, response);
		return "index";		
	}

	
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		//사용자 정보를 찾는 인자 ?
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		new SecurityContextLogoutHandler().logout(request, response, auth);
	}
	

	
}
