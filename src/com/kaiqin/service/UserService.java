package com.kaiqin.service;

import java.util.List;

import com.kaiqin.domain.User;

public interface UserService {
	boolean save(User user);

	User getUserByName(String name);

	List<User> selectAll();

	boolean checkName(String name);

	boolean checkEmail(String email);

	boolean checkLogin(String name);

	boolean activateUser(String email, String code);

	int deleteByid(int id);

	List<User> usersearch(String username, String gender);
	
	//根据用户id找用户
	User findUserByUid(int uid);
}
