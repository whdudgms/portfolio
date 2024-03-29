package com.lhs.util;

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
public class PageInfo {
	private Integer pageSize = 10;  //  페이지를 보여줄 사이즈 (변수로 놓기는 하지만 그냥 10으로 고정할거임 )
	private Integer currentPage = 1; // 현재 페이 1인지 2인지 그런 것 ~   
	private Integer totalBoard;     // 전체 게시
	private Integer pageNaviSize = 10;  // 
	private String searchType ;
	private String searchWord ;
	
	private Integer maxNavi; // 네비 전체 사이즈 ex  페이지 사이즈 10 게시물 2584  259
	private Integer startNavi ;
}
