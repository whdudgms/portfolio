package com.lhs.rest.controller;

import java.util.HashMap;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lhs.dto.BoardDto;
import com.lhs.service.AttFileService;
import com.lhs.service.BoardService;
import com.lhs.util.FileUtil;

@RestController
public class NoticeRestController {

	@Autowired BoardService bService;
	@Autowired AttFileService attFileService;
	@Autowired FileUtil fileUtil;

	private String typeSeq = "1";
	
	
	
	@RequestMapping("/notice/write.do")
	@ResponseBody
	public HashMap<String, Object> write(
			BoardDto boardDto, 
			MultipartHttpServletRequest mReq,HttpSession session) {		
		
		System.out.println(mReq);
		if(Objects.nonNull(boardDto.getTypeSeq())) {
			boardDto.setTypeSeq(Integer.parseInt(this.typeSeq));
		}

		HashMap<String, Object> map = new HashMap<String, Object>();
		int result = bService.write(boardDto, mReq.getFiles("attFiles"));
		String msg = (result == 1) ? "성공" : "실패";

		map.put("result", result);
		map.put("msg", msg);
		map.put("boardSeq", boardDto.getBoardSeq());
		return map;
	}
	
	
	@RequestMapping("/notice/delete.do")
	@ResponseBody
	public HashMap<String, Object> delete(@RequestParam HashMap<String, Object> params, HttpSession session) {

		if(!params.containsKey("typeSeq")) {
			params.put("typeSeq", this.typeSeq);
		}
		//비동기 리턴해줄 맵 생성
		HashMap<String, Object> map = new HashMap<String, Object>();

		if(session.getAttribute("memberId") != null) { // 세션에 값있을 : 로긴상태일때만 
			//1. 글 정보 검사 - has_file 추출.  
			BoardDto boardInfo = bService.read(params);
			params.put("hasFile", boardInfo.getHasFile());
			//삭제 쿼리 호출 
			int result = 0;

			//쿼리결과따라 msg 값 대입 
			String msg = (result == 1 ? "성공" : "실패");

			//필요정보 map에 put 
			map.put("msg", msg);
			map.put("result", result);
			map.put("typeSeq", params.get("typeSeq"));

		}else {
			// 로그인 세션 풀렸을때
		}
		return map; // 비동기: map return 
	}

	
	
	@RequestMapping("/notice/update.do")
	@ResponseBody // !!!!!!!!!!!! 비동기 응답 
	public HashMap<String, Object> update(BoardDto boardDto, 
			MultipartHttpServletRequest mReq) {

		if(Objects.isNull( boardDto.getTypeSeq())) {
			boardDto.setTypeSeq(Integer.parseInt (this.typeSeq ));
		}
		System.out.println("/board/update.do에서  전달받은 파라미터 출력  ");
		System.out.println("boardDto  :"+boardDto);
		int cnt = bService.update(boardDto, mReq.getFiles("attFiles"));
		HashMap<String, Object> map = new HashMap<String, Object>();
	
		map.put("cnt", cnt);
		map.put("msg", cnt==1?"게시물 업데이트 완료!!!":"게시물 업데이트 실패!!!");
		map.put("nextPage", cnt==1?"/board/list.do" : "/board/list.do");
		return map;
	}

	
}
