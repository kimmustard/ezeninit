package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	void insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);
	void cntCommentList();
	int getTotalCount(PagingVO pgvo);

	BoardVO detail(Long bno);
	void readCnt(Long bno);

	int modify(BoardVO bvo);

	int remove(Long bno);




}
