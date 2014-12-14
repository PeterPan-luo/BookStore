package com.javaweb.bookstore.web;

import java.util.List;

public class Page<T> {

	//当前第几页
	private int pageNo;
	
	//当前页的list
	private List<T> pageList;
	
	//每页显示多少条记录
	private int pageSize = 3;
	
	//共有多少条记录
	private long totalItemNumber;
	
	public Page(int pageNo) {
		super();
		this.pageNo = pageNo;
	}
	//需要校验一下
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
	 * 获取总页数
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
