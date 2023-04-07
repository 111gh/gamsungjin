package com.gamsungjin.board.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamsungjin.board.dao.BoardDAO;
import com.gamsungjin.board.model.Board;

@Service
public class BoardBO {
	
	@Autowired
	private BoardDAO boardDAO;
	
	public List<Board> getBoardList() {
		return boardDAO.selectBoardList();
	}
	
	public Board getBoardById(int id) {
		return boardDAO.selectBoardById(id);
	}

}
