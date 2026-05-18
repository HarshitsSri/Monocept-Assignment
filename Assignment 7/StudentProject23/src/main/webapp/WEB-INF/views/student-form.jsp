<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Student</title>

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

            margin-bottom:18px;

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
    String error =
            (String) request.getAttribute("error");
%>

<div class="container">

    <h2>Add Student</h2>

    <%
        if(error != null){
    %>

    <div class="error">
        <%= error %>
    </div>

    <%
        }
    %>

    <form method="post"
          action="<%= request.getContextPath() %>/student/add">

        <label>Student Name</label>

        <input type="text"
               name="studentName">

        <label>Email</label>

        <input type="text"
               name="email">

        <label>Phone</label>

        <input type="text"
               name="phone">

        <label>Age</label>

        <input type="number"
               name="age">

        <label>City</label>

        <input type="text"
               name="city">

        <button type="submit">
            Save Student
        </button>

    </form>

    <a class="back-link"
       href="<%= request.getContextPath() %>/students">

        Back to Student List

    </a>

</div>

</body>
</html>