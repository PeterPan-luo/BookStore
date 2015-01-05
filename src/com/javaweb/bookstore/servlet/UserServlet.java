package com.javaweb.bookstore.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.bookstore.domain.User;
import com.javaweb.bookstore.service.UserService;

/**
 * Servlet implementation class userServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private UserService userService = new UserService();
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		
		User user = userService.getUsersWithTrade(username);
		
		if (user == null) {
			response.sendRedirect(request.getServletPath() + "/error-1.jsp");
		}
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("/WEB-INF/pages/trades.jsp").forward(request, response);
	}
	

}
