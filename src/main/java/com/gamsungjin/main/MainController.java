package com.gamsungjin.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gamsungjin.comment.bo.CommentBO;
import com.gamsungjin.main.bo.ContentBO;
import com.gamsungjin.main.domain.Content;
import com.gamsungjin.post.bo.PostBO;

@RequestMapping("/main")
@Controller
public class MainController {
	
	
	@Autowired
	private ContentBO contentBO;
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private CommentBO commentBO;
	
	/**
	 * 메인화면
	 * @param model
	 * @return
	 */
	@RequestMapping("/main_view")
	public String mainView(
			@RequestParam(value="boardId", required=false) Integer boardId
			, Model model
			, HttpServletRequest request) {
		
		// 세션에서 로그인 유저 아이디 가져옴
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		
		if (userId != null) {
			int postCount = postBO.getPostCountByUserId(userId);
			int commentCount = commentBO.getCommentCountByUserId(userId);
			model.addAttribute("postCount", postCount);
			model.addAttribute("commentCount", commentCount);
		}
		
		Content nav = null;
		if (userId != null) {
			// 로그인 상태
			nav = contentBO.getNavLogin(userId);
		} else {
			// 로그아웃 상태
			nav = contentBO.getNavLogout();
		}
		
		Content content = null;
		if (boardId == null) {
			// 메인화면으로 접속
			content = contentBO.getContent();
			model.addAttribute("viewName", "main/main.jsp");
		} else {
			// 게시판으로 접속
			content = contentBO.getContentByBoardId(boardId);
			model.addAttribute("viewName", "main/board.jsp");
		}
		
		model.addAttribute("nav", nav);
		model.addAttribute("content", content);
		model.addAttribute("title", "gamsungjin");
		
		return "template/main_layout";
	}
	
	@RequestMapping("/search_view")
	public String searchView(
			@RequestParam("searchText") String searchText
			, Model model
			, HttpServletRequest request) {
		
		// 세션에서 로그인 유저 아이디 가져옴
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		
		Content nav = null;
		if (userId != null) {
			// 로그인 상태
			nav = contentBO.getNavLogin(userId);
		} else {
			// 로그아웃 상태
			nav = contentBO.getNavLogout();
		}
		
		model.addAttribute("nav", nav);
		model.addAttribute("title", "검색 확인");
		 
		return "template/main_layout";
	}
}
