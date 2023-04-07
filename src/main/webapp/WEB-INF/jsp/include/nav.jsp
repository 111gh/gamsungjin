<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
	<!-- 로그아웃 상태일 때 -->
	<c:if test="${empty userId}">
		<div id="divInfo">
			<span class="m-3">로그인 해보세용!</span>
			<a href="/user/sign_in_view" class="btn btn-primary w-100">로그인</a>
		</div>
	</c:if>
	
	<!-- 로그인 상태일 때 -->
	<c:if test="${not empty userId}">
		<div id="divInfo">
			<span class="m-3">${userNickname}님 안녕하세요!</span>
			<br><br><br>
			
			<div class="ml-4 mr-4 d-flex justify-content-between">
				<span>방문수</span>
				<span>${nav.visitCount}</span>
			</div>
			<div class="ml-4 mr-4 d-flex justify-content-between">
				<span>작성글</span>
				<span>${nav.postCount}</span>
			</div>
			<div class="ml-4 mr-4 d-flex justify-content-between">
				<span>댓글</span>
				<span>${nav.commentCount}</span>
			</div>
		</div>
		
		<a href="/user/sign_out" class="btn btn-primary w-100">로그아웃</a>
	</c:if>
	
	<!-- 검색창 -->
	<div class="input-group mt-2">
		<input type="text" class="form-control" name="search_text">
		<div class="input-group-prepend">
			<button type="button" id="searchBtn" class="btn btn-success">검색</button>
		</div>
	</div>
	
	<!-- 게시판 목록 -->
	<div id="divBoard">
		<span><b>게시판</b></span>
		<hr>
		<c:forEach var="board" items="${nav.boardList}">
			<span><a href="/main/main_view?boardId=${board.id}">${board.name}</a></span>
			<br>
		</c:forEach>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#searchBtn').on('click', function() {
			let searchText = $('input[name=search_text]').val().trim();
			if (searchText == "") {
				alert("검색어를 입력하세요.");
				return;
			}
			
			location.href = "/main/search_view?searchText=" + searchText;
		});
	});
</script>