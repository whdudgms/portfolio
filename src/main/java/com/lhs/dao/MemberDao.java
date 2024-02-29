package com.lhs.dao;

import java.util.ArrayList;
import java.util.HashMap;

public interface MemberDao {
	
	public ArrayList<HashMap<String, Object>> memberList(HashMap<String, Object> params);

	public int totalMemberCnt(HashMap<String, Object> params);

	public int join(HashMap<String, String> params);
	
	public int checkId(HashMap<String, String> params);
	
	public int updatetype(String memberId);

	
	public HashMap<String, Object> getMemberById(HashMap<String, String> params);
	
	public String makeCipherText(HashMap<String, String> params);
	
	public int delMember(HashMap<String,Object> params);
	
}
