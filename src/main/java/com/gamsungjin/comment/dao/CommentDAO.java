package com.gamsungjin.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gamsungjin.comment.model.Comment;

@Repository
public interface CommentDAO {
	
	public int getCommentCountByUserId(int userId);
	
	public List<Comment> selectCommentListByPostId(int postId);
	
	public int insertComment(
			@Param("userId") int userId
			, @Param("postId") int postId
			, @Param("nickname") String nickname
			, @Param("content") String content);

	public void deleteCommentByPostId(int postId);
	
	public int deleteCommentById(int id);
}
