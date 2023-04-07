package com.gamsungjin.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.gamsungjin.user.model.User;

@Repository
public interface UserDAO {
	
	public int insertUser(
			@Param("loginId") String loginId
			, @Param("password") String password
			, @Param("nickname") String nickname);
	
	public User selectUserByLoginId(String loginId);

	public User selectUserByLoginIdAndPassword(
			@Param("loginId") String loginId
			, @Param("password") String password);
}
