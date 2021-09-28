<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dong.dao.ShoppingUserDao" %> 
<%@ page import="java.io.PrintWriter"%>
<% request.setCharacterEncoding("utf-8");%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
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
    </head>
    <body style="background-color:Tan;">
       <%String userid=(String)session.getAttribute("userid");
      out.println(userid+"님 접속을 환영합니다!");
      %>
      
       <form action="manage.jsp" method=post><input type="text" name="id" value=<%=userid%>  style="visibility:hidden"><input type="submit" value="관리자 페이지" style="position: absolute;top:10px; right:250px; height: 7mm"></form>
       <form action="logout.jsp" method=post><input type="submit" value="로그아웃" style="position: absolute;top:10px; right:130px; height: 7mm"></form>
       <form action="calluserdata.jsp" method=post><input type="text" name="id" value=<%=userid%>  style="visibility:hidden"><input type="submit" name="id" value="회원정보수정" style="position: absolute;top:10px; right:10px; height: 7mm"></form>
        <img src="media/만화고기.png" width="100" height="100" style="position: absolute; top:30px; left:10mm">
        <br>
        <h1 id="header" style="color:brown;text-align:center;margin-top: 50px; letter-spacing:5mm">
          <a href="Home.jsp" style="text-decoration:none;color:brown"><b>Nice to "MEAT" You!!</b></a><br>
        </h1>
        <img src="media/만화고기2.png" width="100" height="100" style="position: absolute; top:100px; right:20mm">
        <hr>
        <br>
        
        
        
        <p style="color:brown;text-align:center;letter-spacing:3mm"><b>소고기</b>
        <table id="소" style="width:100%;magin:auto;text-align:center">
        <tbody>
        <tr>
        <td>
        <form method=post action="소갈비살.jsp">
        <img src="media/소갈비살.jpg" width="250" height="250"><br>
        갈비살<br>10,000원<br><input type="submit" value="보러가기"><br><input type="text" name="id" value=<%=userid%>  style="visibility:hidden"></form>
        </td>
        <td>
        <form method=post action="소안창살.jsp"><img src="media/소안창살.jpg" width="250" height="250"><br>
        안창살<br>10,000원<br><input type="submit" value="보러가기"><br><input type="text" name="id" value=<%=userid%>  style="visibility:hidden"></form>
        </td>
        <td>
        <form method=post action="소치맛살.jsp"><img src="media/소치맛살.jpg" width="250" height="250"><br>
        치맛살<br>10,000원<br>  <input type="submit" value="보러가기"><br><input type="text" name="id" value=<%=userid%>  style="visibility:hidden"></form>
        </td>
        </tr>
        </tbody>
        </table>
        <hr><br>
        
        <p style="color:brown;text-align:center;letter-spacing:3mm"><b>돼지고기</b>
        <table id="돼지" style="width:100%;magin:auto;text-align:center">
        <tbody>
        <tr>
        <td>
        <form method=post action="돼지삼겹살.jsp">
        <img src="media/돼지삼겹살.jpg" width="250" height="250"><br>
        삼겹살<br>10,000원  <br><input type="submit" value="보러가기"><br><input type="text" name="id" value=<%=userid%>  style="visibility:hidden"></form>
        </td>
        <td>
        <form method=post action="돼지목살.jsp"><img src="media/돼지목살.jpg" width="250" height="250"><br>
        목살<br>10,000원  <br><input type="submit" value="보러가기"><br><input type="text" name="id" value=<%=userid%>  style="visibility:hidden"></form>
        </td>
        <td>
        <form method=post action="돼지등심.jsp"><img src="media/돼지등심.jpg" width="250" height="250"><br>
        등심<br>10,000원  <br><input type="submit" value="보러가기"><br><input type="text" name="id" value=<%=userid%>  style="visibility:hidden"></form>
        </td>
        </tr>
        </tbody>
        </table>
        <hr><br>
        
        
        <p style="color:brown;text-align:center;letter-spacing:3mm"><b>양고기</b>
        <table id="양" style="width:100%;magin:auto;text-align:center">
        <tbody>
        <tr>
        <td>
        <form method=post action="양꼬치.jsp"><img src="media/양꼬치.jpg" width="250" height="250"><br>
        양꼬치<br>10,000원 <br> <input type="submit" value="보러가기"><br><input type="text" name="id" value=<%=userid%>  style="visibility:hidden"></form>
        </td>
        <td>
        <form method=post action="숄더렉.jsp"><img src="media/양숄더렉.jpg" width="250" height="250"><br>
        숄더렉<br>10,000원  <br><input type="submit" value="보러가기"><br><input type="text" name="id" value=<%=userid%>  style="visibility:hidden"></form>
        </td>
        <td>
        <form method=post action="프렌치렉.jsp"><img src="media/양프렌치렉.jpg" width="250" height="250"><br>
        프렌치렉<br>10,000원  <br><input type="submit" value="보러가기"><br><input type="text" name="id" value=<%=userid%>  style="visibility:hidden"></form>
        </td>
        </tr>
        </tbody>
        </table>
    </body>
</html>