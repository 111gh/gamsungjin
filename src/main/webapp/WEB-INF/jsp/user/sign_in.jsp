<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="w-50">
	<div class="d-flex justify-content-center">
		<h1 id="logo" class="mb-5"><a href="/main/main_view">gamsungjin</a></h1>
	</div>
	
	<form id="loginForm" method="post" action="/user/sign_in">
		<!-- 아이디 입력 -->
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">ID</span>
			</div>
			<input type="text" class="form-control" name="loginId" placeholder="아이디">
		</div>
		
		<!-- 비밀번호 입력 -->
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<span class="input-group-text">PW</span>
			</div>
			<input type="password" class="form-control" name="password" placeholder="비밀번호">
		</div>
		
		<!-- 회원가입, 로그인 버튼 -->
		<div class="d-flex">
			<a href="/user/sign_up_view" class="btn btn-secondary w-100 mr-2">회원가입</a>
			<button type="submit" class="btn btn-primary w-100">로그인</button>
		</div>
	</form>
</div>

<script>
	$(document).ready(function() {
		$('#loginForm').submit(function(e) {
			e.preventDefault();	// submit 수행 중단
			
			// 입력 확인
			let loginId = $('input[name=loginId]').val().trim();
			if (loginId == "") {
				alert("아이디를 입력해주세요.");
				return;
			}
			
			let password = $('input[name=password]').val().trim();
			if (password == "") {
				alert("비밀번호를 입력해주세요.");
				return;
			}
			
			// ajax로 보내기
			$.ajax({
				url: $(this).attr('action')
				, type: 'post'
				, data: {'loginId':loginId, 'password':password}
				, success: function(data) {
					if (data.result == "success") {
						alert("로그인 성공.")
						location.href = '/main/main_view';
					} else {
						alert("로그인 실패. 다시 확인해주세요.")
					}
				}, error: function(e) {
					alert("오류가 발생했습니다. 관리자에게 문의해주세요." + e)
				}
			});
		});
	});
</script>
