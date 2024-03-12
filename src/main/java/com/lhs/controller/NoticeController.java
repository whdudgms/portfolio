package com.lhs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.lhs.dto.BoardAttach;
import com.lhs.dto.BoardDto;
import com.lhs.service.AttFileService;
import com.lhs.service.BoardService;
import com.lhs.util.FileUtil;
import com.lhs.util.PageInfo;

@Controller
public class NoticeController {

	@Autowired BoardService bService;
	@Autowired AttFileService attFileService;
	@Autowired FileUtil fileUtil;

	private String typeSeq = "1";
	
	

	@RequestMapping("/notice/list.do")
	public ModelAndView goLogin(@RequestParam HashMap<String, Object> params,HttpSession session){
		ModelAndView mv = new ModelAndView();
		
		PageInfo pageInfo = new PageInfo();
		
		if(params.get("currentPage")==null || params.get("currentPage") == "") {
			params.put("startBoard", (pageInfo.getCurrentPage() - 1 ) * 10 ); // 전달값 없으면
			}else {
			params.put("startBoard", (Integer.parseInt( (String) params.get("currentPage")) - 1) * 10 ); // 전달값 있으면 
			pageInfo.setCurrentPage(Integer.parseInt( (String) params.get("currentPage")));
			}
			params.put("pageSize", pageInfo.getPageSize());
		
		if(!params.containsKey("typeSeq")) {
			params.put("typeSeq", this.typeSeq);
		}
		
		params.put("typeSeq", "1");
		ArrayList<BoardDto> boardlist= bService.list(params);
		pageInfo.setTotalBoard(bService.getTotalArticleCnt((Integer.parseInt((String)params.get("typeSeq")))));
		//pageInfo.setTotalPageSize(pageInfo.get)
		//112 111 
		pageInfo.setStartNavi(((pageInfo.getCurrentPage() -1) / pageInfo.getPageNaviSize())*pageInfo.getPageNaviSize() +1  );
		pageInfo.setMaxNavi(pageInfo.getTotalBoard() % pageInfo.getPageSize() ==0? pageInfo.getTotalBoard() / pageInfo.getPageSize() :  pageInfo.getTotalBoard() / pageInfo.getPageSize()+1);      
		mv.addObject("Boardlist", boardlist);
		System.out.println();
		System.out.println(mv);
		System.out.println();
		mv.addObject("pageInfo", pageInfo);
		mv.setViewName("notice/list");
		System.out.println("NoticeController에 session memmber타입SEQ");
		System.out.println(session.getAttribute("typeSeq"));

		return mv;
	}


	//글쓰기 페이지로 	
	@RequestMapping("/notice/goToWrite.do")
	public ModelAndView goToWrite(@RequestParam HashMap<String, Object> params) {
		if(!params.containsKey("typeSeq")) {
			params.put("typeSeq", this.typeSeq);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/notice/write");
		return mv;
	}

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

	@RequestMapping("/notice/read.do")
	public ModelAndView read(@RequestParam HashMap<String, Object> params) {
		
		
		if(!params.containsKey("typeSeq")) {
			params.put("typeSeq", this.typeSeq);
		}
		
		
		
		ModelAndView mv = new ModelAndView();		
		BoardDto boardDto = bService.read(params);
	    List<BoardAttach> attFiles;
	    attFiles = attFileService.readAttFiles(params);

		mv.addObject("currentPage", params.get("currentPage"));
		mv.addObject("boardDto", boardDto);
		mv.addObject("attFiles",attFiles);
		mv.setViewName("/notice/read");


		return mv;
	}	

	//수정  페이지로 	
	@RequestMapping("/notice/goToUpdate.do")
	public ModelAndView goToUpdate(@RequestParam HashMap<String, Object> params, HttpSession session) {
		System.out.println("controller goToUpdate메서드의 params" );
		System.out.println(params);
		ModelAndView mv = new ModelAndView();
		if(!params.containsKey("typeSeq")) {
			params.put("typeSeq", this.typeSeq);
		}
		BoardDto boardDto  = bService.read(params);
		System.out.println("controller goToUpdate메서드의  bService.read(params)" );
		System.out.println(boardDto);
		mv.addObject(boardDto);
		 List<BoardAttach> attFiles;
		    attFiles = attFileService.readAttFiles(params);
		mv.addObject("attFiles",attFiles);
		mv.addObject("currentPage",params.get("currentPage"));
		mv.setViewName("/notice/update");
		return mv;

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

}
