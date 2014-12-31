package com.javaweb.bookstore.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCar {

	//���ﳵ����Ʒ���ϣ�key��Ӧ��Ʒ��id
	private Map<Integer, ShoppingCarItem> booksMap = new HashMap<>();
	
	/**
	 * �޸�ָ�������������
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
	 * �Ƴ�ָ��������
	 * @param id
	 */
	public void removeItem(Integer id) {
		booksMap.remove(id);
	}
	/**
	 * ��鹺�ﳵ����û�и���Ʒ, ����, ��ʹ������ +1, ��û��, 
	 * �´������Ӧ�� ShoppingCartItem, ��������뵽 books ��
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
	 * ���ع��ﳵ����Ʒ��������
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
	 * ��չ��ﳵ
	 */
	public void clear() {
		booksMap.clear();
	}
	/**
	 * ���ﳵ�Ƿ�Ϊ��
	 * @return
	 */
	public boolean isEmpty() {
		return booksMap.isEmpty();
	}
	/**
	 * ��ȡ���ﳵ���ܹ����
	 * @return
	 */
	public float getTotalMoney() {
		float total = 0;
		for(ShoppingCarItem item : getItems())
			total += item.getItemMoney();
		return total;
	}
	/**
	 * ��ȡ���ﳵ�е����е� ShoppingCartItem ��ɵļ���
	 * @return
	 */
	public Collection<ShoppingCarItem> getItems() {
		return booksMap.values();
	}
}
