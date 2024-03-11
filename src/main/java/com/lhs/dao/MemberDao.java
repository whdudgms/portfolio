package com.lhs.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.lhs.dto.MemberDto;

public interface MemberDao {
	
	public ArrayList<HashMap<String, Object>> memberList(HashMap<String, Object> params);

	public int totalMemberCnt(HashMap<String, Object> params);

	public int join( MemberDto memberDto);
	
	public int checkId(HashMap<String, String> params);
	
	public int updatetype(String memberId);
	
	public MemberDto getMemberById(MemberDto memberDto);
	
	public String makeCipherText(HashMap<String, String> params);
	
	public int delMember(HashMap<String,Object> params);
	public HashMap<String, Object>  findMember(String memberId);

}
