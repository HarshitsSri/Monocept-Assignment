<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>

	<h2>Admin Login</h2>

	<form action="login" method="post">

		Username: <input type="text" name="username"> <br>
		<br> Password: <input type="password" name="password"> <br>
		<br> Remember Username <input type="checkbox" name="remember">

		<br>
		<br> <input type="submit" value="Login">

	</form>

	<br>

	<p style="color: red;">${error}</p>

</body>
</html>