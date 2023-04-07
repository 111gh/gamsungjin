package com.gamsungjin.visit.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface VisitDAO {
	
	public void insertVisit(int userId);
	
	public int selectVisitCountByUserId(int userId);
}
