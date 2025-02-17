<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="RegisterServlet"> 
	
	
	<label>Name</label><input type="text" name="name">
	<br>
	<br>
	<label>email</label><input type="text" name="email">
	<br>
	<br>
	<label>password</label><input type="password" name="password">
	<br>
	<br>
	<label>Cpassword</label><input type="password" name="cpassword">
	<br>
	<br>
	<label>PhoneNumber</label><input type="number" name="phonenumber">
	<br>
	<br>
	<button>Register</button>
	</form>
	
	<p>If a already have an account<a href="login.jsp">Login Here</a></p>
	
	
</body>
</html>