package com.javaweb.bookstore.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.jsp.tagext.TryCatchFinally;

import org.eclipse.jdt.internal.compiler.ast.TryStatement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.javaweb.bookstore.db.JdbcUtils;
import com.javaweb.bookstore.utils.ConnectionContext;

/**
 * Servlet Filter implementation class TransactionFilter
 */
public class TransactionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public TransactionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		Connection connection = null;
		try {
			//1. ��ȡ����
			connection = JdbcUtils.getConnection();
			
			//2. ��������
			connection.setAutoCommit(false);
			
			//3. ����ThreadLocal�����Ӻ͵�ǰ�̰߳�
			ConnectionContext.getInstance().bind(connection);
			
			//4. ������ת����Ŀ��Servlet
			chain.doFilter(request, response);
			
			//5. �ύ����
			connection.commit();
			
		} catch (Exception e) {
			// TODO: handle exception
			
			//6. �ع�����
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpServletRequest req = (HttpServletRequest) request;
			resp.sendRedirect(req.getContextPath() + "/error-1.jsp");
		}finally{
			//7. �����
			ConnectionContext.getInstance().remove();
			
			//8. �ر�����
			JdbcUtils.release(connection);
		}
	    
		// pass the request along the filter chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
