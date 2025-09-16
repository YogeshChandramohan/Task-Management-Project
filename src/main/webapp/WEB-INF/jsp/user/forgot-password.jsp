<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Forgot Password</title>
    <link rel="stylesheet" type="text/css" href="css/forgotPassword.css">
</head>
<body>
<div class="container">
    <h2>Forgot Password</h2>

    <form action="forgotPassword" method="post">
        <label>Enter Email :</label>
        <input type="email" name="email" placeholder="Enter your email " required />

        <button type="submit">Recover Password</button>
    </form>

    <!-- Display message if available -->
    <%
        String message = (String) request.getAttribute("message");
        String error = (String) request.getAttribute("error");
        if (message != null) {
    %>
        <p class="message success"><%= message %></p>
    <%
        } else if (error != null) {
    %>
        <p class="message error"><%= error %></p>
    <%
        }
    %>

    <div class="back-link">
        <a href="login">Back to Login</a>
    </div>
</div>
</body>
</html>
