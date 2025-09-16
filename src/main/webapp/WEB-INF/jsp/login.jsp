<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Task Manager Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>

<div class="login-wrapper">
    <div class="login-card">
        <div class="logo">
            <h1>🗂️ TaskManagement</h1>
        </div>
        <h2>Welcome Back</h2>
        <p class="subtitle">Login to manage your tasks efficiently</p>

        <form action="${pageContext.request.contextPath}/login" method="post">
            <input type="text" name="username" placeholder="Enter Mobile Number or Email" required>
            <input type="password" name="password" placeholder="Enter Password" required>
            <input type="submit" value="Login">
        </form>
		<!-- Forgot Password Button -->
		<a href="/forgotPassword">Forgot Password?</a>
        <!-- Error message -->
        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>
    </div>
</div>

</body>
</html>
