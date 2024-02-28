package com.lhs.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.lhs.exception.PasswordMissMatchException;
import com.lhs.exception.UserNotFoundException;

public interface MemberService {

	public ArrayList<HashMap<String, Object>> memberList(HashMap<String, Object> params);
	
	//총 회원수 for paging
	public int totalMemberCnt(HashMap<String, Object> params);

	public int join(HashMap<String, String> params);
	
	public int checkId(HashMap<String, String> params);
	
	public boolean login(HashMap<String, String> params, HttpSession session) throws UserNotFoundException, PasswordMissMatchException;

	public int delMember(HashMap<String,Object> params);
}
