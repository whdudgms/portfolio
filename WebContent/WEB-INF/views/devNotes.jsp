<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

</head>

<body class="smoothscroll enable-animation">


	<%-- 내용 나올 div 시작!!!! --%>
	<section class="alternate">
	<div class="container">

		<div class="row">

			<div class="col-md-3">

				<div
					class="box-icon box-icon-center box-icon-round box-icon-transparent box-icon-large box-icon-content"
					style="width: 100%; height: 350px;">
					<div class="box-icon-title">
		<i class="fa fa-sharp fa-regular fa-object-ungroup"></i>
						<h2>프로젝트 생성과정</h2>
					</div>
					<p>Dynamic Web Project로 시작하여 밑바닥부터 프로젝트를 진행하였습니다. 
					의존성 문제를 효율적으로 해결하기 위해 Maven 프로젝트로 전환했고,
					Spring Framework에 대한 의존성을 추가하여 Spring 프로젝트로 발전시켰습니다.</p>
					<button type="button" class="btn btn-default btn-lg lightbox"
						data-toggle="modal" data-target="#myModal">DB &nbsp;
						Modeling &nbsp; &nbsp;(IMG)</button>
					<br> <br /> <a href="<c:url value='/file/downloadERD.do'/>">
						<button type="button" class="btn btn-default btn-lg lightbox"
							data-toggle="modal">ERD Download (MWB)</button>
					</a> <br />
				</div>

			</div>

			<div class="col-md-3">

				<div
					class="box-icon box-icon-center box-icon-round box-icon-transparent box-icon-large box-icon-content">
					<div class="box-icon-title">
					<i class="fa fa-solid fa-hammer"></i>		
				<h2>구현한 기능</h2>
					</div>
					<p>프로젝트에서는 게시판 관리를 위한 기본 <b>CRUD</b> 기능, 게시판 제목과 내용에 대한 검색 기능 구현, 권한인증을 위한 <b>이메일 인증</b> 시스템, 
					그리고 로컬환경과 배포환경에서 모두 가능한 <b>첨부파일 이미지 업로드 및 삭제</b> 기능을 구현했습니다. 
					</p>
				</div>

			</div>

			<div class="col-md-3">

				<div
					class="box-icon box-icon-center box-icon-round box-icon-transparent box-icon-large box-icon-content">
					<div class="box-icon-title">
						<i class="fa fa-sharp fa-solid fa-envelope"></i>
						<h2>AWS를 활용한 배포 적용</h2>
					</div>
					<p>포트폴리오 프로젝트의 배포를 위해, <b>Amazon Web Services(AWS)의 주요 서비스인 EC2(Elastic Compute Cloud), RDS(Relational Database Service), 
					그리고 Route 53</b>을 학습했습니다. 
					실제로 해당 프로젝트를 <b>배포할 때는 EC2만</b> 우선 사용했습니다. </p>
				</div>

			</div>

			<div class="col-md-3">

				<div
					class="box-icon box-icon-center box-icon-round box-icon-transparent box-icon-large box-icon-content">
					<div class="box-icon-title">
						<i class="fa fa-solid fa-gavel"></i>
						<h2>Rest API 전환</h2>
					</div>
					<p>기존에 @RequestMapping 어노테이션을 사용해 구현된 컨트롤러를 REST 원칙에 따라 수정했습니다. 
					이 과정에서는 <b>리소스 중심의 URL 설계를 채택</b>하여, URL 내에서 행위가 아닌 리소스만을 나타내도록 했습니다. 
					각 리소스에 대한 CRUD(Create, Read, Update, Delete) 작업은 <b>HTTP 메서드(GET, POST, PUT, DELETE 등)를 매핑함으로써</b> 구현했습니다. </p>
				</div>

			</div>

		</div>


	</div>


	<!-- img modal content -->
	<div id="myModal" class="modal fade bs-example-modal-lg" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">

				<!-- Modal Header -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">ERD</h4>
				</div>

				<!-- Modal Body -->
				<div class="modal-body">

					<img id="erdImg" width="100%"
						src="<c:url value='/resources/portfolio_ERD.png'/>" />

					<!-- Modal Footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>

				</div>
			</div>
		</div>
		<!-- img modal content -->


	</div>


	</section>
	<!-- / -->




</body>
</html>