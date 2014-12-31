package com.javaweb.bookstore.service;

import com.javaweb.bookstore.dao.UserDAO;
import com.javaweb.bookstore.dao.impl.UserDAOImpl;
import com.javaweb.bookstore.domain.User;

public class UserService {

	private UserDAO userDAO = new UserDAOImpl();
	
	public User getUserByUsername(String username) {
		return userDAO.getUser(username);
	}
	
}
