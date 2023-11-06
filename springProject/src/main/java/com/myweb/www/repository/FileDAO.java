package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.FileVO;

public interface FileDAO {

	int insertFile(FileVO fvo);

	List<FileVO> selectList(Long bno);

	int removeFile(String uuid);

	FileVO getFile(String uuid);

	void fileDeleteAll(Long bno);

	List<FileVO> selectListAllFiles();



}
