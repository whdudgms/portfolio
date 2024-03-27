package com.lhs.service.impl;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lhs.dao.EmailAuthDao;
import com.lhs.dao.MemberDao;
import com.lhs.dto.MemberDto;
import com.lhs.entity.EmailAuth;
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
	
	@Autowired
	EmailAuthDao eAuth;
	
	
	

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
	public int join(MemberDto membetDto) {
		//아이디  검증하기 중복x 
		if(Objects.nonNull(mDao.getMemberById(membetDto))|| !(membetDto.getMemberPw().equals(membetDto.getAgainPw()))) {
			System.out.println("중복또는 비밀번호 체크에서 오류.");
			return 0;
		}
		try {
			
			membetDto.setMemberPw(encoder.aesEncode(membetDto.getMemberPw()));
			
		} catch (Exception e) {
		

			e.printStackTrace();
		} 
		int result =  mDao.join(membetDto);
		if(result==1) {
			com.lhs.dto.EmailDto emailDto = new com.lhs.dto.EmailDto();
			emailDto.setFrom("whdudgms321@naver.com");
			emailDto.setReceiver(membetDto.getEmail());
			emailDto.setSubject("회원가입을 환영합니다.");
			emailDto.setText(membetDto.getMemberId() + "님의 가입을 진심으로 환영합니다.");
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
	public boolean login(MemberDto memberDto, HttpSession session) throws UserNotFoundException, PasswordMissMatchException {
		MemberDto member = mDao.getMemberById(memberDto);
		if(Objects.isNull(member))
			return false;
		try {
			System.out.println("member = ");
			System.out.println(member);
			String equPw = encoder.aesDecode( member.getMemberPw());
			if(equPw.equals(memberDto.getMemberPw() )) {
				System.out.println("세션에 저장하는 부분 코드입니다.");
				System.out.println(member.toString());
				
				session.setAttribute("memberId", member.getMemberId());
				session.setAttribute("memberNick", member.getMemberNick());
				session.setAttribute("typeSeq", member.getTypeSeq());
				session.setAttribute("memberIdx", member.getMemberIdx());
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
		MemberDto membetDto = new MemberDto();
		membetDto.setMemberId(memberId);
		MemberDto member = mDao.getMemberById(membetDto);
		System.out.println("Service getEamil email = " + (String)member.getEmail());

		
		return (String)member.getEmail();
	}

	@Override
	public MemberDto getMember(String memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public String getVNum(String email, HttpSession session) {
		//이메일 인증 번호 나중에 메서드로 바꾸기 !!!!
		String VNum =RandomNum.randum();
		EmailAuth emailAuth = new EmailAuth();
		emailAuth.setAuth("N");
		emailAuth.setEmail(email);
		emailAuth.setLink("Null");
		emailAuth.setMemberId((String)session.getAttribute("memberId"));
		emailAuth.setMemberIdx((Integer)session.getAttribute("memberIdx"));
		

		session.setAttribute("VNum", VNum);
		
		com.lhs.dto.EmailDto emailDto = new com.lhs.dto.EmailDto();
		emailDto.setFrom("whdudgms321@naver.com");
		emailDto.setReceiver(email);
		emailDto.setSubject("이메일 인증번호");
		emailDto.setText("인증번호 입니다."+ VNum);
		eAuth.insertEAuth(emailAuth);

		try {
			emailUtil.sendHtmlMail(emailDto);
			session.setAttribute("authIdx", emailAuth.getAuthIdx());
			System.out.println("authIdx :");
			System.out.println("authIdx :");
			System.out.println(session.getAttribute("authIdx"));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "성공";
	}

	
	@Override
	public boolean checkVNum(HashMap<String, String> params, HttpSession session) {
		System.out.println("Service checkVNum()");
		System.out.println("params.get(\"VNum\")      "+params.get("VNum"));
		System.out.println("session.getAttribute(\"VNum\")      "+session.getAttribute("VNum"));
		params.put("authIdx", session.getAttribute("authIdx") + "");
		
		boolean result = params.get("VNum").equals( session.getAttribute("VNum"));
		if(result) {
			System.out.println("입력값 변경 !!");
			session.setAttribute("typeSeq", "5");
			eAuth.updateElAuth(params);
		 mDao.updatetype((String)session.getAttribute("memberId"));
			System.out.println("입력값 변경 !!");

		}
		
		return result;
	
	}


}