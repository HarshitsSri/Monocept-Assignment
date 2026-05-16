<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.studentcourse.model.Student"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student List</title>
</head>
<body>

	<h2>All Students</h2>

	<br>

	<a href="student/add"> Add New Student </a>

	<br>
	<br>

	<table border="1" cellpadding="10">

		<tr>

			<th>ID</th>

			<th>Name</th>

			<th>Email</th>

			<th>Phone</th>

			<th>Age</th>

			<th>City</th>

			<th>Actions</th>

		</tr>

		<%
		List<Student> students = (List<Student>) request.getAttribute("students");

		if (students != null) {

			for (Student s : students) {
		%>

		<tr>

			<td><%=s.getStudentId()%></td>

			<td><%=s.getStudentName()%></td>

			<td><%=s.getEmail()%></td>

			<td><%=s.getPhone()%></td>

			<td><%=s.getAge()%></td>

			<td><%=s.getCity()%></td>

			<td><a href="student/edit?id=<%=s.getStudentId()%>"> Edit
			</a> | <a href="student/delete?id=<%=s.getStudentId()%>"> Delete </a></td>

		</tr>

		<%
		}
		}
		%>

	</table>

	<br>
	<br>

	<a href="dashboard"> Back To Dashboard </a>

</body>
</html>