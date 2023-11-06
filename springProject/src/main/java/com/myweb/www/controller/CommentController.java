package com.myweb.www.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.CommentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/comment/*")
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService csv;
	
	
	@PostMapping(value = "/post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody @Valid CommentVO cvo , BindingResult bindingResult){
		log.info("cvo = {}" , cvo);
		if (bindingResult.hasErrors()) {
			log.info("bindingResult = {}", bindingResult);
			return new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR) ;
		}
		
		log.info("bindingResult = {}", bindingResult);
		int isOk = csv.post(cvo);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) :
			 new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR) ;
		
	}
	
	@GetMapping(value = "/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagingHandler> spread(
			@PathVariable("bno")long bno, 
			@PathVariable("page")int page){
		
		
		log.info("List bno = {}", bno);
		log.info("List page = {}", page);
		PagingVO pgvo = new PagingVO(page, 5);
		
		PagingHandler list = csv.getList(bno, pgvo);
		
		return new ResponseEntity<PagingHandler> (list, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{cno}/{writer}")
	public ResponseEntity<String> remove(@PathVariable("cno") long cno, @PathVariable("writer")String writer, Principal principal){
		log.info("writer = {}" , writer);
		log.info("principal.getName() = {}" , principal.getName());
		
		
		if(!principal.getName().equals(writer)) {
			return new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR) ;
		}
		
		log.info("cno555555555 = {}",cno);
		int isOk = csv.remove(cno);
		
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) :
			 new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
	
	@PutMapping(value = "/{cno}", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> modify(@PathVariable("cno") long cno, @RequestBody CommentVO cvo, Principal principal){
		log.info("principal = {}",principal.getName());
		log.info("cvo.getWriter() = {}", cvo.getWriter());
		
		
		if(!principal.getName().equals(cvo.getWriter())) {
			return new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR) ;
		}
		
		
		
		int isOk = csv.modify(cvo);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) :
			 new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR) ;
	}
	
	
}
