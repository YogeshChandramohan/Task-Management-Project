
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader("Expires", 0);

    com.example.demo.entity.User manager =
        (com.example.demo.entity.User) session.getAttribute("loggedInUser");

    if (manager == null) {
        response.sendRedirect("login"); // redirect if not logged in
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminDashboard.css">
</head>
<body>

<!-- Navigation Bar -->
    <div class="navbar">
        <h2>⚙️ Employee Dashboard</h2>
        <div class="nav-links">
            <a href="/logout">Logout</a>
        </div>
    </div>

    <!-- Content -->
    <div class="container">
        <h3>Welcome, ${user.name} 🎉</h3>
        <p>Select an option to manage the system:</p>

        <div class="card-container">
            <div class="card">
                <div class="icon">👤</div>
                <h4>Show Projects And Task</h4>
                <a href="${pageContext.request.contextPath}/employee/projects/${user.id}"class="btn">Go</a>
            </div>
		</div>
    </div>

</body>
</html>
