package com.myweb.www.handler;

import com.myweb.www.domain.PagingVO;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PagingHandler {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int realEndPage;
	private int totalCount;
	private PagingVO pgvo;
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		this.endPage = (int)Math.ceil(pgvo.getPageNo() / (double) pgvo.getQty()) * pgvo.getQty();
		
		this.startPage = endPage - 9;
		
		this.realEndPage = (int)Math.ceil(totalCount / (double) pgvo.getQty());
		
		if(realEndPage < endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1;
		this.next = endPage < realEndPage;
		
	}
	
}
