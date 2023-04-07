package com.gamsungjin.post;

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
import org.springframework.web.multipart.MultipartFile;

import com.gamsungjin.post.bo.PostBO;

@RequestMapping("/post")
@RestController
public class PostRestController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostBO postBO;
	
	/**
	 * 글쓰기
	 * @param boardId
	 * @param subject
	 * @param content
	 * @param file
	 * @param request
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, String> postCreate(
			@RequestParam("boardId") int boardId
			, @RequestParam("subject") String subject
			, @RequestParam(value="content", required=false) String content
			, @RequestParam(value="file", required=false) MultipartFile file
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
		
		int row = postBO.createPost(userId, boardId, userNickname, subject, content, file);
		
		if (row > 0) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	/**
	 * 글삭제
	 * @param postId
	 * @return
	 */
	@DeleteMapping("/delete")
	public Map<String, String> postDelete(
			@RequestParam("postId") int postId) {
		
		int row = postBO.deletePostById(postId);
		
		Map<String, String> result = new HashMap<>();
		if (row > 0) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
	
	/**
	 * 글수정
	 * @param postId
	 * @param boardId
	 * @param subject
	 * @param content
	 * @param file
	 * @param request
	 * @return
	 */
	@PostMapping("/update")
	public Map<String, String> postUpdate(
			@RequestParam("postId") int postId
			, @RequestParam("boardId") int boardId
			, @RequestParam("subject") String subject
			, @RequestParam(value="content", required=false) String content
			, @RequestParam(value="file", required=false) MultipartFile file
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
	
		int row = postBO.updatePostById(postId, userId, boardId, subject, content, file);
		
		if (row > 0) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
	}
}
