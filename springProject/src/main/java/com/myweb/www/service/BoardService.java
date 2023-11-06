package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

//	int insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

//	BoardVO detail(Long bno);
//	BoardVO cntdetail(Long bno);
	
	BoardDTO getDetail(Long bno);
	BoardDTO getCntDetail(Long bno);

	int modify(BoardDTO boardDTO);

	int remove(Long bno);

	int getTotalCount(PagingVO pgvo);

	int insert(BoardDTO boardDTO);

	int removeFile(String uuid);

	FileVO getFile(String uuid);





}
