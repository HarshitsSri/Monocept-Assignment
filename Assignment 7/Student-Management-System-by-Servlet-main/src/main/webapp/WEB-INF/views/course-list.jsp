<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List"%>
<%@ page import="com.studentcourse.model.Course"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course List</title>
</head>
<body>

	<h2>All Courses</h2>

	<br>

	<a href="course/add"> Add New Course </a>

	<br>
	<br>

	<table border="1" cellpadding="10">

		<tr>

			<th>ID</th>

			<th>Course Name</th>

			<th>Duration</th>

			<th>Fees</th>

			<th>Trainer</th>

			<th>Actions</th>

		</tr>

		<%
		List<Course> courses = (List<Course>) request.getAttribute("courses");

		if (courses != null) {

			for (Course c : courses) {
		%>

		<tr>

			<td><%=c.getCourseId()%></td>

			<td><%=c.getCourseName()%></td>

			<td><%=c.getDuration()%></td>

			<td><%=c.getFees()%></td>

			<td><%=c.getTrainerName()%></td>

			<td><a href="course/edit?id=<%=c.getCourseId()%>"> Edit </a> |

				<a href="course/delete?id=<%=c.getCourseId()%>"> Delete </a></td>

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