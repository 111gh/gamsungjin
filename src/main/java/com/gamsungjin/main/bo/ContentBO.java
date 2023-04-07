package com.gamsungjin.main.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamsungjin.board.bo.BoardBO;
import com.gamsungjin.board.model.Board;
import com.gamsungjin.comment.bo.CommentBO;
import com.gamsungjin.main.domain.Content;
import com.gamsungjin.post.bo.PostBO;
import com.gamsungjin.post.model.Post;
import com.gamsungjin.visit.bo.VisitBO;

@Service
public class ContentBO {
	
	@Autowired
	private BoardBO boardBO;
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private VisitBO visitBO;
	
	// 메인화면
	public Content getContent() {
		Content content = new Content();
		
		// 전체글목록
		// 내림차순(최신순) 10개 제한
		List<Post> postList = postBO.getPostList();
		content.setPostList(postList);
		
		// 공지사항
		// 내림차순(최신순) 5개 제한
		List<Post> postNoticeList = postBO.getPostNoticeList();
		content.setPostNoticeList(postNoticeList);
		
		// 사진게시판
		// 내림차순(최신순) 6개 제한
		List<Post> postImageList = postBO.getPostImageList();
		content.setPostImageList(postImageList);
		
		return content;
	}
	
	// 게시판
	public Content getContentByBoardId(int boardId) {
		Content content = new Content();
		
		// 선택된 게시판
		Board board = boardBO.getBoardById(boardId);
		content.setBoard(board);
		
		// 선택된 게시판 글목록
		List<Post> postList = postBO.getPostBoardList(boardId);
		content.setPostBoardList(postList);
		
		return content;
	}
	
	// 로그인 상태: 회원정보, 전체게시판
	public Content getNavLogin(int userId) {
		Content content = new Content();
		
		// 전체게시판
		List<Board> boardList = boardBO.getBoardList();
		content.setBoardList(boardList);
		
		// 포스트, 댓글 카운트, 방문수
		int postCount = postBO.getPostCountByUserId(userId);
		content.setPostCount(postCount);
		
		int commentCount = commentBO.getCommentCountByUserId(userId);
		content.setCommentCount(commentCount);
		
		int visitCount = visitBO.getVisitCountByUserId(userId);
		content.setVisitCount(visitCount);
		
		return content;
	}
	
	// 로그아웃 상태: 전체게시판
	public Content getNavLogout() {
		Content content = new Content();
		
		// 전체게시판
		List<Board> boardList = boardBO.getBoardList();
		content.setBoardList(boardList);
		
		return content;
	}
}
