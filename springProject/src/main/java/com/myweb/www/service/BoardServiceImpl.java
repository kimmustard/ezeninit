package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardDAO bdao;

	@Override
	public void insert(BoardVO bvo) {
		bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		bdao.cntCommentList();
		return bdao.getList(pgvo);
	}

	@Override
	public BoardVO detail(Long bno) {
		bdao.readCnt(bno);
		return bdao.detail(bno);
	}
	@Override
	public BoardVO nodetail(Long bno) {
		return bdao.detail(bno);
	}

	@Override
	public int modify(BoardVO bvo) {
		return bdao.modify(bvo);
	}

	@Override
	public int remove(Long bno) {
		return bdao.remove(bno);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		return bdao.getTotalCount(pgvo);
	}

}
