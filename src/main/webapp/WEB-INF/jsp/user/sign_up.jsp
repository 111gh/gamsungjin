<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="w-50">
	<div class="d-flex justify-content-center">
		<h1 class="mb-5">회원가입</h1>
	</div>
	
	<form id="signUpForm" method="post" action="/user/sign_up">
		<!-- 아이디 -->
		<span>아이디</span>
		<div class="input-group mt-2 mb-3">
			<input type="text" class="form-control mr-3" name="loginId" placeholder="아이디를 입력해주세요.">
			<button type="button" id="loginIdCheckBtn" class="btn btn-secondary">중복확인</button>
		</div>
		
		<div id="idCheckLength" class="small text-danger ml-3 d-none">아이디를 4자 이상 입력해주세요.</div>
		<div id="idCheckDuplicated" class="small text-danger ml-3 d-none">사용중인 아이디입니다.</div>
		<div id="idCheckOk" class="small text-success ml-3 d-none">사용 가능한 아이디입니다.</div>
		
		<!-- 비밀번호 -->
		<span>비밀번호</span>
		<div class="mt-2 mb-3">
			<input type="password" class="form-control mb-2" name="password" placeholder="비밀번호를 입력해주세요.">
			<input type="password" class="form-control" name="passwordCheck" placeholder="비밀번호를 확인합니다.">
		</div>
		
		<div id="pwCheckNotOk" class="small text-danger ml-3 d-none">비밀번호가 일치하지 않습니다.</div>
		<div id="pwCheckOk" class="small text-success ml-3 d-none">비밀번호가 일치합니다.</div>
		
		<!-- 닉네임 -->
		<span>닉네임</span>
		<div class="mt-2 mb-3">
			<input type="text" class="form-control" name="nickname" placeholder="닉네임을 입력해주세요.">
		</div>
		
		<!-- 회원가입, 로그인 버튼 -->
		<div class="d-flex">
			<a href="/user/sign_in_view" class="btn btn-secondary w-100 mr-2">취소</a>
			<button type="submit" class="btn btn-primary w-100">완료</button>
		</div>
	</form>
</div>

<script>
	$(document).ready(function() {
		$('#loginIdCheckBtn').on('click', function() {
			let loginId = $('input[name=loginId]').val().trim();
			
			// 아이디 4자이상 확인
			if (loginId.length < 4) {
				$('#idCheckLength').removeClass('d-none');
				$('#idCheckDuplicated').addClass('d-none');
				$('#idCheckOk').addClass('d-none');
				return;
			}
			
			$.ajax({
				url: '/user/is_duplicated_id'
				, type: 'get'
				, data: {'loginId':loginId}
				, success: function(data) {
					if (data.result == "success") {
						// 중복o
						$('#idCheckLength').addClass('d-none');
						$('#idCheckDuplicated').removeClass('d-none');
						$('#idCheckOk').addClass('d-none');
					} else {
						// 중복x
						$('#idCheckLength').addClass('d-none');
						$('#idCheckDuplicated').addClass('d-none');
						$('#idCheckOk').removeClass('d-none');
					}
				} , error: function(e) {
					alert("오류가 발생했습니다. 관리자에게 문의해주세요." + e);
				}
			});
		});
		
		$('input[name=passwordCheck]').blur(function() {
			let password = $('input[name=password]').val().trim();
			let passwordCheck = $(this).val().trim();
			if (password != passwordCheck) {
				// 비밀번호 일치x
				$('#pwCheckNotOk').removeClass('d-none');
				$('#pwCheckOk').addClass('d-none');
				return;
			} else {
				// 비밀번호 일치o
				$('#pwCheckNotOk').addClass('d-none');
				$('#pwCheckOk').removeClass('d-none');
			}
		});
		
		$('#signUpForm').submit(function(e) {
			e.preventDefault();	// submit 수행 중단
			
			let loginId = $('input[name=loginId]').val().trim();
			if (loginId == "") {
				alert("아이디를 입력해주세요.");
				return;
			}
			
			let password = $('input[name=password]').val().trim();
			let passwordCheck = $('input[name=passwordCheck]').val().trim();
			if (password == "" || passwordCheck == "") {
				alert("비밀번호를 입력해주세요.");
				return;
			}
			
			let nickname = $('input[name=nickname]').val().trim();
			if (nickname == "") {
				alert("닉네임을 입력해주세요.");
				return;
			}
			
			// 아이디 중복확인, 비밀번호 일치 확인
			if ($('#idCheckOk').hasClass('d-none')) {
				alert("아이디 중복확인을 해주세요.");
				return;
			} else if ($('#pwCheckOk').hasClass('d-none')) {
				alert("비밀번호 일치확인을 해주세요.");
				return;
			}
			
			// ajax로 보내기
			$.ajax({
				url: $(this).attr('action')
				, type: 'post'
				, data: {'loginId':loginId, 'password':password, 'nickname':nickname}
				, success: function(data) {
					if (data.result == "success") {
						alert("회원가입 성공.");
						location.href = '/user/sign_in_view';
					} else {
						alert("회원가입 실패. 다시 확인해주세요.");
					}
				}, error: function(e) {
					alert("오류가 발생했습니다. 관리자에게 문의해주세요." + e)
				}
			});
		});
	});
</script>