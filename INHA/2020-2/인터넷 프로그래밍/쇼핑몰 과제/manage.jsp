<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dong.dao.ShoppingUserDao" %> 
<%@ page import="com.dong.dao.ShoppingItemDao" %> 
<%@ page import="com.dong.model.ShoppingUser" %> 
<%@ page import="com.dong.model.ShoppingItem" %> 
<%@ page import="java.io.PrintWriter" %> 
<%@ page import="java.util.List" %>
<% request.setCharacterEncoding("utf-8"); %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
            Nice to "MEAT" you
        </title>
        <style>
            #header {
                width: 100%;
                height: 100px;
            }
        </style>
        <%
        String id=request.getParameter("id");
        session.setAttribute("userid", id);
        if(!id.equals("2006peter")){
        	RequestDispatcher dispatcher = request.getRequestDispatcher("Home2.jsp");
			dispatcher.forward(request, response);
        }
        %>  
        <%
        ShoppingUserDao userDAO = new ShoppingUserDao();
        List<ShoppingUser> users= userDAO.getAllUsers();
        ShoppingItemDao itemDAO = new ShoppingItemDao();
        List<ShoppingItem> items= itemDAO.getAllItems();
        %>
</head>
<body style="background-color:Tan;">
<img src="media/만화고기.png" width="100" height="100" style="position: absolute; top:30px; left:10mm">
<br>
<h1 id="header" style="color:brown;text-align:center;margin-top: 50px; letter-spacing:5mm">
 <a href="Home.jsp" style="text-decoration:none;color:brown"><b>Nice to "MEAT" You!!</b></a><br>
</h1>
<img src="media/만화고기2.png" width="100" height="100" style="position: absolute; top:100px; right:20mm">
<hr>
<br>
<table border=1 bordercolor="brown" align="center" style="width:80%;text-align:center">
<thead>
<tr><th>id</th><th>pwd</th><th>name</th><th>age</th><th>phone</th><th>email</th><th>address</th><th></th></tr>
<tbody>
<%
for(int i=0;i<users.size();i++){%>
<form method="post" action="ShoppingUserDeleteController"><input type="text" name="id" value="2006peter"  style="visibility:hidden"><input type="text" name="userid" value=<%=users.get(i).getId()%>  style="visibility:hidden">
	<tr><td><%=users.get(i).getId()%></td><td><%=users.get(i).getPwd()%></td><td><%=users.get(i).getName()%></td><td><%=users.get(i).getAge()%></td><td><%=users.get(i).getPhone()%></td><td><%=users.get(i).getEmail()%></td><td><%=users.get(i).getAddress()%></td>
	<td><input type="submit" value="삭제"></td></tr></form>
<%
}
%></tbody></table>
<br><br><br>
<table border=1 bordercolor="brown" align="center" style="width:80%;text-align:center">
<thead>
<tr><th>no.</th><th>메뉴</th><th>판매량</th></tr>
<tbody>
<%
for(int i=0;i<items.size();i++){%>
	<tr><td><%=items.get(i).getId()%></td><td><%=items.get(i).getName()%></td><td><%=items.get(i).getNum()%></td></tr>
<%
}
%>
</tbody>
</table>

</body>
</html>