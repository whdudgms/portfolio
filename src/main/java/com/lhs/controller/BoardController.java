package com.lhs.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

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
		
		
		params.put("typeSeq", "2");
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
			@RequestParam HashMap<String, Object> params, 
			MultipartHttpServletRequest mReq,HttpSession session) {
		System.out.println("세션에 MemberId");
		System.out.println (session.getAttribute("memeberId"));
		params.put("memberId","whdudgms1234");
		System.out.println(mReq);
		if(!params.containsKey("typeSeq")) {
			params.put("typeSeq", this.typeSeq);
		}
		bService.write(params, mReq.getFiles("attFiles"));

		return null;
	}

	@RequestMapping("/board/read.do")
	public ModelAndView read(@RequestParam HashMap<String, Object> params) {
		if(!params.containsKey("typeSeq")) {
			params.put("typeSeq", this.typeSeq);
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/board/read");
		return mv;
	}	


	//수정  페이지로 	
	@RequestMapping("/board/goToUpdate.do")
	public ModelAndView goToUpdate(@RequestParam HashMap<String, Object> params, HttpSession session) {
		ModelAndView mv = new ModelAndView();

		if(!params.containsKey("typeSeq")) {
			params.put("typeSeq", this.typeSeq);
		}
		
		return mv;

	}

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

		if(!params.containsKey("typeSeq")) {
			params.put("typeSeq", this.typeSeq);
		}
		return null; // 비동기: map return 
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
