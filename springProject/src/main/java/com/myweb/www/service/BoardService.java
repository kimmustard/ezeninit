package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

	void insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO detail(Long bno);
	BoardVO nodetail(Long bno);

	int modify(BoardVO bvo);

	int remove(Long bno);

	int getTotalCount(PagingVO pgvo);

}
