package com.myweb.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FileVO {
	
	private String uuid;
	private String saveDir;
	private String fileName;
	private int fileType;
	private Long bno;
	private	Long fileSize;
	private String regAt;
	
}
