<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>
<head>
<title>Dashboard</title>

<style>

    body{

        margin:0;

        font-family:Arial, sans-serif;

        background:#f4f7fb;

        padding:40px;
    }

    .container{

        max-width:900px;

        margin:auto;
    }

    .topbar{

        display:flex;

        justify-content:space-between;

        align-items:center;

        margin-bottom:30px;
    }

    h2{

        color:#2563eb;

        margin:0;
    }

    .logout-btn{

        text-decoration:none;

        background:#dc2626;

        color:white;

        padding:10px 16px;

        border-radius:6px;
    }

    .logout-btn:hover{

        background:#b91c1c;
    }

    .card-container{

        display:flex;

        gap:20px;

        margin-bottom:30px;
    }

    .card{

        flex:1;

        background:white;

        padding:25px;

        border-radius:12px;

        box-shadow:0 4px 15px rgba(0,0,0,0.08);

        text-align:center;
    }

    .card h3{

        margin-bottom:10px;

        color:#374151;
    }

    .count{

        font-size:30px;

        font-weight:bold;

        color:#2563eb;
    }

    .links{

        background:white;

        padding:25px;

        border-radius:12px;

        box-shadow:0 4px 15px rgba(0,0,0,0.08);
    }

    .links a{

        display:block;

        text-decoration:none;

        background:#2563eb;

        color:white;

        padding:12px;

        border-radius:6px;

        margin-bottom:15px;

        text-align:center;

        font-weight:bold;
    }

    .links a:hover{

        background:#1d4ed8;
    }

</style>

</head>
<body>

<%
    String user =
            (String) session.getAttribute("loggedInUser");
%>

<div class="container">

    <div class="topbar">

        <div>

            <h2>
                Welcome,
                <%= user %>
            </h2>

            <p>
                Student Course Registration System
            </p>

        </div>

        <a class="logout-btn"
           href="<%= request.getContextPath() %>/logout">

            Logout

        </a>

    </div>

    <div class="card-container">

        <div class="card">

            <h3>Total Students</h3>

            <div class="count">
                <%= request.getAttribute("totalStudents") %>
            </div>

        </div>

        <div class="card">

            <h3>Total Courses</h3>

            <div class="count">
                <%= request.getAttribute("totalCourses") %>
            </div>

        </div>

        <div class="card">

            <h3>Total Registrations</h3>

            <div class="count">
                <%= request.getAttribute("totalRegistrations") %>
            </div>

        </div>

    </div>

    <div class="links">

        <a href="<%= request.getContextPath() %>/students">
            Manage Students
        </a>

        <a href="<%= request.getContextPath() %>/courses">
            Manage Courses
        </a>

        <a href="<%= request.getContextPath() %>/registrations">
            Manage Registrations
        </a>

    </div>

</div>

</body>
</html>