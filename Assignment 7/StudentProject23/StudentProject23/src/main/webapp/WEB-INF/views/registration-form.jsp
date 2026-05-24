<%@ page contentType="text/html;charset=UTF-8" language="java"
         import="java.util.List,com.studentcourse.model.Student,com.studentcourse.model.Course" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Registration</title>

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
            width:420px;
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

        input,
        select{
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
    String error = (String) request.getAttribute("error");

    List<Student> students =
            (List<Student>) request.getAttribute("students");

    List<Course> courses =
            (List<Course>) request.getAttribute("courses");
%>

<div class="container">

    <h2>Student Course Registration</h2>

    <%
        if (error != null) {
    %>

    <div class="error">
        <%= error %>
    </div>

    <%
        }
    %>

    <form method="post"
          action="<%= request.getContextPath() %>/registration/submit">

        <label>Student</label>

        <select name="studentId">

            <option value="">
                --Select Student--
            </option>

            <%
                if (students != null) {

                    for (Student s : students) {
            %>

            <option value="<%= s.getStudentId() %>">

                <%= s.getStudentName() %>

            </option>

            <%
                    }
                }
            %>

        </select>

        <label>Course</label>

        <select name="courseId">

            <option value="">
                --Select Course--
            </option>

            <%
                if (courses != null) {

                    for (Course c : courses) {
            %>

            <option value="<%= c.getCourseId() %>">

                <%= c.getCourseName() %>

            </option>

            <%
                    }
                }
            %>

        </select>

        <label>Registration Date</label>

        <input type="date"
               name="registrationDate">

        <label>Status</label>

        <select name="status">

            <option value="Active">
                Active
            </option>

            <option value="Completed">
                Completed
            </option>

            <option value="Cancelled">
                Cancelled
            </option>

        </select>

        <button type="submit">
            Register
        </button>

    </form>

    <a class="back-link"
       href="<%= request.getContextPath() %>/registrations">

        Back to Registration List

    </a>

</div>

</body>
</html>