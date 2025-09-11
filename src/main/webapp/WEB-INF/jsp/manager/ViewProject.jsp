<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.demo.entity.Project, com.example.demo.entity.User, java.util.Set" %>

<%
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader("Expires", 0);

    com.example.demo.entity.User manager =
        (com.example.demo.entity.User) session.getAttribute("loggedInUser");
    if(manager == null) {
        response.sendRedirect("login");
        return;
    }

    Project project = (Project) request.getAttribute("project");
    Set<User> employees = (Set<User>) request.getAttribute("employees");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Project Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ManagerProject.css">
</head>
<body>

    <div class="container">
        <h1>Project Details</h1>

        <p><b>Name:</b> <%= project.getName() %></p>
        <p><b>Description:</b> <%= project.getDescription() %></p>
        <p><b>Start Date:</b> <%= project.getSdate() %></p>
        <p><b>End Date:</b> <%= project.getEdate() %></p>

        <h3>Assigned Employees:</h3>
        <table>
            <tr>
                <th>Name</th>
                <th>Email</th>
            </tr>
            <% for(User u : employees) { %>
                <tr>
                    <td><%= u.getName() %></td>
                    <td><%= u.getEmail() %></td>
                </tr>
            <% } %>
        </table>

        <div class="actions">
            <a href="${pageContext.request.contextPath}/projects/<%= project.getId() %>/assignEmployees" class="btn">Assign Employees</a>
            <a href="${pageContext.request.contextPath}/projects/manager" class="btn">Back to Projects</a>
        </div>
    </div>

</body>
</html>
