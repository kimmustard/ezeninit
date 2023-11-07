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

@Slf4j
@Controller
@RequestMapping("/member/**")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService msv;
	private final BCryptPasswordEncoder bcEncoder;
	private final MemberDAO mdao;
	
	@GetMapping("/register")
	public String registerForm(@ModelAttribute("mvo")MemberVO mvo, Model model) {
		
		model.addAttribute("mvo", mvo);
		return "/member/register";
	}
	
	@PostMapping("/register")
	public String register(@Validated @ModelAttribute("mvo") MemberVO mvo, BindingResult bindingResult, Model model) {
		log.info("member register mvo = {}", mvo);
		
		if(bindingResult.hasErrors()) {
			log.info("errors = {}", bindingResult.getAllErrors());
			return "/member/register";
		}
		
		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
		int isOk = msv.register(mvo);
		return "/member/login";
	}
	
	
	@GetMapping("/login")
	public String loginForm() {
		return "/member/login";
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request, RedirectAttributes rttr) {
		rttr.addAttribute("email", request.getAttribute("email"));
		rttr.addAttribute("errMsg", request.getAttribute("errMsg"));
		return "redirect:/member/login";
	}
	
	@GetMapping("/list")
	public String memberList(Model model) {
		List<MemberVO> list = msv.memberList();
		
		for(MemberVO mvo : list) {
			mvo.setAuthList(mdao.selectAuths(mvo.getEmail()));
		}
		
		model.addAttribute("list",list);
		return "/member/list";
	}
	
	
	@GetMapping("/detail")
	public String detailForm(@RequestParam("email")String email, Model model) {
		MemberVO mvo = msv.getUser(email);
		model.addAttribute("mvo", mvo);
		return "/member/detail";
	}
	
	@PostMapping("/detail")
	public String detail(MemberVO mvo, HttpServletRequest request,Model model, HttpServletResponse response) {
		log.info("detail mvo = {}", mvo);
		// 비밀번호가 null일때,
		if(mvo.getPwd().isEmpty()) {
			int isMod = msv.noPwdMod(mvo);
			log.info("비밀번호 NULL일시 저장 check");
			model.addAttribute("isMod", isMod);
			logout(request, response);
			return "/member/login";
		}
		//입력한 비밀번호를 -> 인코딩하여 -> mvo에 비밀번호 새로 세팅
		log.info("비밀번호 정상 저장 check");
		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
		int isMod = msv.pwdMod(mvo);
		logout(request, response);
		
		return "/member/login";
	}
	
	@GetMapping("remove")
	public String remove(@RequestParam("email")String email, HttpServletRequest request, HttpServletResponse response) {
		
		int isDel = msv.userDel(email);
		
		logout(request, response);
		return "/member/login";
		
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) {
		//사용자 정보를 찾는 인자
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		new SecurityContextLogoutHandler().logout(request, response, auth);
	}

}
