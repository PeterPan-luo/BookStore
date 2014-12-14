package com.javaweb.bookstore.web;

import java.util.List;

public class Page<T> {

	//��ǰ�ڼ�ҳ
	private int pageNo;
	
	//��ǰҳ��list
	private List<T> pageList;
	
	//ÿҳ��ʾ��������¼
	private int pageSize = 3;
	
	//���ж�������¼
	private long totalItemNumber;
	
	public Page(int pageNo) {
		super();
		this.pageNo = pageNo;
	}
	//��ҪУ��һ��
	public int getPageNo()
	{
		if (pageNo < 0) {
			pageNo = 0;
		}
		if (pageNo > getPageTotalNum()) {
			pageNo = getPageTotalNum();
		}
		return pageNo;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setList(List<T> list) {
		this.pageList = list;
	}
	
	public List<T> getList() {
		return pageList;
	}
	/**
	 * ��ȡ��ҳ��
	 * @return
	 */
	public int getPageTotalNum() {
		int pageTotalNum = (int)totalItemNumber / pageSize;
		if (totalItemNumber % pageSize != 0) {
			pageTotalNum++;
		}
		return pageTotalNum;
	}
	
	public void setTotalItemNum(long totalItemNum) {
		this.totalItemNumber = totalItemNum;
	}
	
	public boolean isHasNext() {
		if (getPageNo() < getPageTotalNum()) {
			return true;
		}
		return false;
	}
	
	public boolean isHaePrev() {
		if (getPageNo() > 1) {
			return true;
		}
		return false;
	}
	
	public int getNextPage() {
		if (isHasNext()) {
			return getPageNo()+1;
		}
		return getPageNo();
	}
	
	public int getPrevPage() {
		if (isHaePrev()) {
			return getPageNo() - 1;
		}
		return getPageNo();	
	}
}
