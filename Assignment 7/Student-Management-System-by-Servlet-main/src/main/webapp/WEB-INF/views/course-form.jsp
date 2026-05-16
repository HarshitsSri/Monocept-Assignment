<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Course</title>
</head>
<body>

	<h2>Add Course</h2>

	<form action="add" method="post">

		Course Name: <input type="text" name="courseName"> <br>
		<br> Duration: <input type="text" name="duration"> <br>
		<br> Fees: <input type="number" name="fees"> <br>
		<br> Trainer Name: <input type="text" name="trainerName">

		<br>
		<br> <input type="submit" value="Add Course">

	</form>

	<br>

	<p style="color: red;">${error}</p>

	<br>

	<a href="../dashboard"> Back To Dashboard </a>

</body>
</html>