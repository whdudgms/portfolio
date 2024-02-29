package com.lhs.service.impl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.dao.MemberDao;
import com.lhs.dto.MemberDto;
import com.lhs.exception.PasswordMissMatchException;
import com.lhs.exception.UserNotFoundException;
import com.lhs.service.MemberService;
import com.lhs.util.AES256Util;
import com.lhs.util.EmailUtil;
import com.lhs.util.RandomNum;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired MemberDao mDao; 
	
	@Autowired
	EmailUtil emailUtil;
	
	@Autowired
	AES256Util encoder;
	
	
	

	@Override
	public ArrayList<HashMap<String, Object>> memberList(HashMap<String, Object> params) {
		return mDao.memberList(params);
	}
	
	//총회원수
	@Override
	public int totalMemberCnt(HashMap<String, Object> params) {
		return mDao.totalMemberCnt(params);
	}

	
	
	
	@Override
	public int join(HashMap<String, String> params) {
		//아이디  검증하기 중복x 
		if(Objects.nonNull(mDao.getMemberById(params))|| params.get("memberPw").equals(params.get("pwAgain"))) {
			System.out.println("중복또는 비밀번호 체크에서 오류.");
			return 0;
		}
		try {
			
			params.put("memberPw", encoder.aesEncode(params.get("memberPw")));
			
		} catch (Exception e) {
		

			e.printStackTrace();
		} 
		int result =  mDao.join(params);
		if(result==1) {
			com.lhs.dto.EmailDto emailDto = new com.lhs.dto.EmailDto();
			emailDto.setFrom("whdudgms321@naver.com");
			emailDto.setReceiver(params.get("email"));
			emailDto.setSubject("회원가입을 환영합니다.");
			emailDto.setText(params.get("memberId") + "님의 가입을 진심으로 환영합니다.");
			try {
				emailUtil.sendHtmlMail(emailDto);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return result;
	}

	@Override
	public int checkId(HashMap<String, String> params) {
		return mDao.checkId(params);
	}

	@Override
	public boolean login(HashMap<String, String> params, HttpSession session) throws UserNotFoundException, PasswordMissMatchException {
		HashMap<String, Object> member = mDao.getMemberById(params);
		if(Objects.isNull(member))
			return false;
		try {
			System.out.println("member = ");
			System.out.println(member);
			String equPw = encoder.aesDecode( (String)member.get("member_pw"));
			if(equPw.equals(params.get("memberPw"))) {
				session.setAttribute("memberId", member.get("member_id"));
				session.setAttribute("memberNick", member.get("member_nick"));
				session.setAttribute("typeSep", member.get("type_seq"));
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-gatch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public int delMember(HashMap<String, Object> params) {	
		return mDao.delMember(params);
	}

	@Override
	public String getEamil(String memberId) {
		HashMap<String,String> params = new HashMap<String, String>();
		params.put("memberId", memberId);
		HashMap<String, Object> member = mDao.getMemberById(params);
		System.out.println("Service getEamil email = " + (String)member.get("email"));

		
		return (String)member.get("email");
	}

	@Override
	public MemberDto getMember(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getVNum(String email, HttpSession session) {
		//이메일 인증 번호 나중에 메서드로 바꾸기 !!!!
		String VNum = RandomNum.randum();
		session.setAttribute("VNum", VNum);
		
		com.lhs.dto.EmailDto emailDto = new com.lhs.dto.EmailDto();
		emailDto.setFrom("whdudgms321@naver.com");
		emailDto.setReceiver(email);
		emailDto.setSubject("이메일 인증번호");
		emailDto.setText("인증번호 입니다."+ VNum);
		try {
			emailUtil.sendHtmlMail(emailDto);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean checkVNum(HashMap<String, String> params, HttpSession session) {
		System.out.println("Service checkVNum()");
		System.out.println("params.get(\"VNum\")      "+params.get("VNum"));
		System.out.println("session.getAttribute(\"VNum\")      "+session.getAttribute("VNum"));
		
		boolean result = params.get("VNum").equals( session.getAttribute("VNum"));
		if(result) {
			System.out.println("입력값 변경 !!");
		 mDao.updatetype((String)session.getAttribute("memberId"));
			System.out.println("입력값 변경 !!");

		}
		
		return result;
	
	}


}
