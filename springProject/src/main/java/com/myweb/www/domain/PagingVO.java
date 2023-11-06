package com.myweb.www.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingVO {

	
	private int pageNo;	//현재페이지 번호
	private int qty;	//현재페이지 개수
	private String type;	//검색처리
	private String keyword;	//검색어 
	
	public PagingVO() {
		this(1,10);
	}
	
	public PagingVO(int pageNo, int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}
	
	/**
	 * limit 시작, qty: 시작페이지 번호 구하기
	 * pageNo 1 2 3 4
	 * limit 0, 10 // limit 10,10 // limit 20,10 .... 앞에는 시작번호, 뒤에는 번호갯수 
	 */
	public int getPageStart() {
		return (this.pageNo-1) * qty;
	}
	
	
	/**
	 * 타입의 형태를 여러가지 형태로 복합적인 검색을 하기위해서
	 * 타입의 키워드 t,c,w,tc,tw,ce,tcw
	 * 복합 타입을 배열로 나누기위해 사용
	 */
	public String[] getTypeToArray() {
		return this.type == null ? new String[] {} : this.type.split("");
	}
	
	
}
