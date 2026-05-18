<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.List,com.studentcourse.model.Student" %>

<!DOCTYPE html>
<html>
<head>
    <title>Student List</title>

    <style>

        body{
            margin:0;
            font-family:Arial, sans-serif;
            background:#f4f7fb;
            padding:30px;
        }

        .container{
            max-width:1100px;
            margin:auto;
            background:white;
            padding:25px;
            border-radius:12px;
            box-shadow:0 4px 15px rgba(0,0,0,0.08);
        }

        h2{
            color:#2563eb;
            margin-top:0;
            margin-bottom:20px;
        }

        .topbar{
            display:flex;
            justify-content:space-between;
            align-items:center;
            margin-bottom:20px;
            flex-wrap:wrap;
            gap:12px;
        }

        .btn-link{
            text-decoration:none;
            background:#2563eb;
            color:white;
            padding:10px 16px;
            border-radius:6px;
            font-weight:bold;
        }

        .btn-link:hover{
            background:#1d4ed8;
        }

        .flash{
            background:#dcfce7;
            color:#166534;
            padding:10px 14px;
            border-radius:6px;
            margin-bottom:18px;
        }

        table{
            width:100%;
            border-collapse:collapse;
            overflow:hidden;
        }

        th, td{
            padding:12px 10px;
            border:1px solid #e5e7eb;
            text-align:left;
        }

        th{
            background:#eff6ff;
            color:#1f2937;
        }

        tr:nth-child(even){
            background:#fafcff;
        }

        .actions a{
            text-decoration:none;
            font-weight:bold;
            margin-right:10px;
        }

        .edit{
            color:#2563eb;
        }

        .delete{
            color:#dc2626;
        }

        .delete:hover, .edit:hover{
            text-decoration:underline;
        }

        .empty-row{
            text-align:center;
            color:#6b7280;
        }

    </style>
</head>
<body>

<%
    String flashMessage = (String) session.getAttribute("flashError");
    if (flashMessage != null) {
        session.removeAttribute("flashError");
    }

    List<Student> students = (List<Student>) request.getAttribute("students");
%>

<div class="container">

    <div class="topbar">
        <h2>Student List</h2>

        <div>
            <a class="btn-link" href="<%= request.getContextPath() %>/student/add">Add Student</a>
            <a class="btn-link" href="<%= request.getContextPath() %>/dashboard">Dashboard</a>
        </div>
    </div>

    <%
        if (flashMessage != null) {
    %>
        <div class="flash"><%= flashMessage %></div>
    <%
        }
    %>

    <table>
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
            if (students != null && !students.isEmpty()) {
                for (Student s : students) {
        %>
        <tr>
            <td><%= s.getStudentId() %></td>
            <td><%= s.getStudentName() %></td>
            <td><%= s.getEmail() %></td>
            <td><%= s.getPhone() %></td>
            <td><%= s.getAge() %></td>
            <td><%= s.getCity() %></td>
            <td class="actions">
                <a class="edit" href="<%= request.getContextPath() %>/student/edit?id=<%= s.getStudentId() %>">Edit</a>
                <a class="delete" href="<%= request.getContextPath() %>/student/delete?id=<%= s.getStudentId() %>"
                   onclick="return confirm('Are you sure you want to delete this student?')">Delete</a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td class="empty-row" colspan="7">No students found.</td>
        </tr>
        <%
            }
        %>
    </table>

</div>

</body>
</html>