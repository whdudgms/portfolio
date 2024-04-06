<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	
	<div class="container">
		<h4>공지사항</h4>
		
			
	<div class="container">
		    <div class="row justify-content-center">
		        <div class="col-md-12">
		            <form   class="form-inline justify-content-center" id="searchForm" name="searchForm">
		                <div class="form-group mx-2">
		                    <select name="searchType" id="searchType" class="form-control">
		                        <option value="title">제목</option>
		                        <option value="content">내용</option>
		                        <option value="titleContent">제목+내용</option>
		                    </select>
		                </div>
		                <div class="form-group mx-2">
		                    <input type="text" id="searchWord"  name="searchWord" class="form-control">
		                </div>
		                <button type="button" id="btnSearch" name="btnSearch" class="btn btn-primary mx-2">검색</button>
		            </form>
		        </div>
		    </div>
		</div>
			
		
		
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
						<th class="fw-30" align="center">&emsp;&emsp;공지사항 번호</th>
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
                        <th class="text-center">${board.boardSeq} </th>
                       <th> <a href="javascript:movePage('/notice/read.do?boardSeq=${board.boardSeq}&currentPage=${pageInfo.currentPage}')" >${board.title}</a></th>
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
						href="javascript:movePage('/notice/list.do?currentPage=${pageInfo.currentPage - 1}')">&laquo;</a></li>
					</c:if>
			
																
						

						<c:forEach begin="${pageInfo.startNavi}" end="${pageInfo.startNavi + pageInfo.pageNaviSize - 1}" var="i">
						    <c:if test="${i <= pageInfo.maxNavi }">
						        <li class="page-item ${i == pageInfo.currentPage ? 'active' : ''}">
						            <a class="page-link" href="javascript:movePage('/notice/list.do?currentPage=${i}')">${i}</a>
						        </li>
						    </c:if>
						</c:forEach>
						
						
						
						
					<c:if test="${pageInfo.currentPage < pageInfo.maxNavi  }">
							<li class="page-item"><a class="page-link"
						href="javascript:movePage('/notice/list.do?currentPage=${pageInfo.currentPage + 1}')">&raquo;</a></li>
					</c:if>
					
						
						
				</ul>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-12 text-right">
			

						
		<c:choose>
			<c:when test="${not empty sessionScope.memberId and sessionScope.typeSeq == 9}">
		        <a href="javascript:movePage('/notice/goToWrite.do')">
		            <button type="button" class="btn btn-primary">
		                <i class="fa fa-pencil"></i> 글쓰기 
		            </button>
		        </a>
		    </c:when>
		    <c:otherwise>
		   
		    </c:otherwise>
		</c:choose>
			
			
			</div>
		</div>
	</div>
	
	<!-- / -->
<script type="text/javascript">
$(document).ready(function(){
    $('#btnSearch').click(function(){
        if($('#searchType').val() == '' || $('#searchWord').val() == ''){
            window.alert("검색 조건과 검색어를 입력하세요.");
            return;
        }
        if($('#searchWord').val().includes('%')){
            window.alert("% 문자를 검색어에 포함할 수 없습니다. 다시 입력하세요.");
            return;
        }
       var searchWord = $("#searchWord").val()
        var searchType  = $("#searchType").val()
       window.alert(searchWord+"     "+searchType)
        javascript:movePage('/notice/list.do?searchWord='+searchWord+'&searchType='+searchType)
        
    });
});
</script>
</body>
<script>
		var totalNavi = '${(pageInfo.totalBoard % pageInfo.pageSize == 0 ? pageInfo.totalBoard / pageInfo.pageSize : pageInfo.totalBoard / pageInfo.pageSize +1)  }'
		console.log(totalNavi);
		var typeSeq = '${sessionScope.typeSeq }'
		console.log(typeSeq);

</script>
</html>