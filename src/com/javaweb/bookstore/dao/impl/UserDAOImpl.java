package com.javaweb.bookstore.dao.impl;

import com.javaweb.bookstore.dao.UserDAO;
import com.javaweb.bookstore.domain.User;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

	@Override
	public User getUser(String username) {
		String sqlString = "select userId, username, accountId from userinfo where username = ?";
		User user = query(sqlString, username);
		return user;
	}

}
