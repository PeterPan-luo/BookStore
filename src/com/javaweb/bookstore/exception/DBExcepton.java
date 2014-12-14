package com.javaweb.bookstore.exception;

public class DBExcepton extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DBExcepton() {
		// TODO Auto-generated constructor stub
	}
	
	public DBExcepton(String msg)
	{
		super(msg);
	}
	
	
	public DBExcepton(String msg, Exception ex)
	{
		super(msg,ex);
	}

}
