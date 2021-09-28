<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
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
        if(id.equals("")){
        	RequestDispatcher dispatcher = request.getRequestDispatcher("로그인.jsp");
			dispatcher.forward(request, response);
        }
        %>
    </head>
    <body style="background-color:Tan;">
      <form action="manage.jsp" method=post><input type="text" name="id" value=<%=id%>  style="visibility:hidden"><input type="submit" value="관리자 페이지" style="position: absolute;top:10px; right:250px; height: 7mm"></form>
       <form action="logout.jsp" method=post><input type="submit" value="로그아웃" style="position: absolute;top:10px; right:130px; height: 7mm"></form>
       <form action="calluserdata.jsp" method=post><input type="text" name="id" value=<%=id%>  style="visibility:hidden"><input type="submit" name="id" value="회원정보수정" style="position: absolute;top:10px; right:10px; height: 7mm"></form>
        <img src="media/만화고기.png" width="100" height="100" style="position: absolute; top:30px; left:10mm">
        <br>
        <h1 id="header" style="color:brown;text-align:center;margin-top: 50px; letter-spacing:5mm">
          <a href="Home.jsp" style="text-decoration:none;color:brown"><b>Nice to "MEAT" You!!</b></a><br>
        </h1>
        <img src="media/만화고기2.png" width="100" height="100" style="position: absolute; top:100px; right:20mm">
        <hr>
        <br>
        <form method=post action="buy.jsp">
        <input type="text" name="id" value=<%=id%> style="visibility:hidden">
        <table style="width:100%;magin:auto;text-align:center">
        <tr><td>
        <img src="media/양프렌치렉.jpg" width="250" height="250"><br>프렌치렉<br>10,000원<br><input type="submit" value="구매하기"><br>
        <input type="text" name="name" value="9"  style="visibility:hidden"></td></tr>
        </table>
        </form>
</body>
</html>