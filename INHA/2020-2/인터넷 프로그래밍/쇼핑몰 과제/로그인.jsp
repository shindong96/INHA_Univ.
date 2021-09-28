<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>
Nice to "MEAT" you - Log In
</title>
<style>
#header {
         width: 100%;
         height: 100px;
        }
</style>
<script>
function go(){
	location.href="회원가입.jsp";
}
</script>
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

<pre style="color:brown;"><b>             *  로  그  인</b></pre><br>
<form method="post" action="loginaction.jsp">
<table border=2 bordercolor="brown" align="center" style="width:80%;text-align:center">
<tbody>
<tr><td>ID</td><td><input type="text" name="id"></td></tr>
<tr><td>비밀번호</td><td><input type="password" name="pwd">
<tr><td colspan="2"><input type="submit" value="로그인"><input type="reset" value="회원가입" onclick="go()"></td>
</tbody>
</table>
</form>
</body>
</html>