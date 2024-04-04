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
public class BoardController {

	private final BoardService bService;
	private final AttFileService attFileService;

	private String typeSeq = "2";  // 이코드가 꼭 필요할까?? 
	

	@RequestMapping("/board/list.do")
	public ModelAndView goList(@RequestParam HashMap<String, Object> params){
		ModelAndView mv = new ModelAndView();
		PageInfo pageInfo = new PageInfo();
		mv.setViewName("board/list");
		// 게시물 목록을 가져오기 위한 요소들  
		// 게시물 타입 
		// 시작 게시물 번호 = (현재페이지 -1) * 10  현재페이지를 전달받아서 구함 
		// 페이지 사이즈 기본값 10 사용  
		//  
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
		System.out.println(" Board  goList  params:");
		System.out.println(params);
		ArrayList<BoardDto> boardlist= bService.list(params);
		mv.addObject("Boardlist", boardlist);
		
		// 게시믈에서 페이지 네이션을 표현하기 위한 요소들 
		// 총 페이지, 현재 페이지 
		//  
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
		pageInfo.setStartNavi(((pageInfo.getCurrentPage() -1) / pageInfo.getPageNaviSize())*pageInfo.getPageNaviSize() +1  );
		pageInfo.setMaxNavi(pageInfo.getTotalBoard() % pageInfo.getPageSize() ==0? pageInfo.getTotalBoard() / pageInfo.getPageSize() :  pageInfo.getTotalBoard() / pageInfo.getPageSize()+1);      
		System.out.println("list view로 전달되는 내용 ");
		System.out.println("boardlist = ");
	
		System.out.println();
		System.out.println("pageInfo = ");
		System.out.println(pageInfo);
		mv.addObject("pageInfo", pageInfo);
		System.out.println();
		
		return mv;
	} 
	


	@RequestMapping("/test.do")
	public ModelAndView test() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("test");
		return mv;
	}

	//글쓰기 페이지로 	
	@RequestMapping("/board/goToWrite.do")
	public ModelAndView goToWrite(@RequestParam HashMap<String, Object> params) {
		if(!params.containsKey("typeSeq")) {
			params.put("typeSeq", this.typeSeq);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/board/write");
		return mv;
	}



	@RequestMapping("/board/read.do")
	public ModelAndView read(@RequestParam HashMap<String, Object> params) {
		System.out.println("컨트럴러 =     \"/board/read.do\"");
		System.out.println(params);
		if(!params.containsKey("typeSeq")) {
			params.put("typeSeq", this.typeSeq);
		}
		ModelAndView mv = new ModelAndView();
	    BoardDto boardDto = bService.read(params);
	    List<BoardAttach> attFiles;
	    attFiles = attFileService.readAttFiles(params);
	    
	    System.out.println(" bService.read(params) ");
	    System.out.println(boardDto);
		mv.addObject(boardDto);
		mv.addObject("currentPage",params.get("currentPage"));
		mv.addObject("attFiles",attFiles);
		mv.setViewName("/board/read");
		return mv;
	}	


	//수정  페이지로 	
	@RequestMapping("/board/goToUpdate.do")
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
		mv.setViewName("/board/update");
		return mv;

	}
 



}