package com.javaweb.bookstore.dao;

import java.util.List;

/**
 * Dao接口，定义DAO基本操作，由BaseDao实现
 * @author Luohong
 *
 * @param <T>DAO实际操作的泛型类型
 */
public interface Dao<T> {

	/**
	 * 插入操作，返回插入后记录的ID
	 * @param sql 执行的sql语句
	 * @param orgs 填充占位符可变参数
	 * @return 插入新纪录的ID
	 */
	long insert(String sql, Object...orgs);
	
	/**
	 * 执行UPDATE操作，包括INSERT(没有返回值)，UPDATE,DELETE
	 * @param sql 执行的sql语句
	 * @param orgs 填充占位符可变参数
	 */
	void update(String sql, Object...orgs);
	
	/**
	 * 执行单条记录的查询操作, 返回与记录对应的类的一个对象
	 * @param sql sql 执行的sql语句
	 * @param orgs 填充占位符的可变参数
	 * @return 与记录对应的类的一个对象
	 */
	T query(String sql, Object...orgs);
	
	/**
	 *  执行多条记录的查询操作, 返回与记录对应的类的一个 List
	 * @param sql 待执行的 SQL 语句
	 * @param orgs 填充占位符的可变参数
	 * @return 与记录对应的类的一个 List
	 */
	List<T> queryForList(String sql, Object...orgs);
	
	/**
	 * 执行一个属性或值的查询操作, 例如查询某一条记录的一个字段, 或查询某个统计信息, 返回要查询的值
	 * @param sql 待执行的 SQL 语句
	 * @param orgs 填充占位符的可变参数
	 * @return 执行一个属性或值的查询操作, 例如查询某一条记录的一个字段, 或查询某个统计信息, 返回要查询的值
	 */
	<E> E getSingleVal(String sql, Object...orgs);
	
	/**
	 * 执行批量更新操作
	 * @param sql 待执行的 SQL 语句
	 * @param orgs 填充占位符的可变参数
	 */
	void batch(String sql, Object[]...orgs);
}
