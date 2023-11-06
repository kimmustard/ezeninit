package com.myweb.www.handler;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myweb.www.domain.FileVO;
import com.myweb.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileSweeper {

	private final String BASE_PATH = "D:\\_myweb\\_java\\fileupload\\";
	
	private final FileDAO fdao; 
	
	//초 분 시 일 월 요일 년도 (생략 가능)
	@Scheduled(cron = "0 0 21 * * *")
	public void fileSweeper() {
		log.info("FileSweeper Running Start = {}", LocalDateTime.now());
		
		//DB에 등록된 파일목록 가져오기
		List<FileVO> dblist = fdao.selectListAllFiles();

		//저장소를 검색할 때 필요한 파일 경로 리스트(실제 존재해야 될 리스트)
		List<String> currFiles = new ArrayList<>();
		
		for (FileVO fvo : dblist) {
			String filePath = fvo.getSaveDir()+"\\"+fvo.getUuid();
			String fileName = fvo.getFileName();
			currFiles.add(BASE_PATH+filePath+"_"+fileName);	//fullPath 경로를 만들어준다.
			
			//이미지파일이면 썸네일
			//fvo.getFileType() > 0 true면 이미지라는 뜻
			if(fvo.getFileType() > 0) {
				currFiles.add(BASE_PATH+filePath+"_th_"+fileName);
			}
		}
		
		
		//날짜를 반영한 폴더 구조 경로 만들기
		LocalDate now = LocalDate.now();
		String today = now.toString();
		today =  today.replace("-", File.separator);
		
		//경로를 기반으로 저장되어있는 파일을 검색
		File dir = Paths.get(BASE_PATH + today).toFile();	//파일 하나가져오기
		File[] allFileObjects = dir.listFiles();	//파일 리스트 전체 가져오기
		
		//실제 저장되어 있는 파일과 DB에 존재하는 파일을 비교하여 없으면 삭제 진행
		for (File file : allFileObjects) {
			String storedFileName = file.toPath().toString();
		
			if(!currFiles.contains(storedFileName)) {
				file.delete();	// 실제 파일 삭제
				log.info("fileSweeper delete File = {}" , storedFileName);
			}
		}
		
		log.info("FileSweeper Running Finish = {}", LocalDateTime.now());
	}
}
