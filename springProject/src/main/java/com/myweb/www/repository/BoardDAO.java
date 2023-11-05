package com.myweb.www.repository;

import java.util.List;


import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);
	int ninsert(BoardVO bvo);
	Long selectLastBno();

	List<BoardVO> getList(PagingVO pgvo);
	void cntCommentList();
	void cntFileList();
	int getTotalCount(PagingVO pgvo);

	BoardVO detail(Long bno);
	void readCnt(Long bno);

	int modify(BoardVO bvo);

	int remove(Long bno);
	
	/**/
	List<BoardVO> getNewList();
	List<BoardVO> getNoticeList();
	




}
