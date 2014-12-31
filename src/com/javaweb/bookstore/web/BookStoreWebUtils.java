package com.javaweb.bookstore.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.javaweb.bookstore.domain.ShoppingCar;
import com.sun.org.apache.regexp.internal.REUtil;


/**
 * ��ȡ���ﳵ����: �� session �л�ȡ, �� session ��û�иĶ���.
 * �򴴽�һ���µĹ��ﳵ����, ���뵽 session ��.
 * ����, ��ֱ�ӷ���. 
 * @author Administrator
 *
 */
public class BookStoreWebUtils {

	public static ShoppingCar getShoppingCar(HttpServletRequest request) {
		HttpSession session = request.getSession();
		ShoppingCar sc = (ShoppingCar)session.getAttribute("ShoppingCar");
		if (sc == null) {
			sc = new ShoppingCar();
			session.setAttribute("ShoppingCar", sc);
		}
		return sc;
	}
}
