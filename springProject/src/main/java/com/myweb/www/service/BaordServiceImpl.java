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
public class BaordServiceImpl implements BoardService{

	private final BoardDAO bdao;
	private final CommentService csv;
	private final FileDAO fdao;
	
	

//	@Override
//	public int insert(BoardVO bvo) {
//		
//		return bdao.insert(bvo);
//	}

	@Transactional
	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		bdao.updateCommentCount();
		bdao.updateFileCount();
		
		
		return bdao.getList(pgvo);
	}

//	@Override
//	public BoardVO detail(Long bno) {
//		
//		return bdao.detail(bno);
//	}
//	@Override
//	public BoardVO cntdetail(Long bno) {
//		bdao.readdetail(bno);
//		return bdao.cntdetail(bno);
//	}

	@Transactional
	@Override
	public BoardDTO getDetail(Long bno) {
		BoardVO bvo = bdao.getDetail(bno);
		List<FileVO> flist = fdao.selectList(bno);
		BoardDTO bdto = new BoardDTO();
		bdto.setBvo(bvo);
		bdto.setFlist(flist);
		
		return bdto;
	}
	
	@Transactional
	@Override
	public BoardDTO getCntDetail(Long bno) {
		bdao.readdetail(bno);	//카운터
		
		BoardVO bvo = bdao.getCntDetail(bno);
		List<FileVO> flist = fdao.selectList(bno);
		BoardDTO bdto = new BoardDTO();
		bdto.setBvo(bvo);
		bdto.setFlist(flist);
		
		return bdto;
	}
	
	@Transactional
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
					isOk *= fdao.insertFile(fvo);
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

	@Transactional
	@Override
	public int insert(BoardDTO bdto) {
		// bvo, flist 가져와서 각자 db에 저장하는 역할
		int isUp = bdao.insert(bdto.getBvo());
		
		if(bdto.getFlist() == null) {
			isUp *= 1;
			return isUp;
		}
		
		// bvo insert후 파일도 있다면...
		if(isUp > 0 && bdto.getFlist().size() > 0) {
			Long bno = bdao.selectOneBno();	// 가장 마지막에 등록된 bno
			// 모든 파일에 bno set
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isUp*= fdao.insertFile(fvo);
			}
			
		}
		return isUp; 
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
