package com.javaweb.bookstore.dao;

import java.util.Collection;
import java.util.Set;

import com.javaweb.bookstore.domain.TradeItem;

public interface TradeItemDAO {

	/**
	 * ÅúÁ¿±£´æ
	 * @param items
	 */
	void batchSave(Collection<TradeItem> items);
	
	Set<TradeItem> getTradeItemsWithTradeId(int tradeId);
}
