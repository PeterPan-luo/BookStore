package com.javaweb.bookstore.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.javaweb.bookstore.dao.impl.BookDAOImpl;
import com.javaweb.bookstore.domain.Book;
import com.javaweb.bookstore.web.CriteriaBook;
import com.javaweb.bookstore.web.Page;

public class BookDAOImpleTest {
	
	
	
	@Test
	public void testGetBook() {
		BookDAOImpl bookImple = new BookDAOImpl();
		Book book = bookImple.getBook(5);
		System.out.println(book);
	}

	@Test
	public void testGetPage() {
		BookDAOImpl bookImple = new BookDAOImpl();
		CriteriaBook cb = new CriteriaBook(50, 90, 5);
		Page<Book> page = bookImple.getPage(cb);
		System.out.println("pageNo: " + page.getPageNo());
		System.out.println("totalPageNumber: " + page.getPageTotalNum());
		System.out.println("list: " + page.getList());
		System.out.println("prevPage: " + page.getPrevPage());
		System.out.println("nextPage: " + page.getNextPage()); 
	}

	@Test
	public void testGetTotalBookNum() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPageList() {
		BookDAOImpl bookImple = new BookDAOImpl();
		CriteriaBook cb = new CriteriaBook(50, 90, 5);
		List<Book> books = bookImple.getPageList(cb, 3);
		System.out.println("list: " + books);
	}

	@Test
	public void testGetStoreNumber() {
		fail("Not yet implemented");
	}

}
