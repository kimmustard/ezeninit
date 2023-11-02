package com.myweb.www.controller;

import java.util.List;

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

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {
	
	
	private final BoardService bsv;
	
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("bvo", new BoardVO());
		return "/board/register";
	}
	
	@PostMapping("/register")
	public String register(@Validated @ModelAttribute("bvo") BoardVO bvo , BindingResult bindingResult) {
		log.info("register check!");
		
		if(bindingResult.hasErrors()) {
			log.info("error 발생! = {}" , bindingResult.getFieldError());
			return "/board/register";
		}
		
		log.info("register bvo = {}", bvo);
		bsv.insert(bvo);
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public String list(Model model, PagingVO pgvo) {
		List<BoardVO> list = bsv.getList(pgvo);
		model.addAttribute("list", list);
		
		int totalCount = bsv.getTotalCount(pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		model.addAttribute("ph",ph);
		
		return "/board/list";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam("bno") Long bno, Model model) {
		BoardVO bvo = bsv.detail(bno);
		model.addAttribute("bvo", bvo);
		return "/board/detail";
	}
	
	@GetMapping("/nodetail")
	public String nodetail(@RequestParam("bno") Long bno, Model model) {
		BoardVO bvo = bsv.nodetail(bno);
		model.addAttribute("bvo", bvo);
		return "/board/detail";
	}
	
	@GetMapping("/modify")
	public String modifyForm(@RequestParam("bno") Long bno, Model model) {
		BoardVO bvo = bsv.nodetail(bno);
		model.addAttribute("bvo", bvo);
		return "/board/modify";
	}
	
	@PostMapping("/modify")
	public String modify(@Validated @ModelAttribute("bvo") BoardVO bvo, BindingResult bindingResult, Model model, RedirectAttributes rttr) {
		
		if(bindingResult.hasErrors()) {
			return "/board/modify";
		}
		
		int isOk = bsv.modify(bvo);
		rttr.addAttribute("bno", bvo.getBno());
		return "redirect:/board/nodetail";
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("bno")Long bno) {
		int isOk = bsv.remove(bno);
		
		return "redirect:/board/list";
	}
	
	
	
	
}
