<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.studentcourse.model.Student"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Student</title>
</head>
<body>

	<%
	Student s = (Student) request.getAttribute("student");
	%>

	<h2>Edit Student</h2>

	<form action="update" method="post">

		<input type="hidden" name="studentId" value="<%=s.getStudentId()%>">

		Student Name: <input type="text" name="studentName"
			value="<%=s.getStudentName()%>"> <br> <br> Email: <input
			type="email" name="email" value="<%=s.getEmail()%>"> <br>
		<br> Phone: <input type="text" name="phone"
			value="<%=s.getPhone()%>"> <br> <br> Age: <input
			type="number" name="age" value="<%=s.getAge()%>"> <br> <br>
		City: <input type="text" name="city" value="<%=s.getCity()%>">
		<br> <br> <input type="submit" value="Update Student">

	</form>

	<br>

	<p style="color: red;">${error}</p>

	<br>

	<a href="../students"> Back To Students </a>

</body>
</html>