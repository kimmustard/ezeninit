package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardVO;

public interface BoardService {

	void insert(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO detail(Long bno);

	int modify(BoardVO bvo);

	int remove(Long bno);

}
