package com.myweb.www.domain;


import javax.validation.constraints.NotEmpty;

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
public class CommentVO {
	
	private Long cno;
	private Long bno;
	private String writer;
	
	@NotEmpty(message = "내용을 입력하세요.")
	private String content;
	private String regAt;
	private String modAt;
	private String isDel;

}
