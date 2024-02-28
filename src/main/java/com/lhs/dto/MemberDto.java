package com.lhs.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
@Alias(value = "member")
public class MemberDto {
	Integer memberIdx;
	Integer typeSeq;
	String memberId;
	String memberPw;
	String memberName;
	String memberNick;
	String email;
	String createDtm;
	String updateDtm;
	String membercol;
}
