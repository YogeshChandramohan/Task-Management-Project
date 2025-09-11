<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/AdminDashboard.css">
</head>
<body>

    <!-- Navigation Bar -->
    <div class="navbar">
        <h2>⚙️ Admin Dashboard</h2>
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
                <h4>Manage Users</h4>
                <a href="/users" class="btn">Go</a>
            </div>
			
            <div class="card">
                <div class="icon">📂</div>
                <h4>Manage Projects</h4>
                <a href="/projects" class="btn">Go</a>
            </div>
			</a>
            
        </div>
    </div>

</body>
</html>
