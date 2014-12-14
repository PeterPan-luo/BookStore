package com.javaweb.bookstore.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.javaweb.bookstore.exception.DBExcepton;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {

	private static DataSource dataSources = null;
	static{
		dataSources = new ComboPooledDataSource("javawebapp");
	}
	
	public static Connection getConnection() {
		try {
			return dataSources.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBExcepton("数据库连接错误！");
		}
	}
	
	public static void release(Connection connection) {
		try {
			if (connection != null) 
			{
			    connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBExcepton("数据库连接错误");
		}
		
	}
	
	public static void release(ResultSet resultSet, Statement statement) {
		try {
			if (resultSet != null) 
			{
			    resultSet.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBExcepton("数据库连接错误");
		}
		try {
			if (statement != null) 
			{
			    statement.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DBExcepton("数据库连接错误");
		}
	}
}
