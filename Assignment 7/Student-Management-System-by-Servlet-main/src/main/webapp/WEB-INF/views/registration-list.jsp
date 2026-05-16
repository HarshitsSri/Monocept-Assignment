<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.studentcourse.model.Registration"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration List</title>
</head>
<body>

	<h2>All Registrations</h2>

	<br>

	<a href="registration/add"> Add Registration </a>

	<br>
	<br>

	<table border="1" cellpadding="10">

		<tr>

			<th>ID</th>

			<th>Student ID</th>

			<th>Course ID</th>

			<th>Date</th>

			<th>Status</th>

			<th>Actions</th>

		</tr>

		<%
		List<Registration> registrations = (List<Registration>) request.getAttribute("registrations");

		if (registrations != null) {

			for (Registration r : registrations) {
		%>

		<tr>

			<td><%=r.getRegistrationId()%></td>

			<td><%=r.getStudentId()%></td>

			<td><%=r.getCourseId()%></td>

			<td><%=r.getRegistrationDate()%></td>

			<td><%=r.getStatus()%></td>

			<td><a
				href="registration/update?id=<%=r.getRegistrationId()%>&status=COMPLETED">
					Mark Completed </a> | <a
				href="registration/delete?id=<%=r.getRegistrationId()%>">
					Delete </a></td>

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