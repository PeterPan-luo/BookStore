package com.javaweb.bookstore.dao;

import com.javaweb.bookstore.domain.Account;

public interface AccountDAO {

	/**
	 * 根据账户id获取账户信息
	 * @param accountId
	 * @return
	 */
	public abstract Account getAccountById(int accountId);
	
	/**
	 * 根据传入的 accountId, amount 更新指定账户的余额: 扣除 amount 指定的钱数
	 * @param accoundId
	 * @param amount
	 */
	public abstract void updateBalance(int accoundId, float amount);
}
