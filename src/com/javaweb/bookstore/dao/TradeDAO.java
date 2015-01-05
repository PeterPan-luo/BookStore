package com.javaweb.bookstore.dao;

import java.util.Set;

import com.javaweb.bookstore.domain.Trade;

public interface TradeDAO {

	/**
	 * 向数据表中插入trade对象
	 * @param trade
	 */
	void inset(Trade trade);
	
	/**
	 * 根据用户ID获得与之相关联的trade
	 * @param userId
	 * @return
	 */
	Set<Trade> getTradesWithUserId(int userId);
}
