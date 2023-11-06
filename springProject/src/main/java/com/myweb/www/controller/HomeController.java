package com.myweb.www.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles requests for the application home page.
 */
@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final BoardService bsv;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@GetMapping("/")
	public String home(Model model, Principal principal) {
		
		List<BoardVO> noticeList = bsv.getNoticeList();
		List<BoardVO> newList = bsv.getNewList();

		if(principal != null) {
			log.info("user name = {}", principal.getName());
			List<BoardVO> myList = bsv.getMyList(principal.getName());
			model.addAttribute("myList", myList);
		}
		
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("newList", newList);
		return "index";
	}
	
}
