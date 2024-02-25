package com.lhs.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.dao.MemberDao;
import com.lhs.exception.PasswordMissMatchException;
import com.lhs.exception.UserNotFoundException;
import com.lhs.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired MemberDao mDao; 

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
		return mDao.join(params);
	}

	@Override
	public int checkId(HashMap<String, String> params) {
		return mDao.checkId(params);
	}

	@Override
	public HashMap<String, Object> login(HashMap<String, String> params) throws UserNotFoundException, PasswordMissMatchException {
		HashMap<String, Object> member = mDao.getMemberById(params);
		return member;
	}

	@Override
	public int delMember(HashMap<String, Object> params) {	
		return mDao.delMember(params);
	}



	

}
