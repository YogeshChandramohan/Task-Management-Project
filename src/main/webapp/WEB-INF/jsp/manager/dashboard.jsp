
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
    <title>Manager Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminDashboard.css">
</head>
<body>

<!-- Navigation Bar -->
    <div class="navbar">
        <h2>⚙️ Manager Dashboard</h2>
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
                <h4>Manage Projects</h4>
                <a href="/managerProjects?managerId=${sessionScope.managerId}" class="btn">Go</a>
            </div>
		</div>
    </div>

</body>
</html>
