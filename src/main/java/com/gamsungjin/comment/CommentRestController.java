package com.gamsungjin.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gamsungjin.comment.bo.CommentBO;

@RequestMapping("/comment")
@RestController
public class CommentRestController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommentBO commentBO;
	
	/**
	 * 댓글쓰기
	 * @param postId
	 * @param content
	 * @param request
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, String> commentCreate(
			@RequestParam("postId") int postId
			, @RequestParam("content") String content
			, HttpServletRequest request) {
		
		// 세션에서 유저번호, 닉네임 가져오기
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		String userNickname = (String) session.getAttribute("userNickname");
		
		// 로그인 확인
		Map<String, String> result = new HashMap<>();
		if (userId == null) {
			result.put("result", "fail");
			logger.error("[포스트 쓰기] 로그인 세션이 없습니다.");
			return result;
		}
		
		int row = commentBO.insertComment(userId, postId, userNickname, content);
		
		if (row > 0) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	@DeleteMapping("/delete")
	public Map<String, String> deleteComment(
			@RequestParam("commentId") int commentId
			, HttpServletRequest request) {
		
		// 세션에서 유저번호 가져오기
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		
		// 로그인 확인
		Map<String, String> result = new HashMap<>();
		if (userId == null) {
			result.put("result", "fail");
			logger.error("[포스트 쓰기] 로그인 세션이 없습니다.");
			return result;
		}
		
		int row = commentBO.deleteCommentById(commentId);
		
		if (row > 0) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}

}
