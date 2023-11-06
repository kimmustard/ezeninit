package com.myweb.www.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.CommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

	private final CommentDAO cdao;

	@Override
	public int post(CommentVO cvo) {
		return cdao.insert(cvo);
	}

//	@Override
//	public List<CommentVO> getList(long bno) {
//		return cdao.getList(bno);
//	}

	@Override
	public int remove(long cno) {
		
		return cdao.delete(cno);
	}

	@Override
	public void cmtDeleteAll(Long bno) {
		
		cdao.cmtDeleteAll(bno);
	}

	@Override
	public int modify(CommentVO cvo) {
		
		return cdao.modify(cvo);
	}

	@Override
	public PagingHandler getList(long bno, PagingVO pgvo) {
			// totalCount 구하기
			int totalCount = cdao.selectOneBnoTotalCount(bno);
			// CommentList	찾아오기
			List<CommentVO> list = cdao.selectListPaging(bno, pgvo);
			// PagingHandler 값 완성해서 리턴
			PagingHandler ph = new PagingHandler(pgvo, totalCount, list);
		
		return ph;
	}
	
}
