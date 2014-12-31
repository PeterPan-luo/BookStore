package com.javaweb.bookstore.dao;

import com.javaweb.bookstore.domain.Account;

public interface AccountDAO {

	/**
	 * �����˻�id��ȡ�˻���Ϣ
	 * @param accountId
	 * @return
	 */
	public abstract Account getAccountById(int accountId);
	
	/**
	 * ���ݴ���� accountId, amount ����ָ���˻������: �۳� amount ָ����Ǯ��
	 * @param accoundId
	 * @param amount
	 */
	public abstract void updateBalance(int accoundId, float amount);
}
