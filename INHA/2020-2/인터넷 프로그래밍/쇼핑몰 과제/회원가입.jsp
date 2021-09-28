<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nice to "MEAT" you - 회원가입 페이지</title>
<style>
#header {
         width: 100%;
         height: 100px;
        }
</style>
<script>
function check(){
	var pwd=document.getElementById("pwd").value;
	var pwdcorrect=document.getElementById("pwdcorrect").value;
	if(pwd==pwdcorrect){
		alert("일치합니다!");
	}
	else{
		alert("비밀번호가 일치하지 않습니다!다시 입력하세요");
		document.getElementById("pwd").value="";
		document.getElementById("pwdcorrect").value="";
	}
}
function cancel(){
	location.href="Home.jsp";
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

<pre style="color:brown;"><b>             *  회  원  가  입</b></pre><br>
<form name=shoppinguser method=post action="ShoppingUserController">
<table border=2 bordercolor="brown" align="center" style="width:80%;text-align:center">
<tbody>
<tr><td>ID</td><td><input type="text" name="id"><input type="button" value="중복확인"></td></tr>
<tr><td>비밀번호</td><td><input type="password" id="pwd" name="pwd">
<tr><td>비밀번호 확인</td><td><input type="password" id="pwdcorrect" name="pwdcorrect"><input type="button" value="확인" onclick="check()"><br><p id="correct" style=":10"></p></td></tr>
<tr><td>이름</td><td><input type="text" name="name"></td></tr>
<tr><td>생년월일( ex)19900101 )</td><td><input type="text" name="age"></td></tr>
<tr><td>전화번호( ex)01099999999 )</td><td><input type="text" name="phone"></td></tr>
<tr><td>E-MAIL</td><td><input type="text" name="email"></td></tr>
<tr><td>주소</td><td><input type="text" name="address"></td></tr>
<tr><td colspan="2"><input type="submit" value="가입하기" ><input type="reset" value="취소" onclick="cancel()"></td>
</tbody>
</table>
</form>
</body>
</html>