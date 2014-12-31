package com.javaweb.bookstore.dao.impl;

import com.javaweb.bookstore.dao.AccountDAO;
import com.javaweb.bookstore.domain.Account;

public class AccountDAOImpl extends BaseDAO<Account> implements AccountDAO {

	@Override
	public Account getAccountById(int accountId) {
		String sql = "select accountid, balance from account where accountid = ?";
		return query(sql, accountId);
	}

	@Override
	public void updateBalance(int accoundId, float amount) {
		String sql = "update account set balance = balance - ? where accountid = ?";
		update(sql, amount, accoundId);
	}

}
