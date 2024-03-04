package com.lhs.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lhs.dao.AttFileDao;
import com.lhs.dao.BoardDao;
import com.lhs.dto.BoardAttach;
import com.lhs.dto.BoardDto;
import com.lhs.service.BoardService;
import com.lhs.util.FileUtil;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired BoardDao bDao;
	@Autowired AttFileDao attFileDao;
	@Autowired FileUtil fileUtil;
	
	
	@Value("#{config['project.file.upload.location']}")
	private String saveLocation;
	
	
	@Override
	public ArrayList<BoardDto> list(HashMap<String, String> params) {
		return bDao.list(params);
	}

	@Override
	public int getTotalArticleCnt(HashMap<String, String> params) {
		return bDao.getTotalArticleCnt(params);
	}

	@Override
	public int write(BoardDto boardDto, List<MultipartFile> mFiles) {	
		
		//1. board DB에 글 정보등록 + hasFile
		int write = bDao.write(boardDto);
		System.out.println();
		System.out.println("BoardDto boardDto의 값입니다.");
		System.out.println(boardDto);
		System.out.println();
		System.out.println("write의 값입니다.");
		System.out.println(write);
		System.out.println();
		for(MultipartFile mFile: mFiles) {
			HashMap<String, Object> params1 =  new HashMap<String, Object>();
			if(mFile.getSize() == 0 ||mFile.getOriginalFilename()== "" )
				continue;
			BoardAttach boardAttach = new BoardAttach();
			boardAttach.setBoardSeq(boardDto.getBoardSeq());  // boardSeq
			boardAttach.setTypeSeq(boardDto.getTypeSeq());	   //
			boardAttach.setFileType(mFile.getContentType());   // file_type
			boardAttach.setFileName(mFile.getOriginalFilename()); //file_name
			boardAttach.setFileSize(mFile.getSize());  // file_size
			System.out.println("--");
			boardAttach.setSaveLoc( saveLocation);
			// to-do : smart_123.pdf -> (UUID).pdf
			// to-do : smart_123.456.pdf -> (UUID).PDF
			// 
			//확장자 떨어져 나간다 
			String fakeName = UUID.randomUUID().toString().replaceAll("-", "");
			boardAttach.setFakeFilename(fakeName);
			
			try {
				fileUtil.copyFile(mFile, fakeName);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("값이 모두 넣어진 boardAttach");
			System.out.println(boardAttach);
			attFileDao.addAttFile(boardAttach);
			  
		//file seq  board seq =write

		}
		
		System.out.println("result of boardDto : "  + boardDto);
		return 0;
	}

	//글 조회 
	@Override
	public HashMap<String, Object> read(HashMap<String, Object> params) {
		
		return bDao.read(params);
	}

	@Override
	public int update(HashMap<String, Object> params, List<MultipartFile> mFiles) {
		if(params.get("hasFile").equals("Y")) { // 첨부파일 존재시 			
			// 파일 처리
		}	
		// 글 수정 dao 
		return bDao.update(params);
	}

	@Override
	public int delete(HashMap<String, Object> params) {
		if(params.get("hasFile").equals("Y")) { // 첨부파일 있으면 		
			 // 파일 처리
		}
		return bDao.delete(params);
	}

	@Override
	public boolean deleteAttFile(HashMap<String, Object> params) {
		boolean result = false;		
		return result;
	}
}
