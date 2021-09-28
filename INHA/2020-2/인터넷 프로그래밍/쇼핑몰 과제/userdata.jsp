<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dong.dao.ShoppingUserDao" %> 
<%@ page import="java.io.PrintWriter"%>
<% request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<head>
<%
        String id=(String)session.getAttribute("id");
		String pwd=(String)session.getAttribute("pwd");
	    String name=(String)session.getAttribute("name");
	    int age =(Integer)session.getAttribute("age");
	    int phone=(Integer)session.getAttribute("phone");
	    String email=(String)session.getAttribute("email");
	    String address=(String)session.getAttribute("address");
%>
        <title>
            Nice to "MEAT" you - 마이페이지
        </title>
        <style>
            #header {
                width: 100%;
                height: 100px;
            }
        </style>       
        <script>
        function insert(){
        	document.getElementById("id").value=<%=id%>;
        	document.getElementById("pwd").value=<%=pwd%>;
        	document.getElementById("name").value=<%=name%>;
        	document.getElementById("age").value=<%=age%>;
        	document.getElementById("phone").value=<%=phone%>;
        	document.getElementById("email").value=<%=email%>;
        	document.getElementById("address").value=<%=address%>;
        }
        </script>
    </head>
    
    <body style="background-color:Tan;" onload="insert()">
        <img src="media/만화고기.png" width="100" height="100" style="position: absolute; top:30px; left:10mm">
        <br>
        <h1 id="header" style="color:brown;text-align:center;margin-top: 50px; letter-spacing:5mm">
          <a href="Home.jsp" style="text-decoration:none;color:brown"><b>Nice to "MEAT" You!!</b></a><br>
        </h1>
        <img src="media/만화고기2.png" width="100" height="100" style="position: absolute; top:100px; right:20mm">
        <hr>
        <br>
<form name=shoppinguser method=post action="update.jsp">
<table border=2 bordercolor="brown" align="center" style="width:80%;text-align:center">
<tbody>
<tr><td>ID</td><td><input type="text" name="id" id="id"></td></tr>
<tr><td>비밀번호</td><td><input type="password" id="pwd" name="pwd"></td></tr>
<tr><td>이름</td><td><input type="text" name="name" id="name"></td></tr>
<tr><td>생년월일( ex)19900101 )</td><td><input type="text" id="age" name="age"></td></tr>
<tr><td>전화번호( ex)01099999999 )</td><td><input type="text" id="phone" name="phone"></td></tr>
<tr><td>E-MAIL</td><td><input type="text" id="email" name="email"></td></tr>
<tr><td>주소</td><td><input type="text" id="address" name="address"></td></tr>
<tr><td colspan="2"><input type="submit" value="수정하기" ></td></tr>
</tbody>
</table>
</form>
</body>
</html>