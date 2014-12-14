package com.javaweb.bookstore.dao;

import java.util.List;

/**
 * Dao�ӿڣ�����DAO������������BaseDaoʵ��
 * @author Luohong
 *
 * @param <T>DAOʵ�ʲ����ķ�������
 */
public interface Dao<T> {

	/**
	 * ������������ز�����¼��ID
	 * @param sql ִ�е�sql���
	 * @param orgs ���ռλ���ɱ����
	 * @return �����¼�¼��ID
	 */
	long insert(String sql, Object...orgs);
	
	/**
	 * ִ��UPDATE����������INSERT(û�з���ֵ)��UPDATE,DELETE
	 * @param sql ִ�е�sql���
	 * @param orgs ���ռλ���ɱ����
	 */
	void update(String sql, Object...orgs);
	
	/**
	 * ִ�е�����¼�Ĳ�ѯ����, �������¼��Ӧ�����һ������
	 * @param sql sql ִ�е�sql���
	 * @param orgs ���ռλ���Ŀɱ����
	 * @return ���¼��Ӧ�����һ������
	 */
	T query(String sql, Object...orgs);
	
	/**
	 *  ִ�ж�����¼�Ĳ�ѯ����, �������¼��Ӧ�����һ�� List
	 * @param sql ��ִ�е� SQL ���
	 * @param orgs ���ռλ���Ŀɱ����
	 * @return ���¼��Ӧ�����һ�� List
	 */
	List<T> queryForList(String sql, Object...orgs);
	
	/**
	 * ִ��һ�����Ի�ֵ�Ĳ�ѯ����, �����ѯĳһ����¼��һ���ֶ�, ���ѯĳ��ͳ����Ϣ, ����Ҫ��ѯ��ֵ
	 * @param sql ��ִ�е� SQL ���
	 * @param orgs ���ռλ���Ŀɱ����
	 * @return ִ��һ�����Ի�ֵ�Ĳ�ѯ����, �����ѯĳһ����¼��һ���ֶ�, ���ѯĳ��ͳ����Ϣ, ����Ҫ��ѯ��ֵ
	 */
	<E> E getSingleVal(String sql, Object...orgs);
	
	/**
	 * ִ���������²���
	 * @param sql ��ִ�е� SQL ���
	 * @param orgs ���ռλ���Ŀɱ����
	 */
	void batch(String sql, Object[]...orgs);
}
