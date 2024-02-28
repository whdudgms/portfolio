<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<section>
	<div class="container">
		<h4>공지사항  </h4>
		<div class="table-responsive">
			<table class="table table-sm">
				<colgroup>
					<col width="10%" />
					<col width="35%" />
					<col width="10%" />
					<col width="8%" />
					<col width="8%" />
					<col width="15%" />
				</colgroup>
				
				<thead>
					<tr>
						<th class="fw-30" align="center">&emsp;&emsp;&emsp;#</th>
						<th align="center">제목</th>
						<th align="center">글쓴이</th>
						<th align="center">조회수</th>
						<th align="center">첨부파일</th>
						<th align="center">작성일</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td align="center">글번호</td>
						<td>
							<span class="bold">
								<a href="javascript:movePage(null,'/notice/read.do?boardSeq=boardSeq&currentPage=currentPage')">
									title
								</a>
							</span>
                          </td>
                          <td>memberNick</td>
                          <td>hits</td>
                          <td>hasFile</td>
                          <td>createDate</td>
                      </tr> 
				</tbody>
			</table>
		</div>
		<div class="row text-center">
		    <div class="col-md-12">
			    <ul class="pagination pagination-simple pagination-sm">
			    	<!-- 페이징 -->
				    <li class="page-item">
			        	<a class="page-link" href="javascript:movePage('/notice/list.do?page=1')">&laquo;</a>
			        </li>
			        <li class="page-item"><a class="page-link">1</a></li>
			        <li class="page-item active"><a class="page-link" href="javascript:movePage('/notice/list.do?page=2')">2</a></li>
			        <li class="page-item"><a class="page-link" href="javascript:movePage('/notice/list.do?page=3')">3</a></li>
			        <li class="page-item"><a class="page-link" href="javascript:movePage('/notice/list.do?page=4')">4</a></li>
			        <li class="page-item"><a class="page-link" href="javascript:movePage('/notice/list.do?page=5')">5</a></li>
			        <li class="page-item"><a class="page-link" href="javascript:movePage('/notice/list.do?page=6')">6</a></li>
			        <li class="page-item">
			        	<a class="page-link" href="javascript:movePage('/notice/list.do?page=99')">&raquo;</a></li>
			        </li>
			    </ul>
		    </div>
		</div>
		<div class="row">
		    <div class="col-md-12 text-right">			   
		    <a href="javascript:movePage('/notice/goToWrite.do')">
		        <button type="button" class="btn btn-primary">
		        	<i class="fa fa-pencil"></i> 글쓰기
		        </button>
		    </a>
		    </div>
		</div>
	</div>
</section>
<!-- / -->
</body>
</html>