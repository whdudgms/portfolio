<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>

<body class="smoothscroll enable-animation">


	<%-- 내용 나올 div 시작!!!! --%>

	<!-- -->
	<section>
	<div class="container">

		<div class="row">

			<div class="col-lg-6 col-md-6 col-sm-6">
				<div class="col-lg-3 col-md-3 col-sm-3">
					<img class="img-responsive"
						src="resources/images/_smarty/lazy-loadimg.jpg" width="500"
						height="300" alt="">
				</div>
			</div>

			<div class="col-lg-6 col-md-6 col-sm-6">
				<div class="heading-title heading-border-bottom">
					<h3>부족한 부분을 공부하고 채우는 개발자가 되겠습니다.</h3>
				</div>
				<p>저는 1번의 국비지원 교육과 1번의 부트캠프 경험이 있습니다. 
				국비지원 학원에서 제일
				처음 <b>스프링</b>을 배웠을 때 <b>정말 어려웠습니다.</b> 객체를 직접 생성하면 되는 문제를 굳이 컨테이너에서 
				가져온다거나 빈을 컨테이너에 등록한다는 등 이해가 되질 않으니 그다음 수업을 따라가기 힘들었습니다.
				오히려 Servlet과 JSP로 프로젝트를 하는 게 더 쉽다고 생각할 정도 였으니까요. 하지만 학원이 끝나고 
				Java에 대해서 더 공부하고 Spring을 다시 공부하니 <b>스프링의 컨테이너가 있어서 객체들을 효율적으로 재사용할 수 있고 
				정적 의존성이 아닌 동적 의존성을 부여할 수 있어서 SOLID 원칙을 잘 구현할 수 있다는 것</b>을 깨달았습니다. 
				<b>앞으로도 
				부족한 점도 있겠지만, 부족한 부분을 내버려두지 않고 채우려고 노력하겠습니다.</b></p>
				<%-- <blockquote>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Maecenas metus nulla, commodo a sodales sed, dignissim pretium
						nunc.</p>
					<cite>Albert Einstein</cite>
				</blockquote> --%>
			</div>

		</div>

	</div>
	</section>
	<!-- / -->




	<!-- -->
	<section>
	<div class="container">

		<div class="row">

			<div class="col-lg-6 col-md-6 col-sm-6">

				<div class="heading-title heading-border-bottom">
					<h3>Development Experience</h3>
				</div>

				<ul class="nav nav-tabs nav-clean">
					<li class="active"><a href="#tab1" data-toggle="tab">개인 포트폴리오
					프로젝트</a></li>
					<!-- <li class="active"><a href="#tab2" data-toggle="tab">쇼핑몰 프로젝트</a></li> -->		
							
					<!-- <li><a href="#tab3" data-toggle="tab">MariaDB</a></li> -->
				</ul>

				<div class="tab-content">
					<div id="tab1" class="tab-pane fade in active">
						<img class="pull-left"
							src="demo_files/images/mockups/600x399/20-min.jpg" width="200"
							alt="" />
						<p>DynamicWebProject부터 직접 의존성을 주입해서 직접 스프링 프로젝트로 
						만들고  최종적으로 개인 포트폴리오 대용으로 만든 프로젝트입니다. 
						AWS를 사용하고 반자동 배포를 위해서 Maven을 사용했습니다.
						<p>
						
					</div>
					<div id="tab2" class="tab-pane fade">
					<!-- 	<img class="pull-right"
							src="demo_files/images/mockups/600x399/20-min.jpg" width="200"
							alt="" />
						<p>국비지원 학원 당시에 만들었던 쇼핑몰 프로젝트입니다. JSP Servlet기반으로 만들었습니다.
						 관리자 관련 기능 구현
						</p>
 -->
					</div>
					<!-- <div id="tab3" class="tab-pane fade">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Ipsam quidem voluptatem accusantium praesentium inventore quae
							illum.
						<p>
						<p>Officia illum eos quos voluptate omnis deleniti molestiae
							numquam fugiat delectus aliquam ab.</p>
					</div> -->
				</div>
			</div>

			<div class="col-lg-6 col-md-6 col-sm-6">
    <div class="education-history">
        <h2>교육 이력</h2>
        <ul>
            <li>
                <strong>기간:</strong> 2023년 10월 - 2024년 1월
                <br>
                <strong>학원명 : 과정명</strong> 정석코딩 : 자바기반 백엔드
            </li>
            <li>
                <strong>기간:</strong> 2023년 7월 - 2023년 8월
                <br>
                <strong>학원명 : 과정명</strong> 서울42 : 라피신 
            </li>
            <li>
                <strong>기간:</strong> 2022년 9월 - 2023년 3월 
                <br>
                <strong>학원명 : 과정명</strong> 강남ITWill : 프로젝트 기반 자바 핀테크 웹 개발자 양성과정
            </li>
        </ul>
    </div>
</div>
		</div>

	</div>
	</section>
	<!-- / -->


</body>
</html>