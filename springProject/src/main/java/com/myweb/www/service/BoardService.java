package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

	/* BoardController */
	int insert(BoardDTO bdto);
	int ninsert(BoardDTO boardDTO);

	List<BoardVO> getList(PagingVO pgvo);

	BoardDTO detail(Long bno);
	BoardDTO nodetail(Long bno);

	int modify(BoardDTO bdto);

	int remove(Long bno);

	int getTotalCount(PagingVO pgvo);

	int removeFile(String uuid);

	FileVO getFile(String uuid);

	
	
	/* HomeController */
	List<BoardVO> getNewList();

	List<BoardVO> getNoticeList();



	

}
