<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.List,com.studentcourse.model.Course" %>

<!DOCTYPE html>
<html>
<head>
    <title>Course List</title>

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
            margin:10px;
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

        .edit:hover,
        .delete:hover{
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
    String flashMessage =
            (String) session.getAttribute("flashError");

    if (flashMessage != null) {
        session.removeAttribute("flashError");
    }

    List<Course> courses =
            (List<Course>) request.getAttribute("courses");
%>

<div class="container">

    <div class="topbar">

        <h2>Course List</h2>

        <div>

            <a class="btn-link"
               href="<%= request.getContextPath() %>/course/add">

                Add Course

            </a>

            <a class="btn-link"
               href="<%= request.getContextPath() %>/dashboard">

                Dashboard

            </a>

        </div>

    </div>

    <%
        if (flashMessage != null) {
    %>

    <div class="flash">
        <%= flashMessage %>
    </div>

    <%
        }
    %>

    <table>

        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Duration</th>
            <th>Fees</th>
            <th>Trainer</th>
            <th>Actions</th>
        </tr>

<%
    if (courses != null && !courses.isEmpty()) {

        for (Course c : courses) {
%>

        <tr>

            <td>
                <%= c.getCourseId() %>
            </td>

            <td>
                <%= c.getCourseName() %>
            </td>

            <td>
                <%= c.getDuration() %>
            </td>

            <td>
                ₹ <%= c.getFees() %>
            </td>

            <td>
                <%= c.getTrainerName() %>
            </td>

            <td class="actions">

                <a class="edit"
                   href="<%= request.getContextPath() %>/course/edit?id=<%= c.getCourseId() %>">

                    Edit

                </a>

                <a class="delete"
                   href="<%= request.getContextPath() %>/course/delete?id=<%= c.getCourseId() %>"
                   onclick="return confirm('Are you sure you want to delete this course?')">

                    Delete

                </a>

            </td>

        </tr>

<%
        }

    } else {
%>

        <tr>

            <td class="empty-row"
                colspan="6">

                No courses found.

            </td>

        </tr>

<%
    }
%>

    </table>

</div>

</body>
</html>