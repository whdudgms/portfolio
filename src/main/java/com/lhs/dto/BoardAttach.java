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
@Alias(value = "board_attch")
public class BoardAttach {
	int fileIdx;
	int typeSeq;
	int boardSeq;
	String fileName;
	String fakeFilename;
	long fileSize;
	String fileType;
	String saveLoc;
	String createDtm;	
}
