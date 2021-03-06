package com.javaweb.bookstore.dao;

import com.javaweb.bookstore.domain.User;

public interface UserDAO {

	/**
	 * 根据用户名获取用户信息
	 * @param username
	 * @return
	 */
	public abstract User getUser(String username);
}
