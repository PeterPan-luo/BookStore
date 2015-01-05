package com.javaweb.bookstore.domain;

import java.util.LinkedHashSet;
import java.util.Set;



public class User {
	
	private int userid;
	private String username;
	private int accountid;
	
	private Set<Trade> trades = new LinkedHashSet<Trade>();
	
	public Set<Trade> getTrades() {
		return trades;
	}
	public void setTrades(Set<Trade> trades) {
		this.trades = trades;
	}
	public User() {
		super();
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAccountid() {
		return accountid;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}
}
