<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="ml-3">
	<div class="ml-2">
	<!-- 게시판 이름, 글제목, 작성자 닉네임 -->
		<h5>${post.boardName}</h5>
		<h3><b>${post.subject}</b></h3>
		
		<div class="d-flex">
			<img src="http://simpleicon.com/wp-content/uploads/user1.png" alt="user_icon" width="25px" height="25px">
			<h5>${post.nickname}</h5>
		</div>
		
	</div>
	<hr>
	
	<!-- 이미지 -->
	<c:if test="${not empty post.imagePath}">
		<div class="mb-2">
			<img src="${post.imagePath}" alt="이미지" width="200px">
		</div>
	</c:if>
	
	<!-- 글내용 -->
	<div>
		${post.content}
	</div>
	<hr>
	
	<!-- 삭제, 수정 버튼 -->
	<!-- 로그인 유저와 글 작성자가 같을 때 -->
	<c:if test="${userId eq post.userId}">
		<div class="d-flex justify-content-end">
			<button type="button" id="postDelBtn" class="btn btn-primary mr-2"
				data-board-id="${post.boardId}" data-post-id="${post.id}">삭제</button>
			<a href="/post/update_view?postId=${post.id}" class="btn btn-secondary">수정</a>
		</div>
	</c:if>
	
	<!-- 댓글 -->
	<h5><b>댓글</b></h5>
	<c:forEach var="comment" items="${commentList}">
		<div>
			<img src="http://simpleicon.com/wp-content/uploads/user1.png" alt="user_icon" width="25px" height="25px">
			<span class="mr-5">${comment.nickname}</span>
			<span>${comment.content}</span>
			
			<!-- 로그인 유저와 댓글 작성 유저가 같아야 삭제버튼이 보임 -->
			<c:if test="${userId eq comment.userId}">
				<img src="https://www.iconninja.com/files/603/22/506/x-icon.png"
					class="btn commentDelBtn" alt="del_icon" height="25px" data-comment-id="${comment.id}">
			</c:if>
		</div>
	</c:forEach>
	
	<!-- 로그인 상태에서만 댓글쓰기 활성화 -->
	<c:if test="${not empty userId}">
		<div class="input-group mt-2">
			<input type="text" class="form-control" name="content" placeholder="댓글을 입력해주세요.">
			<div class="input-group-append">
				<button type="button" id="commentUpBtn" class="btn btn-secondary btn-sm"
					data-post-id="${post.id}">등록</button>
			</div>
		</div>
	</c:if>
</div>

<script>
	$(document).ready(function() {	
		// 글삭제
		$('#postDelBtn').on('click', function() {
			let boardId = $(this).data('board-id');
			let postId = $(this).data('post-id');
			
			$.ajax({
				url: '/post/delete'
				, type: 'delete'
				, data: {'postId':postId}
				, success: function(data) {
					if (data.result == "success") {
						alert("글삭제 성공.");
						location.href = '/main/main_view?boardId='+ boardId;
					} else {
						alert("글삭제 실패. 다시 확인해주세요.");
					}
				}, error: function(e) {
					alert("오류가 발생했습니다. 관리자에게 문의해주세요" + e);
				}
			});
		});
		
		// 댓글쓰기
		$('#commentUpBtn').on('click', function() {
			let postId = $(this).data('post-id');
			// 확인
			let content = $('input[name=content]').val();
			if (content == "") {
				alert("댓글을 입력해주세요.");
				return;
			}
			
			$.ajax({
				url: '/comment/create'
				, type: 'post'
				, data: {'postId':postId, 'content':content}
				, success: function(data) {
					if (data.result == "success") {
						alert("댓글쓰기 성공.");
						location.reload();
					} else {
						alert("댓글쓰기 실패. 다시 확인해주세요.");
					}
				}, error: function(e) {
					alert("오류가 발생했습니다. 관리자에게 문의해주세요" + e);
				}
			});
		});
		
		// 댓글삭제
		$('.commentDelBtn').on('click', function() {
			let commentId = $(this).data('comment-id');
			
			$.ajax({
				url: '/comment/delete'
				, type: 'delete'
				, data: {'commentId':commentId}
				, success: function(data) {
					if (data.result == "success") {
						alert("댓글삭제 성공.");
						location.reload();
					} else {
						alert("댓글삭제 실패.");
					}
				}, error: function(e) {
					alert("오류가 발생했습니다. 관리자에게 문의해주세요" + e);
				}
			});
		});
	});
</script>