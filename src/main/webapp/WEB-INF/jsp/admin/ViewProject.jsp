<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.demo.entity.Project, com.example.demo.entity.User, java.util.Set" %>

<%
    Project project = (Project) request.getAttribute("project");
    com.example.demo.entity.User manager = (com.example.demo.entity.User) request.getAttribute("manager");
    Set<User> employees = (Set<User>) request.getAttribute("employees");
    String stage = (String) request.getAttribute("stage");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Project Details</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ProjectDetails.css">
</head>
<body>

<div class="container">
    <h1>Project Details</h1>

    <p><b>Name:</b> <%= project.getName() %></p>
    <p><b>Description:</b> <%= project.getDescription() %></p>
    <p><b>Start Date:</b> <%= project.getSdate() %></p>
    <p><b>End Date:</b> <%= project.getEdate() %></p>
    <p><b>Stage:</b> <%= stage %></p>

    <h3>Manager:</h3>
    <p><%= (manager != null) ? manager.getName() : "No Manager Assigned" %></p>

    <h3>Assigned Employees:</h3>
    <table border="1" style="width:100%; border-collapse:collapse;">
        <tr>
            <th>Name</th>
            <th>Email</th>
        </tr>
        <% if (employees != null && !employees.isEmpty()) {
               for(User u : employees) { %>
            <tr>
                <td><%= u.getName() %></td>
                <td><%= u.getEmail() %></td>
            </tr>
        <% } } else { %>
            <tr>
                <td colspan="2" style="text-align:center;">No Employees Assigned</td>
            </tr>
        <% } %>
    </table>

    <div class="actions">
        <a href="${pageContext.request.contextPath}/projects" class="btn">Back to Projects</a>
    </div>
</div>

</body>
</html>
