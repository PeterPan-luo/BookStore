package com.javaweb.bookstore.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCar {

	//购物车的商品集合，key对应商品的id
	private Map<Integer, ShoppingCarItem> booksMap = new HashMap<>();
	
	/**
	 * 修改指定购物项的数量
	 * @param id
	 * @param quantity
	 */
	public void updateItemQuantity(Integer id, int quantity) {
		ShoppingCarItem item = booksMap.get(id);
		if (item != null) {
			item.setQuantity(quantity);
		}
	}
	/**
	 * 移除指定购物项
	 * @param id
	 */
	public void removeItem(Integer id) {
		booksMap.remove(id);
	}
	/**
	 * 检查购物车中有没有该商品, 若有, 则使其数量 +1, 若没有, 
	 * 新创建其对应的 ShoppingCartItem, 并把其加入到 books 中
	 * @param book
	 */
	public void addBook(Book book) {
		ShoppingCarItem sCarItem = booksMap.get(book.getId());
		if (sCarItem == null) {
			sCarItem = new ShoppingCarItem(book);
			booksMap.put(book.getId(), sCarItem);
		}else {
			sCarItem.increment();
		}
		
	}
	
	public Map<Integer, ShoppingCarItem> getBooks() {
		return booksMap;
	}
	/**
	 * 返回购物车中商品的总数量
	 * @return
	 */
	public int getBookNumber() {
		int totalNum = 0;
		for(ShoppingCarItem item : getItems())
		{
			totalNum += item.getQuantity();
		}
		return totalNum;
	}
	/**
	 * 清空购物车
	 */
	public void clear() {
		booksMap.clear();
	}
	/**
	 * 购物车是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return booksMap.isEmpty();
	}
	/**
	 * 获取购物车的总共金额
	 * @return
	 */
	public float getTotalMoney() {
		float total = 0;
		for(ShoppingCarItem item : getItems())
			total += item.getItemMoney();
		return total;
	}
	/**
	 * 获取购物车中的所有的 ShoppingCartItem 组成的集合
	 * @return
	 */
	public Collection<ShoppingCarItem> getItems() {
		return booksMap.values();
	}
}
