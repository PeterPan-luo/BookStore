package com.javaweb.bookstore.dao;

import com.javaweb.bookstore.domain.User;

public interface UserDAO {

	/**
	 * �����û�����ȡ�û���Ϣ
	 * @param username
	 * @return
	 */
	public abstract User getUser(String username);
}
