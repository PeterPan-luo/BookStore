package com.javaweb.bookstore.service;

import java.util.List;

import com.javaweb.bookstore.dao.BookDAO;
import com.javaweb.bookstore.dao.impl.BookDAOImpl;
import com.javaweb.bookstore.domain.Book;
import com.javaweb.bookstore.web.CriteriaBook;
import com.javaweb.bookstore.web.Page;

public class BookService {

	private BookDAO bookDAO = new BookDAOImpl();
	
	public Page<Book> getPage(CriteriaBook cb)
	{
		return bookDAO.getPage(cb);
	}
}
