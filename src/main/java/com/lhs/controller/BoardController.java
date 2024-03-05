package com.lhs.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.lhs.dto.BoardDto;
import com.lhs.service.AttFileService;
import com.lhs.service.BoardService;
import com.lhs.util.FileUtil;

@Controller
public class BoardController {

	@Autowired BoardService bService;
	@Autowired AttFileService attFileService;
	@Autowired FileUtil fileUtil;

	private String typeSeq = "2";  // 이코드가 꼭 필요할까?? 
	

	@RequestMapping("/board/list.do")
	public ModelAndView goLogin(@RequestParam HashMap<String, String> params){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/list");
		
		
		params.put("typeSeq", this.typeSeq);
		ArrayList<BoardDto> boardlist= bService.list(params);
		mv.addObject("Boardlist", boardlist);
		System.out.println();
		System.out.println();

		
		return mv;
	} 
	
	@RequestMapping("/board/download.do")
	@ResponseBody
	public byte[] downloadFile(@RequestParam int fileIdx, HttpServletResponse response) {
		
		HashMap<String,Object> fileInfo = null;
		
		
		return null;
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
		
		bService.write(boardDto, mReq.getFiles("attFiles"));

		return null;
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
	    System.out.println(" bService.read(params) ");
	    System.out.println(boardDto);
		mv.addObject(boardDto);
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
		mv.setViewName("board/update");
		return mv;

	}
//	int cnt = bService.delete(params);
//	HashMap<String, Object> map = new HashMap<String, Object>();
//
//	map.put("cnt", cnt);
//	map.put("msg", cnt==1?"게시물 업데이트 완료!!!":"게시물 업데이트 실패!!!");
//	map.put("nextPage", cnt==1?"/board/list.do" : "/board/list.do");

	@RequestMapping("/board/update.do")
	@ResponseBody // !!!!!!!!!!!! 비동기 응답 
	public HashMap<String, Object> update(@RequestParam HashMap<String,Object> params, 
			MultipartHttpServletRequest mReq) {

		if(!params.containsKey("typeSeq")) {
			params.put("typeSeq", this.typeSeq);
		}

		return null;
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
		return null;
	} 



}
