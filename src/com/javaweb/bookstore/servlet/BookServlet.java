package com.javaweb.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.bookstore.domain.User;
import com.javaweb.bookstore.service.AccountService;
import com.javaweb.bookstore.service.UserService;
import com.google.gson.Gson;
import com.javaweb.bookstore.domain.Account;
import com.javaweb.bookstore.domain.Book;
import com.javaweb.bookstore.domain.ShoppingCar;
import com.javaweb.bookstore.domain.ShoppingCarItem;
import com.javaweb.bookstore.service.BookService;
import com.javaweb.bookstore.web.BookStoreWebUtils;
import com.javaweb.bookstore.web.CriteriaBook;
import com.javaweb.bookstore.web.Page;
import com.sun.net.httpserver.Authenticator.Success;
import com.sun.org.apache.regexp.internal.REUtil;
import com.sun.xml.internal.ws.api.ha.StickyFeature;

/**
 * Servlet implementation class BookServlet
 */
public class BookServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	protected void forwardPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		request.getRequestDispatcher("/WEB-INF/pages/" + page + ".jsp").forward(request, response);
	}
	protected void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 获取商品id
		String idString = request.getParameter("id");
		int id = -1;
		boolean flag = false;
		try {
			id = Integer.parseInt(idString);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (id > 0) {
			//2. 获取购物车对象
			ShoppingCar sc = BookStoreWebUtils.getShoppingCar(request);
			
			//3. 调用bookService的addToCar方法将商品加入购物车
			flag = bookService.addToCar(id, sc);
			
		}
		//3. 直接调用getBooks方法
		if (flag) {
			getBooks(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/error-1.jsp");
	}
	protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoppingCar sc = BookStoreWebUtils.getShoppingCar(request);
		bookService.clearShoppingCar(sc);
		request.getRequestDispatcher("/WEB-INF/pages/emptyCar.jsp").forward(request, response);
	}
	protected void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = -1;
		try {
			id = Integer.parseInt(idStr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		ShoppingCar sc = BookStoreWebUtils.getShoppingCar(request);
		bookService.removeItemFromShoppingCart(sc, id);
		if (sc.isEmpty()) {
			request.getRequestDispatcher("/WEB-INF/pages/emptyCar.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("WEB-INF/pages/cart.jsp").forward(request, response);
	}
	protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = -1;
		Book book =  null;
		try {
			id = Integer.parseInt(idStr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (id > 0) 
			book = bookService.getBook(id);
		if (book == null) {
			response.sendRedirect(request.getContextPath() + "/error-1.jsp");
			return;
		}
		request.setAttribute("book", book);
		request.getRequestDispatcher("/WEB-INF/pages/book.jsp").forward(request, response);
	}
	protected void getBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNoStr = request.getParameter("pageNo");
		String minPriceStr = request.getParameter("minPrice");
		String maxPriceStr = request.getParameter("maxPrice");
		
		int pageNo = 1;
		int minPrice = 0;
		int maxPrice = Integer.MAX_VALUE;
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		try {
			minPrice = Integer.parseInt(minPriceStr);
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		try {
			maxPrice = Integer.parseInt(maxPriceStr);
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		
		CriteriaBook criteriaBook = new CriteriaBook(minPrice, maxPrice, pageNo);
		Page<Book> page = bookService.getPage(criteriaBook);
		request.setAttribute("bookPage", page);
		
		request.getRequestDispatcher("/WEB-INF/pages/books.jsp").forward(request, response);
	}
	
	protected void cash(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String accountID = request.getParameter("accountID");
		StringBuffer errors = validateFormField(username, accountID);
		//表单验证通过
		if (errors.toString().equals("")) {
			//验证用户名与密码
			errors = validateUser(username, accountID);
			if (errors.toString().equals("")) {
				//验证库存
				errors = validateBookStoreNumber(request);
				if (errors.toString().equals("")) {
					errors = validateBalance(request, accountID);
				}
			}
		}
		
		if (!errors.toString().equals("")) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/WEB-INF/pages/cash.jsp").forward(request, response);
			return;
		}
		//通过验证
		bookService.cash(BookStoreWebUtils.getShoppingCar(request), username, accountID);
		response.sendRedirect(request.getContextPath() + "/success.jsp");
	}
	
	private UserService userService = new UserService();
	private AccountService accountService = new AccountService();
	
	//验证余额是否充足
	private StringBuffer validateBalance(HttpServletRequest request, String accountId)
	{
		StringBuffer errors = new StringBuffer();
		ShoppingCar sc = BookStoreWebUtils.getShoppingCar(request);
		
		Account account = accountService.getAccountById(Integer.parseInt(accountId));
		if (sc.getTotalMoney() > account.getBalance()) {
			errors.append("余额不足！");
		}
		return errors;
	}
	//验证库存是否充足
	private StringBuffer validateBookStoreNumber(HttpServletRequest request)
	{
		StringBuffer errors = new StringBuffer("");
		ShoppingCar sc = BookStoreWebUtils.getShoppingCar(request);
		for(ShoppingCarItem sci : sc.getItems())
		{
			int quantity = sci.getQuantity();
			int storeNumber = bookService.getBook(sci.getBook().getId()).getStoreNumber();
			if (quantity > storeNumber) {
				errors.append(sci.getBook().getTitle() + "库存不足</br>");
			}
		}
		return errors;
	}
	//验证用户名与密码
	private StringBuffer validateUser(String username, String accounID) {
		StringBuffer errors = new StringBuffer();
		boolean flag = false;
		User user = userService.getUserByUsername(username);
		if (user != null) {
			int accountID2 = user.getAccountid();
			if (accounID.trim().equals(accountID2 + "")) {
				flag = true;
			}
		}
		if (!flag) {
			errors.append("用户名密码不匹配");
		}
		return errors;
	}
	//验证字段输入是否为空
	private StringBuffer validateFormField(String username, String accoundID)
	{
		StringBuffer errors = new StringBuffer();
		if (username == null || username.trim().equals("")) {
			errors.append("用户名不能为空<br>");
		}
		if (accoundID == null || accoundID.trim().equals("")) {
			errors.append("账户不能为空");
		}
		return errors;
	}
	protected void updateItemQuantity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//4. 在 updateItemQuantity 方法中, 获取 quanity, id, 再获取购物车对象, 调用 service 的方法做修改
		String idStr = request.getParameter("id");
		String quantiytString = request.getParameter("quantity");
		
		ShoppingCar sc = BookStoreWebUtils.getShoppingCar(request);
		
		int id = -1;
		int quantity = -2;
		try {
			id = Integer.parseInt(idStr);
			quantity = Integer.parseInt(quantiytString);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (id > -1 && quantity > -1) {
			bookService.updateItemQuantity(sc, id, quantity);
			
			//5. 传回json数据
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("bookNumber", sc.getBookNumber());
			resultMap.put("totalMoney", sc.getTotalMoney());
			Gson gson = new Gson();
			String jsonString = gson.toJson(resultMap);
			response.setContentType("text/javascript");
			response.getWriter().print(jsonString);
		}
	}

}
