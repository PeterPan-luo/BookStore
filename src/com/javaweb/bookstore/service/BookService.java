package com.javaweb.bookstore.service;

import java.util.List;

import com.javaweb.bookstore.dao.BookDAO;
import com.javaweb.bookstore.dao.impl.BookDAOImpl;
import com.javaweb.bookstore.domain.Book;
import com.javaweb.bookstore.domain.ShoppingCar;
import com.javaweb.bookstore.web.CriteriaBook;
import com.javaweb.bookstore.web.Page;

public class BookService {

	private BookDAO bookDAO = new BookDAOImpl();
	
	public Page<Book> getPage(CriteriaBook cb)
	{
		return bookDAO.getPage(cb);
	}

	public Book getBook(int id) {
		
		return bookDAO.getBook(id);
	}

	public boolean addToCar(int id, ShoppingCar sc) {
		// TODO Auto-generated method stub
		Book book = bookDAO.getBook(id);
		if (book != null) {
			sc.addBook(book);
			return true;
		}
		return false;
	}

	public void clearShoppingCar(ShoppingCar sc) {
		// TODO Auto-generated method stub
		if (sc != null) {
			sc.clear();
		}
	}

	public void removeItemFromShoppingCart(ShoppingCar sc, int id) {
		// TODO Auto-generated method stub
		sc.removeItem(id);
	}

	public void updateItemQuantity(ShoppingCar sc, int id, int quantity) {
		// TODO Auto-generated method stub
		sc.updateItemQuantity(id, quantity);
	}

	public void cash(ShoppingCar shoppingCar, String username, String accountID) {
		// TODO Auto-generated method stub
		
	}
}
