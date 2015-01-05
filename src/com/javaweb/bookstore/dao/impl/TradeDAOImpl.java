package com.javaweb.bookstore.dao.impl;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.javaweb.bookstore.dao.TradeDAO;
import com.javaweb.bookstore.domain.Trade;

public class TradeDAOImpl extends BaseDAO<Trade> implements TradeDAO {

	@Override
	public void inset(Trade trade) {
		// TODO Auto-generated method stub
		String sqlString = "INSERT INTO (userid, tradetime) VALUES(?, ?)";
		long tradeId = insert(sqlString, trade.getUserId(), trade.getTradeTime());
		trade.setTradeId((int)tradeId);
	}

	@Override
	public Set<Trade> getTradesWithUserId(int userId) {
		String sqlString = "SELECT tradeId, tradeTime, userId From trade where userid = ? ORDER BY tradetime DESC";
		
		return new HashSet<>(queryForList(sqlString, userId));
	}

}
