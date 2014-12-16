<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书目录</title>
<script type="text/javascript" src="script/jquery-1.7.2.min.js"></script>
<script type="text/javascript">

	$(function()
	{
		$("a").click(function () {
			var serializaVal = $(":hidden").serialize();
			var href = this.href + "&" + serializaVal;
			window.location.href = href;
			return false;
		});
		$("#pageNo").change(function(){
			var val = $(this).val();
			val = $.trim(val);
			
			//1. 校验 val 是否为数字 1, 2, 而不是 a12, b
			var flag = false;
			var reg = /^\d+$/g;
			var pageNo = 0;
			
			if(reg.test(val)){
				//2. 校验 val 在一个合法的范围内： 1-totalPageNumber
				pageNo = parseInt(val);
				if(pageNo >= 1 && pageNo <= parseInt("${bookPage.pageTotalNum }")){
					flag = true;
				}
			}
			
			if(!flag){
				alert("输入的不是合法的页码.");
				$(this).val("");
				return;
			}
			
			//3. 页面跳转
			var href = "bookServlet?method=getBooks&pageNo=" + pageNo + "&" + $(":hidden").serialize();
			window.location.href = href;
		});
	})
</script>
<%@ include file="/commons/queryCondition.jsp" %>
</head>
<body>
	<center>
		<br><br>
		<form action="bookServlet?method=getBooks" method="post">
			Price:
			<input type="text" size="1" name="minPrice"/> - 
			<input type="text" size="1" name="maxPrice"/>
			
			<input type="submit" value="查询"/> 
		</form>
		<br><br>
		<table cellpadding="10">
		
			<c:forEach items="${bookPage.list }" var="book">
				<tr>
					<td>
						<a href="bookServlet?method=getBook&pageNo=${bookPage.pageNo}&d=${book.id}">${book.title }</a>
						<br>
						${book.author }
					</td>
					<td>${book.price }</td>
				</tr>
			</c:forEach>
			
		</table>
		<br><br>
		共${bookPage.pageTotalNum } 页
		&nbsp;&nbsp;
		当前页${bookPage.pageNo }
		&nbsp;&nbsp;
		<c:if test="${bookPage.hasPrev }">
			<a href="bookServlet?method=getBooks&pageNo=1">首页</a>
			&nbsp;&nbsp;
			<a href="bookServlet?method=getBooks&pageNo=${bookPage.prevPage }">上一页</a>
		</c:if>
		&nbsp;&nbsp;
		<c:if test="${bookPage.hasNext }">
			<a href="bookServlet?method=getBooks&pageNo=${bookPage.nextPage }">下一页</a>
			&nbsp;&nbsp;
			<a href="bookServlet?method=getBooks&pageNo=${bookPage.pageTotalNum }">末页</a>
		</c:if>
		&nbsp;&nbsp;
		转到 <input type="text" size="1" id="pageNo"/> 页	
		<br><br>
		
	</center>
</body>
</html>