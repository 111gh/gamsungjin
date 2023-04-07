<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="ml-3">
	<h2>글수정</h2>
	
	<select name="board" class="form-control col-3 mb-3">
		<c:forEach var="board" items="${nav.boardList}">
			<option value="${board.id}">${board.name}</option>
		</c:forEach>
	</select>
	
	<input type="text" class="form-control mb-3" name="subject" value="${post.subject}">
	
	<textarea rows="10" class="form-control mb-3" name="content">${post.content}</textarea>
	
	<div class="d-flex justify-content-between">
		<input type="file" name="file" accept=".jpg,.jpeg,.png,.gif">
		
		<div class="d-flex">
			<a href="/main/main_view" class="btn btn-primary mr-2">메인으로</a>
			<button type="button" id="updateBtn" class="btn btn-secondary"
				data-post-id="${post.id}">수정</button>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$('#updateBtn').on('click', function() {
			// 확인
			let postId = $(this).data('post-id');
			
			let subject = $('input[name=subject]').val();
			if (subject == "") {
				alert("제목을 입력해주세요.");
				return;
			}
			
			// 사진게시판만 파일 업로드 가능
			let boardId = $('select[name=board]').val();
			let file = $('input[name=file]').val();
			if (boardId != 7 && file != "") {
				alert("사진게시판만 파일을 업로드할 수 있습니다.");
				$('input[name=file]').val("");
				return;
			}
			
			// 파일이 업로드 됐을때만 확장자 체크
			if (file != "") {
				let ext = file.split('.').pop().toLowerCase();
				if ($.inArray(ext, ['jpg', 'jpeg', 'png', 'gif']) == -1) {
					alert("이미지 파일만 업로드할 수 있습니다.");
					$('input[name=file]').val("");
					return;
				}
			}
			
			// 폼 생성 -> 서버에 보내기
			let formData = new FormData();
			formData.append('postId', postId);
			formData.append('boardId', boardId);
			formData.append('subject', subject);
			formData.append('content', $('textarea[name=content]').val());
			formData.append('file', $('input[name=file]')[0].files[0]);
			
			$.ajax({
				url: '/post/update'
				, type: 'post'
				, data: formData
				
				// 파일 업로드시 필수 파라미터들
				, processData: false
				, contentType: false
				, enctype: 'multipart/form-data'
				
				, success: function(data) {
					if (data.result == "success") {
						alert("글수정 성공.");
						location.href = '/post/detail_view?postId='+ postId;
					} else {
						alert("글수정 실패. 다시 확인해주세요.")
					}
				}, error: function(e) {
					alert("오류가 발생했습니다. 관리자에게 문의해주세요" + e)
				}
			});
		});
	});
</script>