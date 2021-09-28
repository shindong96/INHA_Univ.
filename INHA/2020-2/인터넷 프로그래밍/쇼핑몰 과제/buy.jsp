<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dong.dao.ShoppingItemDao" %> 
<%@ page import="com.dong.model.ShoppingItem" %>
<%@ page import="java.io.PrintWriter" %> 
<% request.setCharacterEncoding("utf-8"); %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%int name=Integer.parseInt(request.getParameter("name"));
String userid=request.getParameter("id");
%>
</head>
<body>
<input type="text" name="id" value=<%=userid%> style="visibility:hidden">
<% ShoppingItemDao item = new ShoppingItemDao();

ShoppingItem i=new ShoppingItem();
ShoppingItem i2=new ShoppingItem();
i=item.numberOfItem(name);
i2.setId(i.getId());
i2.setName(i.getName());
i2.setNum(i.getNum()+1);
item.updateItem(i2);
response.sendRedirect("Home2.jsp");
%>
</body>
</html>