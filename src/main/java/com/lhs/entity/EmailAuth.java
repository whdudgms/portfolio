package com.lhs.entity;


import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter //set
@Getter  //get
@ToString //toString
@Builder// 빌더 임 
@RequiredArgsConstructor //생성자 알아서 만들어 주는 것 
@AllArgsConstructor
@Alias("EmailAuth")
public class EmailAuth {
	private int idx;
	private int memberIdx;
	private String memberTypeSeq;
	private String memberId;
	private String email;
	private String link;
	private String sendDtm;

}
