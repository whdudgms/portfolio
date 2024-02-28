package com.lhs.service.impl;


import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.ConstructorArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.dao.MemberDao;
import com.lhs.exception.PasswordMissMatchException;
import com.lhs.exception.UserNotFoundException;
import com.lhs.service.MemberService;
import com.lhs.util.AES256Util;
import com.lhs.util.EmailUtil;

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
		if(Objects.nonNull(mDao.getMemberById(params))|| params.get("memberPw").equals(params.get("againPw")))
			return 0;
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


}
