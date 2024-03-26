<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
$(document).ready(function(){
	$('#msgDiv').hide();
	$("#loading-div-background").css({ opacity: 1 });
	function validateEmail(email) {
	    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,4}\.[0-9]{1,4}\.[0-9]{1,4}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	    return re.test(String(email).toLowerCase());
	}
	
	
	$('#btnSignUp').click(function(e){
		

		if( $('#memberId').val() == '' || $('#memberName').val() == '' || $('#pwAgain').val() == '' 
				|| $('#memberNick').val() == '' || $('#memberPw').val() == '' || $('#email').val() == ''){
			var msgTag = $('<strong>').text("모든 항목은 필수입니다.");
			$('#msgDiv').html(msgTag).show();
			e.preventDefault();
			return;
		}
		if( $('#memberId').val().length < 4 ){
			var msgTag = $('<strong>').text("아이디가 너무 짧아요 4글자 이상만 가능해요.");
			$('#msgDiv').html(msgTag).show();
			e.preventDefault();
			return;
		}
		
		
		if( $('#pwAgain').val().length < 6 || $('#memberPw').val().length < 6 ){
			var msgTag = $('<strong>').text("패스워드가 너무 짧아요 6글자 이상만 가능해요.");
			$('#msgDiv').html(msgTag).show();
			e.preventDefault();
			return;
		}
		
		if( $('#pwAgain').val() != $('#memberPw').val() ){
		var msgTag = $('<strong>').text("패스워드가 일치하지 않습니다.");
		$('#msgDiv').html(msgTag).show();
		e.preventDefault();
		return;
		}
		
		var email = $('#email').val();
	    if (!validateEmail(email)) {
	        var msgTag = $('<strong>').text("유효하지 않은 이메일 형식입니다.");
	        $('#msgDiv').html(msgTag).show();
	        e.preventDefault();
	        return;
	    }
	    
	    
	    if( $('#pwAgain').val().length > 20 || $('#memberPw').val().length > 20 ){
			var msgTag = $('<strong>').text("패스워드가 너무 길어요 20글자보다는 짧아야 해요.");
			$('#msgDiv').html(msgTag).show();
			e.preventDefault();
			return;
		}
	    
	    if( $('#memberName').val().length > 30 || $('#memberNick').val().length > 30 ){
			var msgTag = $('<strong>').text("멤버 이름 또는 멤버닉네임이 너무 길어요. 30글자보다는 짧아야 해요.");
			$('#msgDiv').html(msgTag).show();
			e.preventDefault();
			return;
		}
	    if( $('#memberId').val().length > 35 ){
			var msgTag = $('<strong>').text("멤버 아이디가 너무 길어요 35글자보다는 짧아야 해요.");
			$('#msgDiv').html(msgTag).show();
			e.preventDefault();
			return;
		}
	    if( $('#email').val().length > 50 ){
			var msgTag = $('<strong>').text("이메일이 이건 너무 길어요 아무리 길어도 총 50글자는 안 넘어야죠.");
			$('#msgDiv').html(msgTag).show();
			e.preventDefault();
			return;
		}
		
		// overlay 보이기
		$("#loading-div-background").css({'z-index' : '9999'}).show();
		
		$.ajax({
			url: "<c:url value='/memberId'/>",
			data : {memberId : $('#memberId').val() },
			success : function (data, textStatus, XMLHttpRequest) {
				console.log(data);
				if(data.cnt == 1){
// 					alert(data.msg);
					var msgTag = $('<strong>').text(data.message);
					$('#msgDiv').show().html(msgTag);
					$('#memberId').focus();
					// overlay 숨기기
					$("#loading-div-background").hide();
					return;
				}
				else {
					var formData = new FormData(document.regForm);
// 					var formData = $("#registerForm").serialize();
					console.log(formData);
					$.ajax({
						url: '<c:url value="/member"/>',
						type: "POST",
						data: formData,
						dataType:'TEXT',
						cache: false,
						processData: false,
						contentType: false,
						success: function(data, textStatus, jqXHR) {
							data = $.parseJSON(data);
// 							console.log(data);
							alert(data.message);

							$("#loading-div-background").hide();	// overlay 숨기기
							
							movePage(data.nextPage);
						},
						error: function(jqXHR, textStatus, errorThrown) {
							$("#loading-div-background").hide();	// overlay 숨기기
							
							console.log(jqXHR);
							console.log(textStatus);
							console.log(errorThrown);
						}
					});
				}
			},
			error : function (XMLHttpRequest, textStatus, errorThrown) {
				$(tabId, myLayout.panes.center).html(XMLHttpRequest.responseText);
			}
		});
	});
});
</script>
</head>
<body>
	<section>
	<div class="container">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<!-- ALERT -->
				<div id="msgDiv" class="alert alert-mini alert-danger mb-10"></div>
				<!-- /ALERT -->

				<div class="box-static box-border-top p-30">
					<div class="box-title mb-30">
						<h2 class="fs-20">REGISTER</h2>
					</div>

					<form class="m-0 sky-form" name="regForm" method="post"
						enctype="multipart/form-data">
						<fieldset>

							<div class="row">

								<div class="col-md-6 col-sm-6">
									<label>ID *</label> <label class="input mb-10"> <i
										class="ico-append fa fa-user"></i> <input required=""
										type="text" id="memberId" name="memberId" /> <b
										class="tooltip tooltip-bottom-right">Your ID</b>
									</label>
								</div>

								<div class="col-md-6 col-sm-6">
									<label for="register:last_name">Name *</label> <label
										class="input mb-10"> <i class="ico-append fa fa-user"></i>
										<input required="" type="text" id="memberName"
										name="memberName" /> <b class="tooltip tooltip-bottom-right">Your
											Full Name</b>
									</label>
								</div>
							</div>

							<div class="row">
								<div class="col-md-6 col-sm-6">
									<label for="register:pass1">Password *</label> <label
										class="input mb-10"> <i class="ico-append fa fa-lock"></i>
										<input required="" type="password" class="err" id="memberPw"
										name="memberPw" /> <b class="tooltip tooltip-bottom-right">Min.
											6 characters</b>
									</label>
								</div>

								<div class="col-md-6 col-sm-6">
									<label for="register:pass2">Password Again *</label> <label
										class="input mb-10"> <i class="ico-append fa fa-lock"></i>
										<input required="" type="password" id="pwAgain" class="err"
										name="againPw"> <b
										class="tooltip tooltip-bottom-right">Type the password
											again</b>
									</label>
								</div>

							</div>

							<div class="row">
								<div class="col-md-6 col-sm-6">
									<label for="/member/join.do">Nick *</label> <label
										class="input mb-10"> <i class="ico-append fa fa-phone"></i>
										<input required="" type="text" id="memberNick"
										name="memberNick" /> <b class="tooltip tooltip-bottom-right">Your
											Nick</b>
									</label>
								</div>

								<div class="col-md-6 col-sm-6">
									<label for="register:email">Email *</label> <label
										class="input mb-10"> <i
										class="ico-append fa fa-envelope"></i> <input required=""
										type="text" id="email" name="email" /> <b
										class="tooltip tooltip-bottom-right">Your Email</b>
									</label>
								</div>
							</div>
							<%--
							<hr />
							<label class="checkbox m-0"><input class="checked-agree" type="checkbox" name="checkbox"><i></i>I agree to the <a href="#" data-toggle="modal" data-target="#termsModal">Terms of Service</a></label>
							<label class="checkbox m-0"><input type="checkbox" name="checkbox"><i></i>I want to receive news and  special offers</label>
							 --%>
						</fieldset>

						<div class="row">
							<div class="col-md-12 col-sm-12 col-12 text-right">
								<button id="btnSignUp" type="button" class="btn btn-primary">
									<i class="fa fa-check"></i> REGISTER
								</button>
							</div>
						</div>

					</form>
				</div>
			</div>
		</div>
	</div>
	</section>
	<!-- / -->
	<!-- overlay html start -->
	<div id="loading-div-background">
		<div id="loading-div" class="ui-corner-all">
			<img style="height: 32px; width: 32px; margin: 30px;"
				src="<c:url value='/resources/please_wait.gif'/>" alt="Loading.." />
			<br> PROCESSING. PLEASE WAIT...
		</div>
	</div>
	<!-- overlay html end -->
</body>
</html>