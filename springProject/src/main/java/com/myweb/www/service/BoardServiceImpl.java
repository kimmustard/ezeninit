package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardDAO bdao;
	private final FileDAO fdao;
	private final CommentService csv;
	
	
	@Override
	public int insert(BoardDTO bdto) {
		int isUp = bdao.insert(bdto.getBvo());
		
		if(bdto.getFlist() == null) {
			isUp *= 1;
			return isUp;
		}
		
		//bvo insert 이후 파일이 없으면?
		if(isUp > 0 && bdto.getFlist().size() > 0) {
			Long bno = bdao.selectLastBno();
			for (FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isUp *= fdao.inserFile(fvo);
			}
		}
		
		return isUp;
	}

	@Transactional
	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		bdao.cntCommentList();
		bdao.cntFileList();
		return bdao.getList(pgvo);
	}

	@Transactional
	@Override
	public BoardDTO detail(Long bno) {
		bdao.readCnt(bno);
		
		BoardVO bvo = bdao.detail(bno);
		List<FileVO> flist = fdao.selectList(bno);
		BoardDTO bdto = new BoardDTO();
		bdto.setBvo(bvo);
		bdto.setFlist(flist);
		return bdto;
	}
	@Transactional
	@Override
	public BoardDTO nodetail(Long bno) {
		BoardVO bvo = bdao.detail(bno);
		List<FileVO> flist = fdao.selectList(bno);
		BoardDTO bdto = new BoardDTO();
		bdto.setBvo(bvo);
		bdto.setFlist(flist);
		return bdto;
	}

	@Override
	public int modify(BoardDTO bdto) {
		
		int isOk = bdao.modify(bdto.getBvo());
		
		if(bdto.getFlist() == null) {
			isOk *= 1;
		}else {
			if(isOk >0 && bdto.getFlist().size() > 0) {
				Long bno = bdto.getBvo().getBno();
				// 모든 fvo에 bno 세팅
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					isOk *= fdao.inserFile(fvo);
				}
			}
		}
		return isOk;
	}

	@Transactional
	@Override
	public int remove(Long bno) {
		csv.cmtDeleteAll(bno);
		fdao.fileDeleteAll(bno);
		return bdao.remove(bno);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		return bdao.getTotalCount(pgvo);
	}

	@Override
	public int removeFile(String uuid) {
		
		return fdao.removeFile(uuid);
	}

	@Override
	public FileVO getFile(String uuid) {
		
		return fdao.getFile(uuid);
	}

	

}
