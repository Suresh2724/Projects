<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		<form action="loginServlet">
		
		<label>Email</label><input type="text" name="email">
		<br>
		<br>
		<label>Password</label><input type="password" name="password">
		<button>Login</button>
		<br>
		<br>
		</form>
		<p>If you are a new user<a href="register.jsp">Register Here</a></p>
	
</body>
</html>