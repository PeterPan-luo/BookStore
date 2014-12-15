<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<center>
		<br><br>
		<form action="bookServlet?method=getBooks" method="post">
			Price:
			<input type="text" size="1" name="minPrice"/> - 
			<input type="text" size="1" name="maxPrice"/>
			
			<input type="submit" value="Submit"/> 
		</form>

		<br><br>
		<table>
			<c:forEach items="${bookPage.list }" var="book">
				<tr>
					<td>
						<a href="bookServlet?method=getBook&pageNo=${bookPage.paheNo}&d=${book.id}">${book.title }</a>
						<br>
						${book.author }
					</td>
					<td>${book.price }</td>
				</tr>
			</c:forEach>
		</table>
		<br><br>
		共${bookPage.totalPageNumber } 页
		&nbsp;&nbsp;
		当前页${bookPage.pageNo }
		&nbsp;&nbsp;
		<c:if test="${bookPage.isHasPrev }"">
			<a href="bookServlet?method=getBooks&pageNo=1">首页</a>
			&nbsp;&nbsp;
			<a href="bookServlet?method=getBooks&pageNo=${bookPage.prevPage }">上一页</a>
		</c:if>
		&nbsp;&nbsp;
		<c:if test="${bookPage.isHasNext }"">
			<a href="bookServlet?method=getBooks&pageNo=${bookpage.nextPage }">下一页</a>
			&nbsp;&nbsp;
			<a href="bookServlet?method=getBooks&pageNo=${bookPage.totalPageNumber }">末页</a>
		</c:if>
		&nbsp;&nbsp;
		转到 <input type="text" size="1" id="pageNo"/> 页	
	</center>
</body>
</html>