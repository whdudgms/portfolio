<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko" dir="ltr">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no">
<meta http-equiv="Expires" content="-1">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="No-Cache">
<meta http-equiv="Content-Security-Policy"
	content="upgrade-insecure-requests">

<script
	src="https://kr.lgemembers.comhttps://kr.lgemembers.com/js/jquery-3.6.0.min.js"></script>
<script
	src="https://kr.lgemembers.comhttps://kr.lgemembers.com/js/jquery-3.6.0.min.js/js/jquery-ui.js"></script>
<script
	src="https://kr.lgemembers.comhttps://kr.lgemembers.com/js/jquery-3.6.0.min.js/js/jquery.fileDownload.js"></script>
<script src="https://kr.lgemembers.com/js/jquery.loading.block.js"></script>
<script src="https://kr.lgemembers.com/js/util.js?v=202402270658450048"></script>
<script src="https://kr.lgemembers.com/js/jquery.validate.js"></script>

<script src="https://kr.lgemembers.com/js/scroll-detection.js" defer></script>
<script src="https://kr.lgemembers.com/js/scroll-shadow.js" defer></script>

<script
	src="https://kr.lgemembers.com/js/bootstrap/bootstrap.bundle.min.js"></script>
<script src="https://kr.lgemembers.com/js/popper.min.js"></script>
<script src="https://kr.lgemembers.com/js/bootstrap/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="https://kr.lgemembers.com/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://kr.lgemembers.com/css/style_ko.min.css">
<link rel="shortcut icon"
	href="https://kr.lgemembers.com/images/favicon.ico" type="image/x-icon">
<script>
	function tracelog() {
	}
</script>
<script type="text/javascript">
	var svc_list_ga = "SVC612" || 'SVC709';

	var gtmContainer = "GTM-MGKBGC44,GTM-N6B5689";
	if (svc_list_ga === 'SVC709') { //SVC709 일때만 아래 스니펫 호출 요청.
		gtmContainer = "GTM-NNRLX8H";

		if (gtmContainer !== '' && gtmContainer !== null) {
			(function(w, d, s, l, i) {
				w[l] = w[l] || [];
				w[l].push({
					'gtm.start' : new Date().getTime(),
					event : 'gtm.js'
				});
				var f = d.getElementsByTagName(s)[0], j = d.createElement(s), dl = l != 'dataLayer' ? '&l='
						+ l
						: '';
				j.async = true;
				j.src = 'https://www.googletagmanager.com/gtm.js?id=' + i + dl;
				f.parentNode.insertBefore(j, f);
			})(window, document, 'script', 'dataLayer', gtmContainer);
		}
	}
</script>
<script type="text/javascript">
	$(function() {
		$(document).on('click', '.lgacc-contents button', function() {
			var $b = $(this);
			$b.attr('disabled', true);
			setTimeout(function() {
				$b.attr('disabled', false);
			}, 3000);
		})
	})
	var LGAC = LGAC || {};

	LGAC.ERROR = (function() {

		function _getHttpStatusError(__opt) {
			var _msg = '';
			switch (__opt) {
			case '400':
				_msg = "400 Bad Request";
				break;
			case '401':
				_msg = "401 Unauthorized";
				break;
			case '403':
				_msg = "403 Forbidden";
				break;
			case '405':
				_msg = "405 Method Not Allowed";
				break;
			case '406':
				_msg = "406 Not Acceptable";
				break;
			case '411':
				_msg = "411 Length Required";
				break;
			case '412':
				_msg = "412 Precondition Failed";
				break;
			case '500':
				_msg = "500 Internal Server Error";
				break;
			}
			return _msg;
		}

		var _getMembershipMsg = function(__opt, __brTagReplace) {
			var _msg = '', _isReplace = (typeof __brTagReplace === "undefined") ? true
					: __brTagReplace;

			switch (__opt) {

			case "Common.ERR_MSG.64":
				_msg = "지원하지 않는 브라우저 입니다.\u003Cbr\u003E 다른브라우저를 이용해주세요.\u003Cbr\u003E (예 : 크롬, 사파리 등)";
				break;
			case "TB.MINUTE":
				_msg = "분";
				break;
			case "TB.SECOND":
				_msg = "초";
				break;
			case "PC-SI-802-04P.BODY.01":
				_msg = "이미 가입된 계정이 있습니다.";
				break;
			case "Common.ERR_MSG.36":
				_msg = "{0}으로 가입된 계정입니다.\u003Cbr\u003E ‘{1} 로그인’으로 로그인하세요.";
				break;
			case "Common.ERR_MSG.37":
				_msg = "SNS로 가입 된 계정입니다.\u003Cbr\u003E 해당 계정으로 로그인하세요.";
				break;
			case "PC-SI-206P.TITLE":
				_msg = "로그인 안내";
				break;
			case "PC-SI-401L.ERR_MSG.82":
				_msg = "LG ThinQ 서비스 회원가입이 필요합니다.\u003Cbr\u003E LG ThinQ 앱에 제품을 등록하신 후 다시 로그인해주세요.";
				break;
			}

			if (_isReplace) {
				_msg = _msg.replace(/(<br>|<br\/>|<br \/>)/g, '\r\n');
			}

			return _msg;
		};

		return {
			getHttpStatusError : _getHttpStatusError,
			getMessage : _getMembershipMsg
		};
	})();
</script>
<head>
<!-- 변경할 이메일 아이디 입력 [PC-AC-103] -->
<title>프로필 | LGE 멤버스</title>

<script>
	var svcCode = svc_list_ga || 'SVC709'; //getSessionStorageObject('svcCode') || 'SVC709'	//REQEMP-10097
	function googleTag_email_auth() {
		_gTitle('이메일 인증');
		_gPush('', svcCode, '', '', '', 'ID_C');

		_gTM('GTM-MGKBGC44,GTM-N6B5689');

	}

	googleTag_email_auth();
</script>
</head>
<body>
	<!-- tooltip -->
	<!-- Popover content -->
	<div id="PopoverContent1" style="display: none;">
		<ul class="list-box pt-3">
			<li class="list-box-item"><span class="telecom-logo"> <img
					src="https://kr.lgemembers.com/images/svg/logo-uplus.svg"
					alt="LG U+">
			</span>
				<p></p></li>
			<li class="list-box-item"><span class="telecom-logo"> <img
					src="https://kr.lgemembers.com/images/svg/logo-sk-telecom.svg"
					alt="SK Telecom">
			</span>
				<p></p></li>
			<li class="list-box-item"><span class="telecom-logo"> <img
					src="https://kr.lgemembers.com/images/drawable-hdpi/logo-kt.png"
					alt="KT"
					srcset="/images/drawable-xhdpi/logo-kt@2x.png 2x, /images/drawable-xxhdpi/logo-kt@3x.png 3x">
			</span>
				<p></p></li>
		</ul>
	</div>

	<div id="PopoverContent2" style="display: none;">
		<dl class="popover-info-list mb-4" role="text">
			<dd class="bullet-on">
				1. 이메일의 스팸 메일함 또는 휴지통 폴더에 “LG전자 계정”에서 보낸 이메일이 있는지 확인해 보세요.<br>
				(이메일 공급자가 이메일을 스팸으로 식별할 수도 있습니다.)
			</dd>
			<dd class="bullet-on">
				2. 조금만 기다려주세요.<br> 때때로 이메일 서버가 느려 이메일을 수신하는데 몇 분의 시간이 소요 될 수
				있습니다.
			</dd>
			<dd class="bullet-on">※ 여전히 이메일을 찾을 수 없다면 다른 이메일을 사용해 주세요.</dd>
		</dl>
	</div>
	<!-- Popover content // -->
	<!-- //Popover content -->
	<script>
		$(function() {
			/* modal 열때 tooltip 닫음 */
			$('[data-toggle="modal"]').click(function() {
				$('[data-toggle="popover"]').popover('hide');
			});

			$('.popover-info1')
					.popover(
							{
								trigger : 'click',
								customClass : 'popover-w-other popover-contents-over',
								container : 'body',
								html : true,
								altBoundary : true,
								sanitize : false,
								template : '<div class="popover" role="tooltip"><div class="arrow"></div><div class="tooltip-contents" role="text"><h3 class="popover-header"></h3><div class="popover-body"></div></div><button type="button" class="popover-close"><span class="sr-only">닫기</span></button></div>',
								title : '<span class="popover-tit mb-2"><i class="bi bi-information-tooltip align-text-top"></i>알뜰폰 이동통신사를 확인하세요</span>',
								content : function() {
									return $("#PopoverContent1").html();
								}
							}).on('shown.bs.popover', $popoverClosable)

			$('.popover-info2')
					.popover(
							{
								trigger : 'click',
								customClass : 'popover-w-other popover-contents-over',
								container : 'body',
								html : true,
								altBoundary : true,
								sanitize : false,
								template : '<div class="popover" role="tooltip"><div class="arrow"></div><div class="tooltip-contents" role="text"><h3 class="popover-header"></h3><div class="popover-body"></div></div><button type="button" class="popover-close"><span class="sr-only">닫기</span></button></div>',
								title : '<span class="popover-tit mb-2"><i class="bi bi-information-tooltip align-text-top"></i>이메일을 받지 못한 경우 아래와 같은 방법을 시도해 주세요.</span>',
								content : function() {
									return $("#PopoverContent2").html();
								}
							}).on('shown.bs.popover', $popoverClosable)
		});
	</script>
	<div class="lgacc lgacc-ms-contents">
		<div class="container-fluid px-0 lgacc-container lgacc-nav-empty">

			<!-- [[ lgacc-contents -->
			<div class="container justify-content-start lgacc-contents"
				id="mainContents">
				<div class="row flex-grow-1 mb-4 mb-sm-5 pt-3 pt-sm-5">
					<!-- 이메일 아이디 -->
					<div class="col-12 d-flex flex-column pt-0 pt-sm-2">
						<div class="row flex-column flex-grow-1 no-gutters">

							<div class="lgacc-content-card">

								<div class="row justify-content-md-center ">
									<div class="col">

										<div class="card lgacc-login-card lgacc-card mx-auto mt-3">
											<div class="card-body px-0 py-0 py-sm-4 px-sm-px30">
												<form id="emailID" action="" method="post"
													autocomplete="off">
													<div class="form-group  text-left pt-sm-3">
														<label class="input-label" for="signinId"><span
															role="text">새 이메일 아이디<span class="required-info">필수입력</span></span><span
															class="sr-only">.</span></label> <input type="email"
															class="form-control text-rtl" enterkeyhint="done"
															name="signinId" id="signinId" aria-invalid=""
															oninput="this.value = this.value.replace(/[^0-9a-z\!$\&\'\*\'\*\+\-\=\^\_\~\@\.]/g, '');"
															placeholder='새 이메일 아이디를 입력해 주세요' required>
														<div class="invalid-feedback error email register"
															id="register" role="alert" style="display: none;">
															<span role="text">이미 사용 중인 이메일입니다. 다른 이메일을 입력해
																주세요.</span>
														</div>
														<div class="invalid-feedback error email wrong" id="wrong"
															role="alert" style="display: none;">
															<span role="text">이메일 아이디를 정확하게 입력해 주세요.</span>
														</div>
													</div>

													<div class="row pt-4 pt-sm-px34 mb-sm-px30" id="first-step">
														<div class="col pb-1 text-center">
															<button type="button" id="certiCodeBtn"
																class="btn btn-w320 btn-primary" disabled>인증 메일
																발송</button>
														</div>
													</div>
													<div id="second-step" style="display: none;">
														<div class="form-group valid-mark-no text-left pt-1">
															<label class="input-label" for="certificationCode"
																tabindex="1" id="certificationCodeLabel"
																style="outline: 0;"><span role="text">인증번호<span
																	class="required-info">필수입력</span></span></label>
															<button type="button" class="popover-info2" tabindex="0"
																data-placement="top">
																<i
																	class="bi bi-information-tooltip align-text-top text-hide">인증번호
																	설명</i>
															</button>
															<div class="input-group input-certification">
																<div class="input-group-prepend">
																	<input type="text" inputmode="numeric"
																		class="form-control txt-space-1"
																		oninput="this.value = this.value.replace(/[^0-9]/g, '');"
																		pattern="\d*" maxlength='6' value=""
																		name="certificationCode" id="certificationCode"
																		placeholder="●●●●●●" required>
																	<div class="valid-feedback code success" role="alert"
																		style="display: none;">
																		<span role="text">인증되었습니다.</span>
																	</div>
																	<div class="invalid-feedback code wrong" id="wrong"
																		role="alert" style="display: none;">
																		<span role="text">잘못된 인증번호입니다.<br> 인증번호를
																			다시 확인해 주세요.
																		</span>
																	</div>
																	<div class="invalid-feedback code limit" id="limit"
																		role="alert" style="display: none;">
																		<span role="text">인증 시간이 초과되었습니다.<br>
																			인증번호를 재발송하여 다시 확인해 주세요.
																		</span>
																	</div>
																	<div class="invalid-feedback code restrict"
																		id="restrict" role="alert" style="display: none;">
																		<span role="text">5회 이상 요청 시 5분간 재설정 메일을 발송할 수
																			없습니다.<br> 잠시 후 다시 시도해 주시기 바랍니다.
																		</span>
																	</div>
																</div>
																<div class="input-group-append">
																	<div class="time-set mx-0" role="text"
																		aria-label=":03:00">
																		<span aria-hidden="true">03:00</span>
																	</div>
																</div>
															</div>

															<div class="btn-box justify-content-end pt-2">
																<button type="button" id="btnEmailConfirm"
																	class="btn btn-sm btn-outline-ghost-week flex-grow-1"
																	title="새 창 열림">메일함으로 이동</button>
																<button type="button" id="reCertiCodeBtn"
																	class="btn btn-sm btn-outline-ghost-week flex-grow-1"
																	disabled>재발송</button>
															</div>
														</div>

														<div class="row pt-4 pt-sm-px34 mb-sm-px30">
															<div class="col pb-1 text-center">
																<button type="button" id="submitButton"
																	class="btn btn-w320 btn-primary" disabled>완료</button>
															</div>
														</div>
													</div>
													<input type="hidden" name="type" id="type" value="E">
													<input type="hidden" name="newYn" id="newYn" value="N">
													<input type="hidden" name="email" id="email"
														value="jyeory@gmail.com">
												</form>
											</div>
										</div>
									</div>
								</div>

							</div>

						</div>
					</div>
					<!-- 이메일 아이디 // -->
				</div>
			</div>
			<!-- // ]] -->
		</div>
		<!-- //contents -->

		<!-- Toast -->
		<div class="lgacc-toast-box position-fixed">
			<div id="accountToast" class="toast lgacc-toast hide mb-4"
				role="alert" aria-live="assertive" aria-atomic="true"
				data-delay="3000">
				<div class="toast-body"></div>
			</div>
		</div>

	</div>


	<script
		src="https://kr.lgemembers.com/js/profile/profile.js?v=202402270658"></script>
	<script>
		var certiCodeBtn = $("#certiCodeBtn");
		var login = getSessionStorageObject('signIn_data');
		var crtDate = getSessionStorageObject('signIn_data').account.crtDate;
		var referrer = document.referrer;
		var svcCode = getSessionStorageObject('svcCode');
		var country = 'KR';
		var language = 'ko';
		var smsAuthCntry = 'KR';

		$(document).ready(function() {
			tooltip('닫기');
		})

		/* 뒤로가기 버튼 이벤트 */
		$("#backIcon")
				.on(
						"click",
						function() {
							openConfirmPopup({
								confirmId : 'BACK_BTN',
								callback : function() {
									if (referrer
											.indexOf('/profile/accountInfo') != -1) {
										//                  doFormSubmit("/lgacc/service/v1/profile/accountInfo", "POST", {userAuth1: '', userAuth2: ''});
										if (svcCode == 'SVC709') { /* Front */
											retrieveProfile(country, language,
													smsAuthCntry);
											return false;
										} else { /* Service */
											var profileParam = getSessionStorageObject('profile_param');
											doFormSubmit(
													"/lgacc/service/v1/profile/retrieveProfile",
													"GET", profileParam);
											return false;
										}
									} else {
										window.history.go(-2);
									}
								}
							});
						});

		/* 아이디 중복 확인 */
		$("#signinId").on(
				"keyup paste",
				function(e) {
					var currentVal = $(this).val();

					if (e.keyCode == 13) {
						$(this).blur();
					}

					if (checkEmail($("#signinId").val())) {
						var domain = $("#signinId").val().split('@');
						var dot = domain[1].split('.');
						if ((e.keyCode > 31 && e.keyCode < 128)
								|| e.keyCode == 8 || e.keyCode == 9
								|| e.keyCode == 187 || e.keyCode == 189
								|| e.keyCode == 192
								|| !$("#signinId").is(":focus")
								|| $("#signinId").is(":focus")) {
							if (dot.length - 1 == 1
									&& dot[dot.length - 1].length == 3)
								$("#signinId").blur();
							else if (dot.length - 1 > 1
									&& dot[dot.length - 1].length == 2)
								$("#signinId").blur();
							$('.email.wrong').hide();
							checkEmailId();
						}
					} else {
						$('.email.wrong').show();
						$('.email.register').hide();
						$("#signinId").addClass("is-invalid").removeClass(
								"is-valid");
						$("#signinId").attr("aria-invalid", true).attr(
								"aria-describedby", "wrong");
						$('#certiCodeBtn').prop("disabled", true);
					}
				})

		var count = 0;

		/* sendButton */
		certiCodeBtn.on("click",
				function() {
					checkEmailId(function() {
						/* 발송 버튼 숨기기 */
						$('#first-step').hide();
						/* 입력한 아이디 비활성화 */
						$("#signinId").prop("disabled", true);
						/* 타이머 시작 */
						set_timer($(".time-set"), [ 0, 0, 3, 0 ], 'N', "분",
								"초", function() {
									$("#certificationCode").addClass(
											"is-invalid").removeClass(
											"is-valid");
									$("#certificationCode").siblings(
											".invalid-feedback").css("display",
											"none");
									if (!$('.code.restrict').is(':visible')) {
										$('.code.limit').show();
										$("#certificationCode").attr(
												'aria-invalid', true).attr(
												'aria-describedby', 'limit');
									}
									$(".time-set").hide();
									$("#certificationCode").prop("disabled",
											true);
									$("#certificationCode").val('');
									$("#reCertiCodeBtn")
											.prop("disabled", false);
								});

						/* email 발송 */
						mailSendLogin($("#signinId").val(), crtDate);
						/* 인증번호 입력 보이기 */
						$('#second-step').show();
						$('.toast-body').text('인증번호를 발송했습니다.');
						$('#accountToast').toast('show');
						$("#certificationCodeLabel").focus();

						/* goToInbox 버튼 show/hide */
						var emailDomail = $('#signinId').val().split("@")[1];
						doPostUrl("/lgacc/service/v1/emailDomain", {
							email : emailDomail
						}, function(e) {
							if (e === "goBox") {
								$("#btnEmailConfirm").show();
							} else if (e === "noBox") {
								$("#btnEmailConfirm").hide();
							}
						})
					})

				})
		/* 받은 메일함으로 이동 */
		$("#btnEmailConfirm").on(
				"click",
				function(e) {
					var emailDomail = $('#signinId').val().split("@")[1];
					var domain = 'https://' + emailDomail
					tracelog(emailDomail);
					// [REQEMP-11020][프라엘케어앱] 메일 인증을 위해 메일함 이동 동작성 문의
					if (svcCode == "SVC202" || svcCode == "SVC316") {
						if (typeof window.empBridge != 'undefined') {
							empBridge.startOutLink(domain);
						} else {
							emailChk($('#signinId').val());
						}
					} else {
						var url = "http://www." + emailDomail;
						if (emailDomail == "naver.com") {
							window.open("http://mail.naver.com", 'email site',
									'width=900,height=1000');
						} else {
							window.open(url, 'email site',
									'width=900,height=1000');
						}
					}

				});

		function checkEmailId(nextFnc) {
			doPostUrl("/lgacc/service/v1/signup/retrieveDupUserIdOnlyLge", {
				type : "email",
				userId : $("#signinId").val()
			}, function(dt) {
				if (dt.error === 200) {
					$('.email.register').hide();
					$('.email.register').hide();
					$("#signinId").attr("aria-invalid", false).attr(
							"aria-describedby", "");
					$("#signinId").addClass("is-valid").removeClass(
							"is-invalid");
					$('#certiCodeBtn').prop("disabled", false);

					if (nextFnc) {
						nextFnc();
					}

					return false;
				} else if (dt.code == 'MR.001.07' || dt.code == 'MR.001.08'
						|| dt.code == 'MR.001.08') {
					$('.email.wrong').show();
					$('.email.register').hide();
					$("#signinId").addClass("is-invalid").removeClass(
							"is-valid");
					$("#signinId").attr("aria-invalid", true).attr(
							"aria-describedby", "wrong");
					$('#certiCodeBtn').prop("disabled", true);
					return false;
				} else {
					$('.email.wrong').hide();
					$('.email.register').show();
					$("#signinId").addClass("is-invalid").removeClass(
							"is-valid");
					$("#signinId").attr("aria-invalid", true).attr(
							"aria-describedby", "register");
					$('#certiCodeBtn').prop("disabled", true);

					return false;
				}
			});
		}

		/* 재발송 */
		$('#reCertiCodeBtn').on(
				"click",
				function() {
					/* 재발송 버튼 비활성화 */
					$('#reCertiCodeBtn').prop("disabled", true);
					/* 타이머 중단 */
					clearInterval(timer);

					/* 5회 이상 발송 시 제한 */
					++count;
					$('.code.limit').hide(); /* timeout 안내 문구 */
					$("#certificationCode").removeAttr(
							'aria-invalid aria-describedby');
					$(".time-set").show();

					if (count > 4) {
						$('.code.restrict').show();
						$("#certificationCode").attr('aria-invalid', true)
								.attr('aria-describedby', 'restrict');
						/* 타이머 시작 */
						set_timer($(".time-set"), [ 0, 0, 5, 0 ], 'N', "분",
								"초", function() {
									$("#certificationCode").siblings(
											".invalid-feedback").css("display",
											"none");
									$("#certificationCode").addClass(
											"is-invalid").removeClass(
											"is-valid");
									$("#certificationCode").attr(
											'aria-invalid', true).attr(
											'aria-describedby', 'limit');
									$("#certificationCode").prop("disabled",
											true);
									$("#certificationCode").val('');
									$("#reCertiCodeBtn")
											.prop("disabled", false);
								});
						count = 0; /* 횟수 초기화 */
					} else {
						$('.code.restrict').hide();
						$("#certificationCode").removeAttr(
								'aria-invalid aria-describedby');
						/* 이메일 발송 */
						mailSendLogin($("#signinId").val(), crtDate);
						/* 타이머 시작 */
						set_timer($(".time-set"), [ 0, 0, 3, 0 ], 'N', "분",
								"초", function() {
									$("#certificationCode").addClass(
											"is-invalid").removeClass(
											"is-valid");
									$("#certificationCode").siblings(
											".invalid-feedback").css("display",
											"none");
									if (!$('.code.restrict').is(':visible')) {
										$('.code.limit').show();
										$("#certificationCode").attr(
												'aria-invalid', true).attr(
												'aria-describedby', 'limit');
									}
									$(".time-set").hide();
									$("#certificationCode").prop("disabled",
											true);
									$("#certificationCode").val('');
									$("#reCertiCodeBtn")
											.prop("disabled", false);
								});
						/* 인증번호 입력 활성화 */
						$("#certificationCode").prop("disabled", false);
						/* 토스트 메세지 노출 */
						$('.toast-body').text('인증번호를 재발송했습니다.');
						$('#accountToast').toast('show');
						$("#certificationCodeLabel").focus();
					}
				})

		/**
		 * 확인코드 입력 -> 재발송 버튼 활성화 여부 체크
		 */
		$("#certificationCode").on(
				"keyup paste",
				function(e) {
					var authCode = $(this).val();

					if (e.keyCode == 13) {
						$(this).blur();
					}

					if (authCode.length === 6) {
						/* 인증코드 확인 */
						doCallUrl("/lgacc/service/v1/emailAuthChk", "authoNo="
								+ authCode, function(dt) {
							if (dt.error === 0) {
								$('.code.wrong').hide();
								$('.code.success').show();
								$("#certificationCode").removeAttr(
										'aria-invalid aria-describedby');
								$("#certificationCode").addClass("is-valid")
										.removeClass("is-invalid");
								/* 타이머 중단 */
								clearInterval(timer);
								sessionStorage.removeItem('timer_start');
								$(".time-set").hide();
								/* 인증완료 되면 확인코드, 재발송, 메일함 disabled / 완료버튼 활성화 */
								$("#certificationCode").prop("disabled", true);
								$("#btnReSend").prop("disabled", true);
								$("#btnEmailConfirm").prop("disabled", true);
								$("#submitButton").prop("disabled", false);
							} else {
								$('.code.wrong').show();
								$(this).attr("aria-invalid", true).attr(
										"aria-describedby", "wrong");
								$("#certificationCode").addClass("is-invalid")
										.removeClass("is-valid");
							}
						});
					} else {
						$('.code.wrong').hide();
						$("#certificationCode").removeAttr(
								'aria-invalid aria-describedby');
						$("#certificationCode").removeClass("is-invalid");
					}
				});

		$('#submitButton').on(
				"click",
				function() {
					/* 이메일 아이디 변경 */
					tracelog($('#email').val())
					doPostUrl("/lgacc/service/v1/profile/updateUserId", {
						newId : $('#signinId').val(),
						userId : $('#email').val(),
						userNo : login.account.userNo,
						uuid : getLocalStorageObject('gfp_uuid') || '',
						svcUsrId : login.account.svcUsrId | ''
					}, function(data) {
						tracelog(data)
						if (data.error == null) {
							doFormSubmit('/lgacc/service/v1/profile/resultId',
									'POST', {
										signinId : $('#signinId').val(),
										type : 'E',
										newYn : 'N',
										email : 'jyeory@gmail.com',
										idType : login.account.userIDType
									})
						} else {
							tracelog("오류");
							window.location.reload();
						}
					})
				})
	</script>
	<script src="https://kr.lgemembers.com/js/scroll-shadow.js"></script>
</body>
</html>