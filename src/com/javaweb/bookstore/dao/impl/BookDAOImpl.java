package com.javaweb.bookstore.dao.impl;

import java.util.List;

import com.javaweb.bookstore.dao.BookDAO;
import com.javaweb.bookstore.domain.Book;
import com.javaweb.bookstore.web.CriteriaBook;
import com.javaweb.bookstore.web.Page;

public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {

	@Override
	public Book getBook(int id) {
		String sql = "select id, author, title, price," +
				"publishingdate, salesamount, storenumber, remark" +
				" from mybooks where id = ?";
		
		return query(sql, id);
	}

	@Override
	public Page<Book> getPage(CriteriaBook cb) {
		Page<Book> page = new Page<>(cb.getPageNo());
		page.setTotalItemNum(getTotalBookNum(cb));
		//校验pageNo的合法性
		cb.setPageNo(page.getPageNo());
		
		page.setList(getPageList(cb, 3));
		return null;
	}

	@Override
	public long getTotalBookNum(CriteriaBook cb) {
		String sql = "select count(id) from mybooks where price >= ? and price <= ?";
		
		return getSingleVal(sql, cb.getMinPrice(), cb.getMaxPrice());
	}

	/**
	 * mysql 使用limit分页，其中fronIndex从0开始
	 * @param cb
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<Book> getPageList(CriteriaBook cb, int pageSize) {
		String sql = "select id, author, title, price," +
				"publishingdate, salesamount, storenumber, remark" +
				" from mybooks where price >= ? and price <= ? limit ?,?";
		
		return queryForList(sql, cb.getMinPrice(), cb.getMaxPrice(), 
				(cb.getPageNo() -1) * pageSize, pageSize);
	}

	@Override
	public int getStoreNumber(int id) {
		String sql = "select storenumber from mybooks where id = ?";
		return getSingleVal(sql, id);
	}

}
