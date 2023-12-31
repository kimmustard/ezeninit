package com.myweb.www.controller;

import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.FileHandler;
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
	private final FileHandler fh;
	
	
	@GetMapping("/company")
	public String mapForm() {
		return "/board/company";
	}
	
	
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("bvo", new BoardVO());
		return "/board/register";
	}
	
	@GetMapping("/noticeRegister")
	public String noticeRegisterForm(Model model) {
		model.addAttribute("bvo", new BoardVO());
		return "/board/noticeRegister";
	}
	
	
	@PostMapping("/register")
	public String register(@Validated @ModelAttribute("bvo") BoardVO bvo, BindingResult bindingResult,
			@RequestParam(name = "files", required = false) MultipartFile[] files) {
		log.info("bvo = {}" , bvo);
		
		
		if(bindingResult.hasErrors()) {
			log.info("에러발생 = {}",bindingResult.getFieldError());
			return "/board/register";
		}
		
		List<FileVO> flist = null;
		if(files[0].getSize() > 0) {
			flist = fh.uploadFiles(files);
		}
		
		int isOk = bsv.insert(new BoardDTO(bvo, flist));
		log.info("register = {} ", (isOk > 0 ? "Ok" : "Fail"));
		return "redirect:/board/list";
	}
	
	@PostMapping("/noticeRegister")
	public String noticeRegister(@Validated @ModelAttribute("bvo") BoardVO bvo, BindingResult bindingResult,
			@RequestParam(name = "files", required = false) MultipartFile[] files) {
		log.info("bvo = {}" , bvo);
		
		if(bindingResult.hasErrors()) {
			log.info("에러발생 = {}",bindingResult.getFieldError());
			return "/board/register";
		}
		
		List<FileVO> flist = null;
		if(files[0].getSize() > 0) {
			flist = fh.uploadFiles(files);
		}
		
		int isOk = bsv.ninsert(new BoardDTO(bvo, flist));
		log.info("register = {} ", (isOk > 0 ? "Ok" : "Fail"));
		return "redirect:/board/noticeList";
	}
	
	
	@GetMapping("/list")
	public String list(Model model, PagingVO pgvo) {
		//공지사항
		List<BoardVO> noticeList = bsv.getNoticeList();
		model.addAttribute("noticeList", noticeList);
		
		//일반게시물
		List<BoardVO> list = bsv.getList(pgvo);
		model.addAttribute("list", list);
		
		int totalCount = bsv.getTotalCount(pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		model.addAttribute("ph",ph);
		
		return "/board/list";
	}
	
	@GetMapping("/noticeList")
	public String nlist(Model model, PagingVO pgvo) {
		//공지사항
		List<BoardVO> noticeList = bsv.getNoticeList();
		model.addAttribute("noticeList", noticeList);
		
		return "/board/noticeList";
	}
	
	// read count detail
	@GetMapping("/detail")
	public String detail(@RequestParam("bno") Long bno, Model model) {
		BoardDTO bdto = bsv.detail(bno);
		model.addAttribute("bvo", bdto.getBvo());
		model.addAttribute("bdto", bdto);
		return "/board/detail";
	}
	
	// not read count detail
	@GetMapping("/nodetail")
	public String nodetail(@RequestParam("bno") Long bno, Model model) {
		BoardDTO bdto = bsv.nodetail(bno);
		model.addAttribute("bvo", bdto.getBvo());
		model.addAttribute("bdto", bdto);
		return "/board/detail";
	}
	
	@GetMapping("/modify")
	public String modifyForm(@RequestParam("bno") Long bno, Model model) {
		BoardDTO bdto = bsv.nodetail(bno);
		model.addAttribute("bvo", bdto.getBvo());
		model.addAttribute("bdto", bdto);
		return "/board/modify";
	}
	
	@PostMapping("/modify")
	public String modify(@Validated @ModelAttribute("bvo") BoardVO bvo, BindingResult bindingResult, 
			Model model, RedirectAttributes rttr,
			@RequestParam(name = "files", required= false )MultipartFile[] files ) {
		
		if(bindingResult.hasErrors()) {
			BoardDTO bdto = bsv.nodetail(bvo.getBno());
			model.addAttribute("bvo", bvo);
			model.addAttribute("boardDTO" , bdto);
			return "/board/modify";
		}
		
		List<FileVO> flist = null;
		if(files[0].getSize() > 0) {
			flist = fh.uploadFiles(files);
		}
		
		int isOk = bsv.modify(new BoardDTO(bvo, flist));
		rttr.addAttribute("bno", bvo.getBno());
		return "redirect:/board/nodetail";
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("bno")Long bno, RedirectAttributes rttr) {
		int isOk = bsv.remove(bno);
		log.info("register = {} ", (isOk > 0 ? "Ok" : "Fail"));
		rttr.addFlashAttribute("isOk", isOk);
		return "redirect:/board/list";
	}
	
	@DeleteMapping("/file/{uuid}")
	public ResponseEntity<String> fileRemove(@PathVariable("uuid") String uuid){
		int isOk = bsv.removeFile(uuid);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) :
			 new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
	
	@GetMapping("/file/{uuid}")
	public ResponseEntity<Resource> fileDownload(@PathVariable("uuid") String uuid) throws MalformedURLException {
		
		//uuid를 이용하여 파일 객체조회
		FileVO fvo = bsv.getFile(uuid);
		
		//조회해온 파일 객체에서 추출
		String date = fvo.getSaveDir();
		String fileName = fvo.getFileName();

		//풀경로
		String fullName = "file:///D:\\_myweb\\_java\\fileupload\\"+date+"\\"+uuid+"_"+fileName;
		log.info("Download fullName>>>>>>>> = {}", fullName);
		
		//resource객체로 매핑
		UrlResource resource = new UrlResource(fullName);
		String encodedUploadFileName = UriUtils.encode(fileName, StandardCharsets.UTF_8);
		String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";
		log.info("contentDisposition = {}", contentDisposition);
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
				.body(resource);
	}
	
	

	
}
