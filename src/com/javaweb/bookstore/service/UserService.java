package com.javaweb.bookstore.service;

import java.util.Iterator;
import java.util.Set;

import com.javaweb.bookstore.dao.BookDAO;
import com.javaweb.bookstore.dao.TradeDAO;
import com.javaweb.bookstore.dao.TradeItemDAO;
import com.javaweb.bookstore.dao.UserDAO;
import com.javaweb.bookstore.dao.impl.BookDAOImpl;
import com.javaweb.bookstore.dao.impl.TradeDAOImpl;
import com.javaweb.bookstore.dao.impl.TradeItemDAOImpl;
import com.javaweb.bookstore.dao.impl.UserDAOImpl;
import com.javaweb.bookstore.domain.Trade;
import com.javaweb.bookstore.domain.TradeItem;
import com.javaweb.bookstore.domain.User;

public class UserService {

	private UserDAO userDAO = new UserDAOImpl();
	
	public User getUserByUsername(String username) {
		return userDAO.getUser(username);
	}
	
	private TradeDAO tradeDAO = new TradeDAOImpl();
	private TradeItemDAO tradeItemDAO = new TradeItemDAOImpl();
	private BookDAO bookDAO = new BookDAOImpl();
	
	public User getUsersWithTrade(String username) {
		User user = userDAO.getUser(username);
		if (user == null) {
			return null;
		}
		
		int userId = user.getUserid();
		Set<Trade> trades = tradeDAO.getTradesWithUserId(userId);
		if (trades != null) {
			Iterator<Trade> tradeIterator= trades.iterator();
			while (tradeIterator.hasNext()) {
				Trade trade = (Trade) tradeIterator.next();
				
				int tradeId = trade.getTradeId();
				Set<TradeItem> items = tradeItemDAO.getTradeItemsWithTradeId(tradeId);
				if (items != null) {
					for (TradeItem tradeItem : items) {
						tradeItem.setBook(bookDAO.getBook(tradeItem.getBookId()));
					}
					trade.setItems(items);
				}
				if(items == null || items.size() == 0){
					tradeIterator.remove();	
				}
			}
		}
		if(trades != null && trades.size() > 0)
			user.setTrades(trades);
		
		return user;
	}
	
}
