package com.myweb.www.domain;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardVO {
	
	private Long bno;
	
	@NotBlank(message = "제목을 입력하세요.")
	private String title;
	
	private String writer;
	
	@NotBlank(message = "내용을 입력하세요.")
	private String content;
	
	private String regAt;
	private String modAt;
	private int readCount;
	private int cmtQty;
	private int hasFile;
	private String isDel;
	
}
