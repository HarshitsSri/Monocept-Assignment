<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Form</title>
</head>
<body>

	<h2>Student Course Registration</h2>

	<form action="save" method="post">

		Student ID: <input type="number" name="studentId"> <br>
		<br> Course ID: <input type="number" name="courseId"> <br>
		<br> Registration Date: <input type="date"
			name="registrationDate"> <br>
		<br> Status: <select name="status">

			<option value="ACTIVE">ACTIVE</option>

			<option value="COMPLETED">COMPLETED</option>

		</select> <br>
		<br> <input type="submit" value="Register">

	</form>

	<br>

	<p style="color: red;">${error}</p>

	<br>

	<a href="../dashboard"> Back To Dashboard </a>

</body>
</html>