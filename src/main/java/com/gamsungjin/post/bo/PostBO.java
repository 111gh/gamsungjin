package com.gamsungjin.post.bo;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gamsungjin.comment.bo.CommentBO;
import com.gamsungjin.common.FileManagerService;
import com.gamsungjin.post.dao.PostDAO;
import com.gamsungjin.post.model.Post;

@Service
public class PostBO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}
	
	public List<Post> getPostBoardList(int boardId) {
		return postDAO.selectPostBoardList(boardId);
	}
	
	// 공지사항 글 가져오기
	public List<Post> getPostNoticeList() {
		return postDAO.selectPostNoticeList();
	}
	
	// 사진게시판 글 가져오기
	public List<Post> getPostImageList() {
		return postDAO.selectPostImageList();
	}
	
	public Post getPostById(int id) {
		return postDAO.selectPostById(id);
	}
	
	public int getPostCountByUserId(int userId) {
		return postDAO.getPostCountByUserId(userId);
	}
	
	public int createPost(int userId, int boardId, String userNickname, String subject, String content, MultipartFile file) {
		// 파일 이미지 url생성 후 db 입력
		String imagePath = null;
		if (file != null) {
			try {
				imagePath = fileManagerService.saveFile(userId, file);
			} catch (IOException e) {
				logger.error("[파일업로드] 업로드 에러" + e.getMessage());
			}
		}
		
		return postDAO.insertPost(userId, boardId, userNickname, subject, content, imagePath);
	}
	
	public int deletePostById(int id) {
		Post post = postDAO.selectPostById(id);
		String imagePath = post.getImagePath();
		
		if (imagePath != null) {
			try {
				fileManagerService.deleteFile(imagePath);
			} catch (IOException e) {
				logger.error("[파일삭제] 삭제 에러" + e.getMessage());
			}
		}
		
		commentBO.deleteCommentByPostId(id);
		
		return postDAO.deletePostById(id);
	}
	
	public int updatePostById(int postId, int userId, int boardId, String subject, String content, MultipartFile file) {
		// 새로운 파일 이미지 url 생성
		String newImagePath = null;
		if (file != null) {
			// 새로운 파일이 있을 경우
			// db 입력
			try {
				newImagePath = fileManagerService.saveFile(userId, file);
			} catch (IOException e) {
				logger.error("[파일업로드] 업로드 에러" + e.getMessage());
			}
		
			// 기존 파일 삭제
			Post post = postDAO.selectPostById(postId);
			String oldImagePath = post.getImagePath();
			
			if (oldImagePath != null) {
				try {
					fileManagerService.deleteFile(oldImagePath);
				} catch (IOException e) {
					logger.error("[파일삭제] 삭제 에러" + e.getMessage());
				}
			}
		} else {
			// 새로운 파일이 없을 경우
			Post post = postDAO.selectPostById(postId);
			String oldImagePath = post.getImagePath();
			
			if (oldImagePath != null) {
				newImagePath = oldImagePath;
			}
		}
		
		return postDAO.updatePostById(postId, boardId, subject, content, newImagePath);
	}
	
	// 조회수 업데이트
	public void updateVisitById(int id) {
		postDAO.updateVisitById(id);
	}
}
