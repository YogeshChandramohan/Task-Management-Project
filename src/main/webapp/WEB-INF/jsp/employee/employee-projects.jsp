<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Set, com.example.demo.entity.Project, com.example.demo.entity.User" %>

<%
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader("Expires", 0);

    com.example.demo.entity.User employee =
        (com.example.demo.entity.User) request.getAttribute("employee");
    Set<Project> projects = (Set<Project>) request.getAttribute("projects");
%>

<!DOCTYPE html>
<html>
<head>
    <title>My Projects</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/EmployeeProjects.css">
</head>
<body>

    <h1>Projects Assigned to <%= employee.getName() %></h1>

    <table border="1" cellpadding="10" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Start</th>
            <th>End</th>
            <th>View Tasks</th>
        </tr>
        <%
            if (projects != null && !projects.isEmpty()) {
                for (Project p : projects) {
        %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getName() %></td>
            <td><%= p.getDescription() %></td>
            <td><%= p.getSdate() %></td>
            <td><%= p.getEdate() %></td>
            <td>
                <a href="${pageContext.request.contextPath}/employee/project/<%= p.getId() %>/tasks" class="btn">View Tasks</a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="6" style="text-align:center;">No Projects Assigned</td>
        </tr>
        <%
            }
        %>
    </table>

    <a href="${pageContext.request.contextPath}/employeeDashboard" class="btn">⬅ Back to Dashboard</a>

</body>
</html>
