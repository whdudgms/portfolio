<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<section>
	<div class="container">
		<h4>자유게시판</h4>
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
					<c:forEach var="board" items="${Boardlist}" varStatus="rowStatus">
                    <tr>
                        <th class="text-center">${rowStatus.index} </th>
                       <th> <a href="javascript:movePage('/board/read.do?boardSeq=${board.boardSeq}&currentPage=${pageInfo.currentPage}')" >${board.title}</a></th>
                        <th>${board.memberNick} </th>
                        <th>${board.hits} </th>
                        <th>${board.hasFile} </th>
                        <th>${board.createDtm} </th>
                    </tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="row text-center">
			<div class="col-md-12">
				<ul class="pagination pagination-simple pagination-sm">
					<!-- 페이징 -->
					
					<c:if test="${pageInfo.currentPage != 1}">
									<li class="page-item"><a class="page-link"
						href="javascript:movePage('/board/list.do?currentPage=${pageInfo.currentPage - 1}')">&laquo;</a></li>
					</c:if>
					
																
						
						<c:set var="totalPages" value="${pageInfo.totalBoard % pageInfo.pageSize == 0 ? pageInfo.totalBoard / pageInfo.pageSize : (pageInfo.totalBoard / pageInfo.pageSize) + 1}" />

						<c:forEach begin="${pageInfo.startNavi}" end="${pageInfo.startNavi + pageInfo.pageNaviSize - 1}" var="i">
						    <c:if test="${i <= totalPages}">
						        <li class="page-item ${i == pageInfo.currentPage ? 'active' : ''}">
						            <a class="page-link" href="javascript:movePage('/board/list.do?currentPage=${i}')">${i}</a>
						        </li>
						    </c:if>
						</c:forEach>
						
						
					<c:if test="${pageInfo.currentPage < pageInfo.maxNavi  }">
							<li class="page-item"><a class="page-link"
						href="javascript:movePage('/board/list.do?currentPage=${pageInfo.currentPage + 1}')">&raquo;</a></li>
					</c:if>
		
						
						
				</ul>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12 text-right">
				<a href="javascript:movePage('/board/goToWrite.do')">
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
<script>
		var totalNavi = '${(pageInfo.totalBoard % pageInfo.pageSize == 0 ? pageInfo.totalBoard / pageInfo.pageSize : pageInfo.totalBoard / pageInfo.pageSize +1)  }'
		console.log(totalNavi);
</script>
</html>