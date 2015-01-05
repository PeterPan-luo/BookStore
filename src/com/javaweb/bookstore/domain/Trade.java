package com.javaweb.bookstore.domain;

import java.sql.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import com.javaweb.bookstore.domain.TradeItem;;

public class Trade {

	private Integer tradeId;
	
	private Date tradeTime;
	
	private Integer userId;
	
	private Set<TradeItem> items = new LinkedHashSet<>();
	

	public Set<TradeItem> getItems() {
		return items;
	}

	public void setItems(Set<TradeItem> items) {
		this.items = items;
	}

	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
