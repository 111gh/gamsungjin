<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="ml-3">
	<h2>${content.board.name}</h2>
	
	<table class="table text-center">
		<thead>
			<tr>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="post" items="${content.postBoardList}">
			<tr>
				<td><a href="/post/detail_view?postId=${post.id}">${post.subject}</a></td>
				<td>${post.nickname}</td>
				<td>
					<fmt:formatDate value="${post.createdAt}" pattern="yyyy.MM.dd" />
				</td>
				<td>${post.visit}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<!-- 로그인 상태만 글쓰기 버튼 활성화 -->
	<c:if test="${not empty userId}">
		<div class="d-flex justify-content-end">
			<a href="/post/create_view" class="btn btn-secondary">글쓰기</a>
		</div>
	</c:if>
</div>