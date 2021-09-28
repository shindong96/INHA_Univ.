<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dong.dao.ShoppingUserDao" %> 
<%@ page import="com.dong.model.ShoppingUser" %> 
<%@ page import="java.io.PrintWriter" %> 
<% request.setCharacterEncoding("utf-8"); %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nice to "MEAT" you - 회원정보수정</title>
<%  // 로그인 페이지에서 넘겨준 userID와 userPassword를 받아서 로그인 판별
        ShoppingUserDao userDAO = new ShoppingUserDao();
        ShoppingUser userdata = new ShoppingUser();
        String userid=request.getParameter("id");
		userdata = userDAO.getUserById(userid);
		String id = userdata.getId();
		String pwd = userdata.getPwd();
        String name =  userdata.getName();
		int age = userdata.getAge();
		int phone = userdata.getPhone();
		String email = userdata.getEmail();
		String address = userdata.getAddress();
%>
</head>
<body style="background-color:Tan;" onload="insert()">
        <img src="media/만화고기.png" width="100" height="100" style="position: absolute; top:30px; left:10mm">
        <br>
        <h1 id="header" style="color:brown;text-align:center;margin-top: 50px; letter-spacing:5mm">
          <a href="Home.jsp" style="text-decoration:none;color:brown"><b>Nice to "MEAT" You!!</b></a><br>
        </h1>
        <img src="media/만화고기2.png" width="100" height="100" style="position: absolute; top:100px; right:20mm">
        <hr>
        <br><br><br>
<form name=shoppinguser method=post action="ShoppingUserUpdateController">
<input type="text" name="userid" value=<%=userid%>  style="visibility:hidden">
<table border=2 bordercolor="brown" align="center" style="width:80%;text-align:center">
<tbody>
<tr><td>ID</td><td><input type="text" name="id" id="id" value=<%=id%> ></td></tr>
<tr><td>비밀번호</td><td><input type="password" id="pwd" name="pwd" value=<%=pwd%>></td></tr>
<tr><td>이름</td><td><input type="text" name="name" id="name" value=<%=name%>></td></tr>
<tr><td>생년월일( ex)19900101 )</td><td><input type="text" id="age" name="age" value=<%=age%>></td></tr>
<tr><td>전화번호( ex)01099999999 )</td><td><input type="text" id="phone" name="phone" value=<%=phone%>></td></tr>
<tr><td>E-MAIL</td><td><input type="text" id="email" name="email" value=<%=email%>></td></tr>
<tr><td>주소</td><td><input type="text" id="address" name="address" value=<%=address%>></td></tr>
<tr><td colspan="2"><input type="submit" value="수정하기" ></td></tr>
</tbody>
</table>
</form>
</body>
</html>