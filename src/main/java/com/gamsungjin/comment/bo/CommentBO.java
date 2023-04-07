package com.gamsungjin.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamsungjin.comment.dao.CommentDAO;
import com.gamsungjin.comment.model.Comment;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;

	public int getCommentCountByUserId(int userId) {
		return commentDAO.getCommentCountByUserId(userId);
	}
	
	public List<Comment> getCommentListByPostId(int postId) {
		return commentDAO.selectCommentListByPostId(postId);
	}
	
	public int insertComment(int userId, int postId, String nickname, String content) {
		return commentDAO.insertComment(userId, postId, nickname, content);
	}
	
	public void deleteCommentByPostId(int postId) {
		commentDAO.deleteCommentByPostId(postId);
	}
	
	public int deleteCommentById(int id) {
		return commentDAO.deleteCommentById(id);
	}
}
