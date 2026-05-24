<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="com.studentcourse.model.Student, java.util.Map" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Student</title>

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

        .top-error{
            background:#fee2e2;
            color:#dc2626;
            padding:10px;
            border-radius:6px;
            margin-bottom:18px;
            font-size:14px;
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
    Student student = (Student) request.getAttribute("student");
    Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");

    if (student == null) {
        student = new Student();
    }
%>

<div class="container">

    <h2>Edit Student</h2>

    <form method="post"
          action="<%= request.getContextPath() %>/student/update">

        <input type="hidden"
               name="studentId"
               value="<%= student.getStudentId() %>">

        <label>Student Name</label>
        <input type="text"
               name="studentName"
               value="<%= student.getStudentName() != null ? student.getStudentName() : "" %>">
        <div class="error"><%= errors != null && errors.get("studentName") != null ? errors.get("studentName") : "" %></div>

        <label>Email</label>
        <input type="text"
               name="email"
               value="<%= student.getEmail() != null ? student.getEmail() : "" %>">
        <div class="error"><%= errors != null && errors.get("email") != null ? errors.get("email") : "" %></div>

        <label>Phone</label>
        <input type="text"
               name="phone"
               value="<%= student.getPhone() != null ? student.getPhone() : "" %>">
        <div class="error"><%= errors != null && errors.get("phone") != null ? errors.get("phone") : "" %></div>

        <label>Age</label>
        <input type="number"
               name="age"
               value="<%= student.getAge() > 0 ? student.getAge() : "" %>">
        <div class="error"><%= errors != null && errors.get("age") != null ? errors.get("age") : "" %></div>

        <label>City</label>
        <input type="text"
               name="city"
               value="<%= student.getCity() != null ? student.getCity() : "" %>">
        <div class="error"><%= errors != null && errors.get("city") != null ? errors.get("city") : "" %></div>

        <button type="submit">
            Update Student
        </button>

    </form>

    <a class="back-link"
       href="<%= request.getContextPath() %>/students">
        Back to Student List
    </a>

</div>

</body>
</html>