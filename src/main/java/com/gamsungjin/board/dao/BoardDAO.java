package com.gamsungjin.board.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.gamsungjin.board.model.Board;

@Repository
public interface BoardDAO {
	
	public List<Board> selectBoardList();
	
	public Board selectBoardById(int id);

}
