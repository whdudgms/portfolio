

package com.lhs.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lhs.dto.MemberDto;
import com.lhs.exception.PasswordMissMatchException;
import com.lhs.exception.UserNotFoundException;
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
	

	@GetMapping("/memberId")
	@ResponseBody
	public ResponseEntity<?> checkId(String memberId){
		HashMap<String, String> params =  new HashMap<String,String>();
		params.put("memberId", memberId);
		int cnt = 0;
		cnt = mService.checkId(params);
		
		return ResponseEntity.ok().body(Map.of("cnt",cnt,"message",cnt==1? "중복된 ID 입니다.":""));
	}
	
	@PostMapping("/member")
	@ResponseBody
	public ResponseEntity<?> join(
			String memberId, 
			String memberPw,
			String againPw,
			String memberName,
			String memberNick,
			String email
//			MemberDto memberDto
			){
		MemberDto memberDto =  new MemberDto();
		memberDto.setMemberId(memberId);
		memberDto.setMemberPw(memberPw);
		memberDto.setAgainPw(againPw);
		memberDto.setMemberName(memberName);
		memberDto.setMemberNick(memberNick);
		memberDto.setEmail(email);
		
		System.out.println("\"/member/login.do\")의 시작.");
		System.out.println("MemberController의 join메서드에 전달받은 파라미터 출력");
		System.out.println("memberDto = "+memberDto);
		int cnt = 0;
		cnt = mService.join(memberDto);
	
		
		return ResponseEntity.ok().body(Map.of("cnt",cnt,"nextPage",cnt==1?"/member/goLoginPage.do" : "/member/goRegisterPage.do",
				"message",cnt==1?"회원 가입 완료!!!":"회원 가입 실패!!!"));
	}
	
	
	

	// 이메일주소로 인증번호 보내는 메서드 
	@GetMapping("/authEmail")
	@ResponseBody
	public ResponseEntity<?> sendEmail(String email, HttpSession session){
	    return ResponseEntity.ok().body(Map.of(
	    		"message",mService.getVNum(email,session).equals("성공")? "이메일 잘 전송했어요.": "이메일 전송 실패"));
	}
	
	
	
	
	// 입력 받은 이메일 검증하고 검증결과에 따라 Member Type을 바꾸는 메서드 
		@PostMapping("/member/VNum")
		@ResponseBody
		public ResponseEntity<?> checkVNum(String memberId, String email, String VNum, HttpSession session){
			
			HashMap params = new HashMap<String,String>();
			params.put("memberId", memberId);
			params.put("eamil", email);
			params.put("VNum", VNum);
			
			
			System.out.println(params);
			
				if(mService.checkVNum(params,session)) {
					System.out.println("인증 성공 인증성공 !!!");
					return ResponseEntity.ok().body(Map.of("nextPage","/index.do" ));
				}else{
					System.out.println("인증 실패 인증실패..");
					return ResponseEntity.ok().body(Map.of("message","CheckFail","nextPage","/member/email.do"));
				}
		}
	
	
		@PostMapping("/sessions")
		@ResponseBody
		public ResponseEntity<?>  login( String memberId,String memberPw, HttpSession session) throws UserNotFoundException, PasswordMissMatchException{
			
			
			MemberDto memberDto = new MemberDto();
			memberDto.setMemberId(memberId);
			memberDto.setMemberPw(memberPw);
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			System.out.println("\"/member/login.do\")의 시작.");
			System.out.println("MemberController의 login메서드에 전달받은 파라미터 출력");
			System.out.println("memberDto = "+memberDto);
			
			
				if(mService.login(memberDto,session)) {
				System.out.println(".");
				System.out.println(memberDto);
				System.out.println("로그인 성공 메서드.");
			
				System.out.println("세션에 저장된 요소들 출력.");
				System.out.println(session.getAttribute("memberId"));
				System.out.println(session.getAttribute("memberNick"));
				System.out.println(session.getAttribute("memberIdx"));
				System.out.println(session.getAttribute("typeSeq"));
				return ResponseEntity.ok().body(Map.of("nextPage","/index.do"));
				}else{
				return ResponseEntity.ok().body(Map.of("nextPage","/member/goLoginPage.do","message","LoginFail"));
				}
		}
	
	
//		@RequestMapping("/admin/memberList.do")
//		@ResponseBody //비동기식 호출
//		public HashMap<String, Object> memberList(@RequestParam HashMap<String, Object> params) {
//			// 페이징
//			// 모든 회원 가져오기
//			List<HashMap<String,Object>> memberList = new ArrayList<HashMap<String,Object>>();
//			
//			//go to  JSP 
//
//			HashMap<String,Object> result = new HashMap<String,Object>();
//			//정해진  키 이름으로 넘겨주기.. 
//			result.put("page", params.get("page")); //현재 페이지 
//			result.put("rows", memberList); // 불러온 회원목록 
//			result.put("total", 1);// 전체 페이지 
//			result.put("records", 10); //전체 회원수 
//
//			return result;
//
//		}
//
//		@RequestMapping("/member/delMember.do")
//		@ResponseBody
//		public HashMap<String,Object> delMember(@RequestParam HashMap<String, Object> params){
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			int result = 0;
//			map.put("msg", (result == 1) ? "삭제되었습니다.":"삭제 실패!");
//			map.put("result",result);
//			return map;
//
//		}

}
