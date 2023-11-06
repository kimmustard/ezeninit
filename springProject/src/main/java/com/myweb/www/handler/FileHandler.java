package com.myweb.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.FileVO;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Component
public class FileHandler {

	private final String UP_DIR = "D:\\_myweb\\_java\\fileupload";
	
	public List<FileVO> uploadFiles(MultipartFile[] files){
		List<FileVO> flist = new ArrayList<>();
		//파일 경로, fvo 세팅, 파일저장, uuid 등
		//날짜를 폴더로 만들어서 생성하고 관리한다.
		LocalDate date = LocalDate.now();	// 오늘 날짜 생성
		String today = date.toString();	// 2023-10-24
		today = today.replace("-", File.separator);	// 2023\10\24
		
		File folders = new File(UP_DIR, today);
		
		// 존재하지않으면 ? 폴더 생성
		if(!folders.exists()) {
			folders.mkdirs();	//하위폴더 전체 생성
		}
		
		//files 객체에 대한 설정
		for (MultipartFile file : files) {	// 첨부파일 중 1개씩 for문 처리
			FileVO fvo = new FileVO();
			fvo.setSaveDir(today);
			fvo.setFileSize(file.getSize());
			String originalFileName = file.getOriginalFilename();
			String fileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator)+1);
			fvo.setFileName(fileName);
			
			UUID uuid = UUID.randomUUID();
			//uuid는 객체이기 때문에 toString으로 형변환 필수
			fvo.setUuid(uuid.toString());
			/* 기본 FileVO 생성 완료 */
			
			//하단부터 디스크에 저장할 파일 객체 생성
			//파일 이름 uuid + fileName | 썸네일 uuid_th_fileName
			String fullFileName = uuid.toString() + "_" + fileName;
			File storeFile = new File(folders, fullFileName);
			
			//file 객체가 저장이 되려면 첫 경로부터 다 설정이 되어있어야한다.
			// D:\\_myweb\\_java\\fileupload\\2023\\10\\24\\uuid_fileName.jpg
			
			try {
				file.transferTo(storeFile);	//저장
				//썸네일 생성 => 이미지 파일만 썸네일 생성
				//이미지 파일인 확인
				if(isImageFile(storeFile)) {
					fvo.setFileType(1);
					//썸네일 생성
					File thumbNail = new File(folders, 
							uuid.toString()+"_th_"+fileName);
					Thumbnails.of(storeFile).size(75, 75).toFile(thumbNail);;
					
				}
				
				
			} catch (Exception e) {
				log.debug(" >>>>>> file 생성 오류");
				e.printStackTrace();
			}
			
			//flist에 fvo 추가
			flist.add(fvo);
		}
		
		
		
		return flist;
	}
	
	private boolean isImageFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile);	//image/jpg
		
		return mimeType.startsWith("image")? true : false;
		
	}
	
}
