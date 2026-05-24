<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="com.studentcourse.model.Course, java.util.Map" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Course</title>

    <style>
        body{
            margin:0;
            font-family:Arial, sans-serif;
            background:#f4f7fb;
            display:flex;
            justify-content:center;
            align-items:center;
            min-height:100vh;
        }

        .container{
            background:white;
            width:400px;
            padding:30px;
            border-radius:12px;
            box-shadow:0 4px 15px rgba(0,0,0,0.1);
        }

        h2{
            text-align:center;
            color:#2563eb;
            margin-bottom:25px;
        }

        .error{
            color:#dc2626;
            font-size:13px;
            margin-top:0px;
            margin-bottom:14px;
        }

        label{
            display:block;
            margin-bottom:6px;
            font-weight:bold;
            color:#374151;
        }

        input{
            width:100%;
            padding:10px;
            border:1px solid #cbd5e1;
            border-radius:6px;
            margin-bottom:8px;
            box-sizing:border-box;
        }

        button{
            width:100%;
            background:#2563eb;
            color:white;
            border:none;
            padding:12px;
            border-radius:6px;
            font-size:16px;
            cursor:pointer;
        }

        button:hover{
            background:#1d4ed8;
        }

        .back-link{
            display:block;
            text-align:center;
            margin-top:20px;
            text-decoration:none;
            color:#2563eb;
            font-weight:bold;
        }
    </style>
</head>
<body>

<%
    Course course = (Course) request.getAttribute("course");
    Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");

    if (course == null) {
        course = new Course();
    }
%>

<div class="container">

    <h2>Add Course</h2>

    <form method="post" action="<%= request.getContextPath() %>/course/add">

        <label>Course Name</label>
        <input type="text" name="courseName"
               value="<%= course.getCourseName() != null ? course.getCourseName() : "" %>">
        <div class="error"><%= errors != null && errors.get("courseName") != null ? errors.get("courseName") : "" %></div>

        <label>Duration</label>
        <input type="text" name="duration"
               value="<%= course.getDuration() != null ? course.getDuration() : "" %>">
        <div class="error"><%= errors != null && errors.get("duration") != null ? errors.get("duration") : "" %></div>

        <label>Fees</label>
        <input type="number" step="0.01" name="fees"
               value="<%= course.getFees() > 0 ? course.getFees() : "" %>">
        <div class="error"><%= errors != null && errors.get("fees") != null ? errors.get("fees") : "" %></div>

        <label>Trainer Name</label>
        <input type="text" name="trainerName"
               value="<%= course.getTrainerName() != null ? course.getTrainerName() : "" %>">
        <div class="error"><%= errors != null && errors.get("trainerName") != null ? errors.get("trainerName") : "" %></div>

        <button type="submit">Save Course</button>
    </form>

    <a class="back-link" href="<%= request.getContextPath() %>/courses">
        Back to Course List
    </a>

</div>

</body>
</html>