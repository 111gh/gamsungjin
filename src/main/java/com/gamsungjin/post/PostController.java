package com.gamsungjin.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gamsungjin.comment.bo.CommentBO;
import com.gamsungjin.comment.model.Comment;
import com.gamsungjin.main.bo.ContentBO;
import com.gamsungjin.main.domain.Content;
import com.gamsungjin.post.bo.PostBO;
import com.gamsungjin.post.model.Post;

@RequestMapping("/post")
@Controller
public class PostController {
	
	@Autowired
	private ContentBO contentBO;
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private CommentBO commentBO;
	
	/**
	 * 글쓰기 화면
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/create_view")
	public String postCreateView(
			Model model
			, HttpServletRequest request) {
		
		// 세션에서 로그인 유저 아이디 가져옴
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		
		// 로그아웃 상태
		if (userId == null) {
			return "redirect:/main/main_view";
		}
		
		Content nav = contentBO.getNavLogin(userId);
		
		model.addAttribute("nav", nav);
		model.addAttribute("viewName", "post/post_create.jsp");
		model.addAttribute("title", "글쓰기");
		
		return "template/main_layout";
	}
	
	/**
	 * 글보기 화면
	 * @param postId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/detail_view")
	public String postDetailView(
			@RequestParam("postId") int postId
			, Model model
			, HttpServletRequest request) {
		
		// 세션에서 로그인 유저 아이디 가져옴
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		
		Content nav = null;
		if (userId == null) {
			// 로그아웃 상태
			nav = contentBO.getNavLogout();
		} else {
			// 로그인 상태
			nav = contentBO.getNavLogin(userId);
		}
		
		// 조회수 +1
		postBO.updateVisitById(postId);
		
		Post post = postBO.getPostById(postId);
		List<Comment> commentList = commentBO.getCommentListByPostId(postId);
		
		model.addAttribute("nav", nav);
		model.addAttribute("post", post);
		model.addAttribute("commentList", commentList);
		model.addAttribute("viewName", "post/post_detail.jsp");
		model.addAttribute("title", "글보기");
		
		return "template/main_layout";
	}
	
	@RequestMapping("/update_view")
	public String postUpdateView(
			@RequestParam("postId") int postId
			, Model model
			, HttpServletRequest request) {
		
		// 세션에서 로그인 유저 아이디 가져옴
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		
		// 로그아웃 상태
		if (userId == null) {
			return "redirect:/main/main_view";
		}
		
		Content nav = contentBO.getNavLogin(userId);
		Post post = postBO.getPostById(postId);
		
		model.addAttribute("nav", nav);
		model.addAttribute("post", post);
		model.addAttribute("viewName", "post/post_update.jsp");
		model.addAttribute("title", "글수정");
		
		return "template/main_layout";
	}

}
