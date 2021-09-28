<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dong.dao.ShoppingUserDao" %> 
<%@ page import="java.io.PrintWriter" %> 
<% request.setCharacterEncoding("utf-8"); %> 
<jsp:useBean id="user" class="com.dong.model.ShoppingUser" scope="page"/> 
<jsp:setProperty name="user" property="id" />
<jsp:setProperty name="user" property="pwd" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%  // 로그인 페이지에서 넘겨준 userID와 userPassword를 받아서 로그인 판별
        ShoppingUserDao userDAO = new ShoppingUserDao();
		int result = userDAO.login(user.getId(), user.getPwd());
		if (result == 1){ // 로그인 정보가 맞으면 자바스크립트를 실행하여 페이지를 이동시킴
			PrintWriter script = response.getWriter();
			String userid= request.getParameter("id");
			
			session.setAttribute("userid", user.getId());
			
			script.println("<script>");
			script.println("alert('로그인 성공!')");
			script.println("location.href='Home2.jsp'");
			script.println("</script>");
			RequestDispatcher dispatcher = request.getRequestDispatcher("Home2.jsp");
			dispatcher.forward(request, response);
		
		}
		else if (result == 0){ 
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 틀립니다.')");	
			script.println("history.back()"); // 이전 페이지로 사용자를 돌려 보냄.	
			script.println("</script>");
		}
		else if (result == -1){ 
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('존재하지 않는 아이디입니다.')");	
			script.println("history.back()");	
			script.println("</script>");
		}
		else if (result == -2){ 
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스에 오류가 발생했습니다.')");	
			script.println("history.back()");	
			script.println("</script>");
		}
		
	%>
</body>
</html>