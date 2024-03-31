package com.lhs.controller;

import java.util.HashMap;

import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lhs.service.BoardService;

@Controller
@RequiredArgsConstructor
public class IndexController {


	private final BoardService bService;

	Logger logger = Logger.getLogger(MemberController.class);

	@RequestMapping("/authNumMail.do")
	public ModelAndView authNumMail(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("member/authNumMail");
		return mv;
	}
	
	@RequestMapping("/sendAuthMail.do")
	public ModelAndView sendAuthMail(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("member/sendAuthMail");
		return mv;
	}
	
	@RequestMapping("/emailAuth.do")
	public ModelAndView emailAuth(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("member/emailAuth");
		return mv;
	}
	
	@RequestMapping("/index.do")
	public ModelAndView index(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("index");
		return mv;
	}

	@RequestMapping("/profile.do")
	public ModelAndView profile(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("profile");
		return mv;
	}

	@RequestMapping("/home.do")
	public ModelAndView home(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("home");
		return mv;
	}

	@RequestMapping("/admin.do")
	public ModelAndView admin(@RequestParam HashMap<String, Object> params) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("/admin/memberList");
		return mv;
	}


	@RequestMapping("/devNotes.do")
	public ModelAndView tables(@RequestParam HashMap<String, String> params) {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("devNotes");

		return mv;
	}
}
