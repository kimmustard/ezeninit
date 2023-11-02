package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;

public interface BoardDAO {

	void insert(BoardVO bvo);

	List<BoardVO> getList();

	BoardVO detail(Long bno);
	void readCnt(Long bno);

	int modify(BoardVO bvo);

	int remove(Long bno);


}
