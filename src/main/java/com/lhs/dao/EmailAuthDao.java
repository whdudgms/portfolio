package com.lhs.dao;

import java.util.HashMap;

import com.lhs.entity.EmailAuth;

public interface EmailAuthDao {
	
	public int insertEAuth(EmailAuth dto);
	public int updateElAuth(HashMap<String, String> params); 


}
