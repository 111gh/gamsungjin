package com.gamsungjin.visit.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gamsungjin.visit.dao.VisitDAO;

@Service
public class VisitBO {
	
	@Autowired
	private VisitDAO visitDAO;
	
	public void addVisit(int userId) {
		visitDAO.insertVisit(userId);
	}
	
	public int getVisitCountByUserId(int userId) {
		return visitDAO.selectVisitCountByUserId(userId);
	}
}
