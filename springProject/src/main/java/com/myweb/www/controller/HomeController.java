package com.myweb.www.controller;

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
	public String home(Model model) {
		List<BoardVO> noticeList = bsv.getNoticeList();
//		List<BoardVO> mList = bsv.getMyList(세션정보);
		List<BoardVO> newList = bsv.getNewList();
		
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("newList", newList);
		return "index";
	}
	
}
