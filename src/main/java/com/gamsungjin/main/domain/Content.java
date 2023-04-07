package com.gamsungjin.main.domain;

import java.util.List;

import com.gamsungjin.board.model.Board;
import com.gamsungjin.post.model.Post;

public class Content {
	// 전체게시판
	private List<Board> boardList;
	// 전체글목록
	private List<Post> postList;
	// 공지사항 글목록
	private List<Post> postNoticeList;
	// 사진게시판 글목록
	private List<Post> postImageList;
	// 선택된 게시판 글목록
	private List<Post> postBoardList;
	// 선택된 게시판
	private Board board;
	// 글수
	private int postCount;
	// 댓글수
	private int commentCount;
	// 방문수
	private int visitCount;
	
	public List<Post> getPostNoticeList() {
		return postNoticeList;
	}
	public void setPostNoticeList(List<Post> postNoticeList) {
		this.postNoticeList = postNoticeList;
	}
	public List<Post> getPostImageList() {
		return postImageList;
	}
	public void setPostImageList(List<Post> postImageList) {
		this.postImageList = postImageList;
	}
	public int getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}
	public int getPostCount() {
		return postCount;
	}
	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}
	public int getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public List<Board> getBoardList() {
		return boardList;
	}
	public void setBoardList(List<Board> boardList) {
		this.boardList = boardList;
	}
	public List<Post> getPostList() {
		return postList;
	}
	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
	public List<Post> getPostBoardList() {
		return postBoardList;
	}
	public void setPostBoardList(List<Post> postBoardList) {
		this.postBoardList = postBoardList;
	}
}
