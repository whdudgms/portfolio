package com.lhs.controller;



import javax.servlet.http.HttpSession;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



import com.lhs.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor

public class MemberController {


	private final MemberService mService;

	@Value("#{config['site.context.path']}")
	String ctx;

	@RequestMapping("/member/goLoginPage.do")
	public String goLogin() {
		return "member/login";
	}

	@RequestMapping("/member/goRegisterPage.do")
	public String goRegisterPage() {
		return "member/register";
	}


	@RequestMapping("/member/logout.do")
	public String logout(HttpSession session){
	    session.invalidate();
	    return "redirect:/index.do";
	}
	

	//이메일 페이지로 이동!
	@RequestMapping("/member/email.do")
	public String Email( HttpSession session){

	    session.setAttribute("email", mService.getEamil((String)session.getAttribute("memberId")));
		
	    return "member/email";
	}
	
	

}
