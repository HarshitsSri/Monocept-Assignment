<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>

    <style>

        body{
            margin:0;
            font-family: Arial, sans-serif;
            background:#f4f7fb;

            display:flex;
            justify-content:center;
            align-items:center;

            height:100vh;
        }

        .login-box{

            background:white;

            width:350px;

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

            margin-bottom:15px;

            font-size:14px;
        }

        label{
            display:block;
            margin-bottom:6px;
            font-weight:bold;
            color:#374151;
        }

        input[type="text"],
        input[type="password"]{

            width:100%;

            padding:10px;

            border:1px solid #cbd5e1;

            border-radius:6px;

            margin-bottom:18px;

            box-sizing:border-box;
        }

        .remember{

            display:flex;

            align-items:center;

            gap:8px;

            margin-bottom:20px;

            color:#4b5563;

            font-size:14px;
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

    </style>

</head>
<body>

<%
    String error =
            (String) request.getAttribute("error");

    String rememberedUsername =
            (String) request.getAttribute("rememberedUsername");
%>

<div class="login-box">

    <h2>Admin Login</h2>

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
          action="<%= request.getContextPath() %>/login-action">

        <label>Username</label>

        <input type="text"
               name="username"

               value="<%= rememberedUsername != null ? rememberedUsername : "" %>">

        <label>Password</label>

        <input type="password"
               name="password">

        <div class="remember">

            <input type="checkbox"
                   name="remember"

                   <%= rememberedUsername != null ? "checked" : "" %>>

            Remember Username

        </div>

        <button type="submit">
            Login
        </button>

    </form>

</div>

</body>
</html>