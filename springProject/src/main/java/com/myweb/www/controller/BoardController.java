package com.myweb.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardVO;
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
	public String registerForm() {
		return "/board/register";
	}
	
	@PostMapping("/register")
	public String register(@ModelAttribute("bvo") BoardVO bvo ) {
		log.info("register bvo = {}", bvo);
		bsv.insert(bvo);
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		List<BoardVO> list = bsv.getList();
		model.addAttribute("list", list);
		return "/board/list";
	}
	
	@GetMapping("/detail")
	public String detail(@RequestParam("bno") Long bno, Model model) {
		BoardVO bvo = bsv.detail(bno);
		model.addAttribute("bvo", bvo);
		return "/board/detail";
	}
	
	@GetMapping("/modify")
	public String modifyForm(@RequestParam("bno") Long bno, Model model) {
		BoardVO bvo = bsv.detail(bno);
		model.addAttribute("bvo", bvo);
		return "/board/modify";
	}
	
	@PostMapping("/modify")
	public String modify(@ModelAttribute("bvo") BoardVO bvo, Model model, RedirectAttributes rttr) {
		int isOk = bsv.modify(bvo);
		rttr.addAttribute("bno", bvo.getBno());
		return "redirect:/board/detail";
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("bno")Long bno) {
		int isOk = bsv.remove(bno);
		
		return "redirect:/board/list";
	}
	
	
	
	
}
