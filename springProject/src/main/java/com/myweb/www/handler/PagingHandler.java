package com.myweb.www.handler;

import java.util.List;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PagingHandler {

	// 1~10 / 11~ 20 / 21 ~30
	private int startPage; //화면에 보여지는 시작페이지 네이션 번호
	private int endPage;	//화면에 보여지는 끝 페이지 네이션 번호
	private boolean prev, next;	// 이전, 다음버튼 존재여부
	private int realEndPage;
	private int totalCount;	//총 게시물 수
	private PagingVO pgvo;
	
	private List<CommentVO> cmtList;
	
	// 현재 페이지 값 가져오는 용도/ totalCount DB에서 조회 매개변수로 입력받기
	public PagingHandler(PagingVO pgvo, int totalCount) {
		//pgvo.qty 한 페이지에 보이는 게시글의 수
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		
		// 1~10 / 11~20 / 21~30  ....
		// 1페이지부터 10페이지까지 어떤 페이지가 선택되도 EndPage는 n * 10이여야함
		// 1 2 3 4 5 ....10 / 10 정수부분을 없애고, 나머지를 올림(ceil)으로 1로 만든다. 
		//화면에 페이지 네이게이션바 표시되어야하는 페이지 개수
		this.endPage = (int)Math.ceil(pgvo.getPageNo() / (double) pgvo.getQty()) * pgvo.getQty();
		
		
		this.startPage = endPage - 9;
		
		//전체 글수에서 / 한페이지에 표시되는 게시글 수 pgvo.getQty() => 올림
		this.realEndPage = (int)Math.ceil( totalCount / (double) pgvo.getQty());
		
		if(realEndPage < endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1;
		this.next = endPage < realEndPage;
		
		
		
	}
	
	public PagingHandler(PagingVO pgvo, int totalCount, 
			List<CommentVO> cmtList) {
		this(pgvo, totalCount);
		this.cmtList = cmtList;
	}
	
	
}
