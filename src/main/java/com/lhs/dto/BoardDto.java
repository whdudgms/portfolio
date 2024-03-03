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
@Alias(value = "board")
public class BoardDto {
	int boardSeq;
	int typeSeq;
	String memberId;
	String memberNick;
	String title;
	String content;
	String hasFile;
	int hits;
	String createDtm;
	String updateDtm;
}
