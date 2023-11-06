package com.myweb.www.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO {
	
	private Long bno;
	
	@NotBlank(message = "제목을 입력해주세요.")
	private String title;
	
	@NotBlank(message = "내용을 입력해주세요.")
	private String content;
	
	private String writer;
	private String regAt;
	private String modAt;
	private int readCount;
	
	// 댓글 페이징
	private int cmtQty;
	private	int hasFile;
	
}
