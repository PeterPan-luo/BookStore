package com.javaweb.bookstore.dao;

import java.util.List;

import com.javaweb.bookstore.domain.Book;
import com.javaweb.bookstore.web.CriteriaBook;
import com.javaweb.bookstore.web.Page;

public interface BookDAO {
	
	/**
	 * ����id���ָ����Book����
	 * @param id
	 * @return
	 */
	public abstract Book getBook(int id); 
	
	/**
	 * ���ݴ����CriteriaBook���󷵻�Page����
	 * @param cb
	 * @return
	 */
	public abstract Page<Book> getPage(CriteriaBook cb);
	
	/**
	 * ���ݴ���� CriteriaBook ���󷵻����Ӧ�ļ�¼��
	 * @param cb
	 * @return
	 */
	public abstract long getTotalBookNum(CriteriaBook cb);
	
	/**
	 * ���ݴ���� CriteriaBook �� pageSize ���ص�ǰҳ��Ӧ�� List 
	 * @param cb
	 * @param pageNo
	 * @return
	 */
	public abstract List<Book> getPageList(CriteriaBook cb, int pageNo);
	
	/**
	 * ����ָ�� id �� book �� storeNumber �ֶε�ֵ
	 * @param id
	 */
	public abstract int getStoreNumber(int id);
}
