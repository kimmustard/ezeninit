package com.myweb.www.service;


import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;

public interface CommentService {

	int post(CommentVO cvo);

//	List<CommentVO> getList(long bno);

	int remove(long cno);

	void cmtDeleteAll(Long bno);

	int modify(CommentVO cvo);

	PagingHandler getList(long bno, PagingVO pgvo);


}
