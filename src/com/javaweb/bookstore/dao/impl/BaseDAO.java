package com.javaweb.bookstore.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.javaweb.bookstore.dao.Dao;
import com.javaweb.bookstore.db.JdbcUtils;
import com.javaweb.bookstore.domain.Account;
import com.javaweb.bookstore.utils.ConnectionContext;
import com.javaweb.bookstore.utils.ReflectionUtils;

public class BaseDAO<T> implements Dao<T> {

	private QueryRunner queryRunner = new QueryRunner();
	
	private Class<T> clazz = null;
	
	public BaseDAO() {
		clazz = ReflectionUtils.getSuperGenericType(getClass());
	}
	
	@Override
	public long insert(String sql, Object... orgs) {
		long id = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = ConnectionContext.getInstance().get();
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			for (int i = 0; i < orgs.length; i++) {
				preparedStatement.setObject(i + 1, orgs[i]);
			}
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally
		{

			JdbcUtils.release(resultSet, preparedStatement);
		}
		return id;
	}

	@Override
	public void update(String sql, Object... orgs) {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = ConnectionContext.getInstance().get();
			queryRunner.update(connection, sql, orgs);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public T query(String sql, Object... orgs) {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = ConnectionContext.getInstance().get();
			return queryRunner.query(connection, sql,
					new BeanHandler<>(clazz), orgs);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public List<T> queryForList(String sql, Object... orgs) {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = ConnectionContext.getInstance().get();
			return queryRunner.query(connection, sql,
					new BeanListHandler<>(clazz), orgs);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public <E> E getSingleVal(String sql, Object... orgs) {
		Connection connection = null;
		try {
			connection = ConnectionContext.getInstance().get();
			return (E)queryRunner.query(connection, sql,
					new ScalarHandler(), orgs);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public void batch(String sql, Object[]... orgs) {
		// TODO Auto-generated method stub
		Connection connection = null;
		try {
			connection = ConnectionContext.getInstance().get();
		    queryRunner.batch(connection, sql, orgs);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
