package com.javaweb.bookstore.dao;

import java.util.List;

import com.javaweb.bookstore.domain.Book;
import com.javaweb.bookstore.web.CriteriaBook;
import com.javaweb.bookstore.web.Page;

public interface BookDAO {
	
	/**
	 * 根据id获得指定的Book对象
	 * @param id
	 * @return
	 */
	public abstract Book getBook(int id); 
	
	/**
	 * 根据传入的CriteriaBook对象返回Page对象
	 * @param cb
	 * @return
	 */
	public abstract Page<Book> getPage(CriteriaBook cb);
	
	/**
	 * 根据传入的 CriteriaBook 对象返回其对应的记录数
	 * @param cb
	 * @return
	 */
	public abstract long getTotalBookNum(CriteriaBook cb);
	
	/**
	 * 根据传入的 CriteriaBook 和 pageSize 返回当前页对应的 List 
	 * @param cb
	 * @param pageNo
	 * @return
	 */
	public abstract List<Book> getPageList(CriteriaBook cb, int pageNo);
	
	/**
	 * 返回指定 id 的 book 的 storeNumber 字段的值
	 * @param id
	 */
	public abstract int getStoreNumber(int id);
}
