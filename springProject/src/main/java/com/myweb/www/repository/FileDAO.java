package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.FileVO;

public interface FileDAO {

	int inserFile(FileVO fvo);

	List<FileVO> selectList(Long bno);

	int removeFile(String uuid);

	void fileDeleteAll(Long bno);

	FileVO getFile(String uuid);

	List<FileVO> selectAllFiles();

}
