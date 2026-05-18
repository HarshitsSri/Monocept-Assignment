<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.List,com.studentcourse.model.Registration" %>

<!DOCTYPE html>
<html>
<head>
    <title>Registration List</title>

    <style>

        body{
            margin:0;
            font-family:Arial, sans-serif;
            background:#f4f7fb;
            padding:30px;
        }

        .container{
            max-width:1200px;
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
        }

        th, td{
            padding:12px 10px;
            border:1px solid #e5e7eb;
            text-align:left;
            vertical-align:top;
        }

        th{
            background:#eff6ff;
            color:#1f2937;
        }

        tr:nth-child(even){
            background:#fafcff;
        }

        .status-form{
            display:flex;
            gap:8px;
            align-items:center;
            flex-wrap:wrap;
        }

        select{
            padding:8px;
            border:1px solid #cbd5e1;
            border-radius:6px;
        }

        .update-btn{
            background:#2563eb;
            color:white;
            border:none;
            padding:8px 12px;
            border-radius:6px;
            cursor:pointer;
        }

        .update-btn:hover{
            background:#1d4ed8;
        }

        .delete{
            text-decoration:none;
            color:#dc2626;
            font-weight:bold;
        }

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
    String flashMessage = (String) session.getAttribute("flashError");
    if (flashMessage != null) {
        session.removeAttribute("flashError");
    }

    List<Registration> registrations =
            (List<Registration>) request.getAttribute("registrations");
%>

<div class="container">

    <div class="topbar">
        <h2>Registration List</h2>

        <div>
            <a class="btn-link" href="<%= request.getContextPath() %>/registration/add">Add Registration</a>
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
            <th>Student</th>
            <th>Course</th>
            <th>Registration Date</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>

        <%
            if (registrations != null && !registrations.isEmpty()) {
                for (Registration r : registrations) {
        %>
        <tr>
            <td><%= r.getRegistrationId() %></td>
            <td><%= r.getStudentName() %></td>
            <td><%= r.getCourseName() %></td>
            <td><%= r.getRegistrationDate() %></td>
            <td>
                <form class="status-form" method="post" action="<%= request.getContextPath() %>/registration/status">
                    <input type="hidden" name="registrationId" value="<%= r.getRegistrationId() %>">
                    <select name="status">
                        <option value="Active" <%= "Active".equals(r.getStatus()) ? "selected" : "" %>>Active</option>
                        <option value="Completed" <%= "Completed".equals(r.getStatus()) ? "selected" : "" %>>Completed</option>
                        <option value="Cancelled" <%= "Cancelled".equals(r.getStatus()) ? "selected" : "" %>>Cancelled</option>
                    </select>
                    <button class="update-btn" type="submit">Update</button>
                </form>
            </td>
            <td>
                <a class="delete"
                   href="<%= request.getContextPath() %>/registration/delete?id=<%= r.getRegistrationId() %>"
                   onclick="return confirm('Are you sure you want to delete this registration?')">
                    Delete
                </a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td class="empty-row" colspan="6">No registrations found.</td>
        </tr>
        <%
            }
        %>
    </table>

</div>

</body>
</html>