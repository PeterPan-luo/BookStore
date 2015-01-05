package com.javaweb.bookstore.dao;

import java.util.Set;

import com.javaweb.bookstore.domain.Trade;

public interface TradeDAO {

	/**
	 * �����ݱ��в���trade����
	 * @param trade
	 */
	void inset(Trade trade);
	
	/**
	 * �����û�ID�����֮�������trade
	 * @param userId
	 * @return
	 */
	Set<Trade> getTradesWithUserId(int userId);
}
