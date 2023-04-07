package com.gamsungjin.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gamsungjin.post.model.Post;

@Repository
public interface PostDAO {
	
	public Post selectPostById(int id);
	
	public List<Post> selectPostList();
	
	public List<Post> selectPostBoardList(int boardId);
	
	public List<Post> selectPostNoticeList();
	
	public List<Post> selectPostImageList();
	
	public int getPostCountByUserId(int userId);
	
	public int insertPost(
			@Param("userId") int userId
			, @Param("boardId") int boardId
			, @Param("nickname") String userNickname
			, @Param("subject") String subject
			, @Param("content") String content
			, @Param("imagePath") String imagePath);
	
	public int deletePostById(int id);
	
	public int updatePostById(
			@Param("postId") int postId
			, @Param("boardId") int boardId
			, @Param("subject") String subject
			, @Param("content") String content
			, @Param("imagePath") String imagePath);
	
	public void updateVisitById(int id);
}
