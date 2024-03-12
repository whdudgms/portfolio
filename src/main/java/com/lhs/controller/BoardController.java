package com.lhs.controller;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
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
public class BoardController {

	@Autowired BoardService bService;
	@Autowired AttFileService attFileService;
	@Autowired FileUtil fileUtil;

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
		
		pageInfo.setTotalBoard(bService.getTotalArticleCnt((Integer.parseInt((String)params.get("typeSeq")))));
		//pageInfo.setTotalPageSize(pageInfo.get)
		//112 111 
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
	
	@RequestMapping("/board/download.do")
	@ResponseBody
	public byte[] downloadFile(@RequestParam int fileIdx, HttpServletResponse response) throws UnsupportedEncodingException {
		
		
		
		// fileIdx를 가지고 첨부파일 정보 가져오기 
		System.out.println("파일 번호 값을 출력합니다 "
				+ "fileIdx  =  "
				);
		System.out.println(fileIdx);
		
		
		
		
		 // 첨부파일 정보 조회
        HashMap<String, Object> fileInfo = attFileService.readAttFileByPk(fileIdx);
        if (fileInfo == null) {
            // 파일 정보가 없는 경우, 적절한 HTTP 상태 코드 반환
            return null;
        }
		
		response.setContentType((String) fileInfo.get("file_type"));
		response.setHeader("Content-Disposition", "attachment; filename=\"" +  URLEncoder.encode((String) fileInfo.get("file_name") , "UTF-8").replaceAll("\\+", "%20")+ "\"");
		
		 
		
		return	fileUtil.readFile(fileInfo);
		

		
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

	@RequestMapping("/board/write.do")
	@ResponseBody
	public HashMap<String, Object> write(
			BoardDto boardDto, 
			MultipartHttpServletRequest mReq,HttpSession session) {
		

//		boardDto.put("memberId","whdudgms1234");
		System.out.println(mReq);
		if(Objects.nonNull(boardDto.getTypeSeq())) {
			boardDto.setTypeSeq(Integer.parseInt(this.typeSeq));
		}
		
		int cnt = bService.write(boardDto, mReq.getFiles("attFiles"));

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cnt", cnt);
		map.put("msg", cnt==1?"게시물 삽입 완료!!!":"게시물 삽입 실패!!!");
		map.put("nextPage", cnt==1?"/board/list.do" : "/board/list.do");
		return map;
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
//	int cnt = bService.delete(params);
//	HashMap<String, Object> map = new HashMap<String, Object>();
//
//	map.put("cnt", cnt);
//	map.put("msg", cnt==1?"게시물 업데이트 완료!!!":"게시물 업데이트 실패!!!");
//	map.put("nextPage", cnt==1?"/board/ .do" : "/board/list.do");

	@RequestMapping("/board/update.do")
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

	@RequestMapping("/board/delete.do")
	@ResponseBody
	public HashMap<String, Object> delete(@RequestParam HashMap<String, Object> params, HttpSession session) {
		System.out.println("controller delete메서드." );
		System.out.println(params);

		if(!params.containsKey("typeSeq")) {
			params.put("typeSeq", this.typeSeq);
		}
		int cnt = bService.delete(params);
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("cnt", cnt);
		map.put("msg", cnt==1?"게시물 삭제 완료!!!":"게시물 삭제 실패!!!");
		map.put("nextPage", cnt==1?"/board/list.do" : "/board/list.do");
		
		return map; // 비동기: map return 
	}

	@RequestMapping("/board/deleteAttFile.do")
	@ResponseBody
	public HashMap<String, Object> deleteAttFile(@RequestParam HashMap<String, Object> params) {
		
		if(!params.containsKey("typeSeq")) {
			params.put("typeSeq", this.typeSeq);
		}
		boolean cnt = bService.deleteAttFile(params) ;
				
		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("cnt", cnt);
		map.put("msg", cnt?"첨부파일 완료!!!":"첨부파일 실패!!!");
		map.put("nextPage", cnt?"/board/list.do" : "/board/list.do");
		
		return map;
	} 



}