<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
	 	$('#msgDiv').hide();
		$("#loading-div-background").css({ opacity: 1 }); 
		


			// overlay 보이기
			 $("#btnEmail").click(function(event){
			var formData = new FormData(document.emailForm);
			$.ajax({
				url: "<c:url value='/member/VNumCheck.do'/>",
				type: "POST",
				data: formData,
				dataType:'TEXT',
				cache: false,
				processData: false,
				contentType: false,
				success: function(data, textStatus, jqXHR) {
					data = $.parseJSON(data);
					console.log(data);
					if(data.msg != undefined && data.msg != ''){
						var msgTag = $("<strong>").text(data.msg);
						$('#msgDiv').html(msgTag).show();
						$("#loading-div-background").hide();	// overlay 숨기기
					}
					else {
						window.location.href = ctx + data.nextPage;
					}
				},
				error: function(jqXHR, textStatus, errorThrown) {
					$("#loading-div-background").hide();	// overlay 숨기기					
					console.log(jqXHR);
					console.log(textStatus);
					console.log(errorThrown);
				}
			});
		});
			
			
			
			
		
			
			
			 $("#btnVNum").click(function(event){
			        event.preventDefault(); // 폼 전송 방지
			        $.ajax({
			            url: "<c:url value='/member/sendemail.do'/>", // 요청을 보낼 서버의 URL
			            type: 'POST', // 요청 방식
			            data: {
			                // 서버로 보낼 데이터
			                email: $("#email").val() // 예시: email 필드의 값을 전송
			            },
			            success: function() {
			            	alert('정상적으로 메일이 전송 됐습니다.')
			            },
			            error: function() {
			            	alert('메일 전송이 실패했습니다.')
			            }
			        });
			    });
	});
</script>
</head>
<body>
	<!-- -->
	<section>
	<div class="container">
		<!-- 			<h2>LOGIN</h2> -->
		<div class="row">

			<div class="col-md-6 offset-md-3">

				<!-- ALERT -->
				<div id="msgDiv" class="alert alert-mini alert-danger mb-10"></div>
				<!-- /ALERT -->

				<div class="box-static box-border-top p-30">
					<div class="box-title mb-30">
						<h2 class="fs-20">REGISTER EMAIL</h2>
					</div>

					<form class="m-0" method="post" name="emailForm" autocomplete="off">
						<div class="clearfix">
							<!-- Email -->
							<div class="form-group">
								<input type="text" id="memberId" name="memberId"
									class="form-control"  value='${sessionScope.memberId }' disabled>
							</div>

							<!-- Password -->
							<div class="form-group">
								<input type="text" id="email" name="email"
									class="form-control"  value='${sessionScope.email}' disabled>
							</div>
							
							<div class="form-group">
								<input type="text" id="VNum" name="VNum"
									class="form-control"  required >
							</div>
						</div>

						<div class="row">
						
								<div class="col-md-8 col-sm-8 col-8 text-left">
								<button type="button"  id="btnVNum" class="btn btn-primary">
									Get verification number  </button>
							</div>
							<div class="col-md-4 col-sm-4 col-4 text-right">
								<button type="button" id="btnEmail" class="btn btn-primary">
									Submit</button>
							</div>
					
						</div>

					</form>

				</div>

			</div>
		</div>

	</div>
	</section>
</body>
</html>
