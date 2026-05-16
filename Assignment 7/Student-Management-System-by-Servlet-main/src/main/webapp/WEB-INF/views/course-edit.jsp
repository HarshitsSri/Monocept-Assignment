<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.studentcourse.model.Course"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Course</title>
</head>
<body>

	<%
	Course c = (Course) request.getAttribute("course");
	%>

	<h2>Edit Course</h2>

	<form action="update" method="post">

		<input type="hidden" name="courseId" value="<%=c.getCourseId()%>">

		Course Name: <input type="text" name="courseName"
			value="<%=c.getCourseName()%>"> <br>
		<br> Duration: <input type="text" name="duration"
			value="<%=c.getDuration()%>"> <br>
		<br> Fees: <input type="number" name="fees"
			value="<%=c.getFees()%>"> <br>
		<br> Trainer Name: <input type="text" name="trainerName"
			value="<%=c.getTrainerName()%>"> <br>
		<br> <input type="submit" value="Update Course">

	</form>

	<br>

	<p style="color: red;">${error}</p>

	<br>

	<a href="../courses"> Back To Courses </a>

</body>
</html>