package com.lhs.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	public ArrayList<BoardDto> list(HashMap<String, Object> params) {
		return bDao.list(params);
	}

	@Override
	public int getTotalArticleCnt(Integer typeSeq) {
		return bDao.getTotalArticleCnt(typeSeq);
	}

	@Transactional
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
			HashMap<String,Object> params = new HashMap<String,Object>();
			params.put("boardSeq", boardDto.getBoardSeq());
			params.put("hasFile", "Y");
			bDao.updateHasFile(params);
		}
		
		
		System.out.println("result of boardDto : "  + boardDto);
		return write;
	}

	//글 조회 
	@Override
	public BoardDto read(HashMap<String, Object> params) {
		
		return bDao.read(params);
	}

	@Override
	public int update(BoardDto boardDto, List<MultipartFile> mFiles) {
		if(boardDto.getHasFile().equals("Y")) { // 첨부파일 존재시 			
			// 기존 첨부파일 삭제 
				// 기존 첨부파일 검색 받아오기 >>  행 삭제, 실물 데이터 삭
			// 전달 받은 첨부 파일 넣기 
				// 기존 
		}	
		// 글 수정 dao 
		return bDao.update(boardDto);
	}

	@Override
	public int delete(HashMap<String, Object> params) {
		System.out.println("board  delete메서드를 호출했습니다. !!!!!!!!!!!!!!!!!!!!!");
		System.out.println("params 의 값은 !!!!");
		
		
		System.out.println(params);
		if(Objects.nonNull(params.get("hasFile")) && params.get("hasFile").equals("Y")) { // 첨부파일 있으면 		
			
			
			List<BoardAttach> attlist = attFileDao.readAttFiles(params);
			
			for(int i = 0; i < attlist.size(); i++) {
				fileUtil.deleteFile(attlist.get(i).getFakeFilename());
				System.out.println("첨부 파일도 삭제했습니다. 번째 : " +i + "입니다!" );
			}
			
			if( attFileDao.deleteAttFileByBoard(params)>= 1)
				System.out.println("첨부 파일도 삭제했습니");
			//fileUtil.deleteFile("");
		}
		
		return bDao.delete(params);
	}

	@Override
	public boolean deleteAttFile(HashMap<String, Object> params) {
		boolean result = false;		
		return result;
	}
}
