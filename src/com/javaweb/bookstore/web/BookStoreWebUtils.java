package com.javaweb.bookstore.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.javaweb.bookstore.domain.ShoppingCar;
import com.sun.org.apache.regexp.internal.REUtil;


/**
 * 获取购物车对象: 从 session 中获取, 若 session 中没有改对象.
 * 则创建一个新的购物车对象, 放入到 session 中.
 * 若有, 则直接返回. 
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
