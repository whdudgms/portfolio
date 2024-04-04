package com.lhs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

import com.lhs.dto.BoardAttach;
import com.lhs.dto.BoardDto;
import com.lhs.service.AttFileService;
import com.lhs.service.BoardService;
import com.lhs.util.PageInfo;

@Controller
@RequiredArgsConstructor
public class NoticeController {

	private final  BoardService bService;
	private final AttFileService attFileService;

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
		
		
		if(Objects.nonNull(params.get("searchType"))  && Objects.nonNull(params.get("searchWord"))
				&&!("".equals((String)params.get("searchWord")))
				) {
			pageInfo.setTotalBoard(bService.searchGetTotalArticleCnt((Integer.parseInt((String)params.get("typeSeq")))
					,(String)params.get("searchType"),(String)params.get("searchWord")));
			pageInfo.setSearchWord((String)params.get("searchWord"));
			pageInfo.setSearchType((String)params.get("searchType"));

		}else {
			pageInfo.setTotalBoard(bService.getTotalArticleCnt((Integer.parseInt((String)params.get("typeSeq")))));

		}
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


}
