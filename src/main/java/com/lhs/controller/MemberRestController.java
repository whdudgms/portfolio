package com.lhs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RestController
public class MemberRestController {
	
	@Autowired
	MemberService mService;
	

	@Value("#{config['site.context.path']}")
	String ctx;
	

	@RequestMapping("/member/checkId.do")
	@ResponseBody
	public HashMap<String, Object> checkId(@RequestParam HashMap<String, String> params){
		int cnt = 0;
		cnt = mService.checkId(params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("cnt", cnt);
		map.put("msg", cnt==1? "중복된 ID 입니다.":"");
		return map;
	}
	
	@RequestMapping("/member/join.do")
	@ResponseBody
	public HashMap<String, Object> join(@RequestParam HashMap<String, String> params){		
		System.out.println(params);
		HashMap<String, Object> map = new HashMap<String, Object>();
		int cnt = 0;
		cnt = mService.join(params);
		map.put("cnt", cnt);
		map.put("msg", cnt==1?"회원 가입 완료!!!":"회원 가입 실패!!!");
		map.put("nextPage", cnt==1?"/member/goLoginPage.do" : "/member/goRegisterPage.do");
		return map;
	}

	// 이메일주소로 인증번호 보내는 메서드 
	@RequestMapping("/member/sendemail.do")
	@ResponseBody
	public HashMap<String, Object> sendEmail(@RequestParam HashMap<String, String> params, HttpSession session){
		HashMap<String, Object> map = new HashMap<String, Object>();
	    // 인증번호 요청온다 인증번호 만드는 메서드 호출했다고치고 
		mService.getVNum(params.get("email"),session);
	    return map;
	}
	
	// 입력 받은 이메일 검증하고 검증결과에 따라 Member Type을 바꾸는 메서드 
		@RequestMapping("/member/VNumCheck.do")
		@ResponseBody
		public HashMap<String, Object> checkVNum(@RequestParam HashMap<String, String> params, HttpSession session){
			HashMap<String, Object> map = new HashMap<String, Object>();
			System.out.println(params);
			
			try {
				if(mService.checkVNum(params,session)) {
					
				System.out.println("인증 성공!!!mService.checkVNum(params,session) 인증 성공!!!");
				System.out.println("memeber typeSeq  5로 바꿔야함.");
				// 5로 바뀌면 세션도 바뀌어야함 member정보 가져와서 세로 업데이트 ㄱ 
				map.put("nextPage", "/index.do");
				}else{
					System.out.println("인증 실패 인증실패..");
					map.put("msg", "CheckFail");
					map.put("nextPage", "/member/email.do");
				}
			}catch(Exception e) {
				e.printStackTrace();
				log.error("", e);
				map.put("nextPage", "/member/email.do");
				map.put("msg", e.getMessage());
			}
			return map;
		}
	
	
		@RequestMapping("/member/login.do")
		@ResponseBody
		public HashMap<String, Object> login(@RequestParam HashMap<String, String> params, HttpSession session){
			HashMap<String, Object> map = new HashMap<String, Object>();
			System.out.println("\"/member/login.do\")의 시작.");
			System.out.println("\"/member/login.do\")의 시작.");
			System.out.println("\"/member/login.do\")의 시작.");
			
			try {
				if(mService.login(params,session)) {
				System.out.println(session.getAttribute("memberId"));
				System.out.println(session.getAttribute("typeSep"));
				System.out.println(session.getAttribute("memberNick"));
				map.put("nextPage", "/index.do");
				}else{
					map.put("msg", "LoginFail");
					map.put("nextPage", "/member/goLoginPage.do");
				}
			}catch(Exception e) {
				e.printStackTrace();
				log.error("", e);
				map.put("nextPage", "/member/gologinPage.do");
				map.put("msg", e.getMessage());
			}
			return map;
		}
	
	
		@RequestMapping("/admin/memberList.do")
		@ResponseBody //비동기식 호출
		public HashMap<String, Object> memberList(@RequestParam HashMap<String, Object> params) {
			// 페이징
			// 모든 회원 가져오기
			List<HashMap<String,Object>> memberList = new ArrayList<HashMap<String,Object>>();
			
			//go to  JSP 

			HashMap<String,Object> result = new HashMap<String,Object>();
			//정해진  키 이름으로 넘겨주기.. 
			result.put("page", params.get("page")); //현재 페이지 
			result.put("rows", memberList); // 불러온 회원목록 
			result.put("total", 1);// 전체 페이지 
			result.put("records", 10); //전체 회원수 

			return result;

		}

		@RequestMapping("/member/delMember.do")
		@ResponseBody
		public HashMap<String,Object> delMember(@RequestParam HashMap<String, Object> params){
			HashMap<String, Object> map = new HashMap<String, Object>();
			int result = 0;
			map.put("msg", (result == 1) ? "삭제되었습니다.":"삭제 실패!");
			map.put("result",result);
			return map;

		}

}
