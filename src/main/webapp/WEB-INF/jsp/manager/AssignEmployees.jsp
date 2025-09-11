<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.example.demo.entity.User, com.example.demo.entity.Project" %>
<%
    Project project = (Project) request.getAttribute("project");
    List<User> employees = (List<User>) request.getAttribute("employees");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Assign Employees</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AssignEmployee.css">
</head>
<body>
    <h2>Assign Employees to Project: <%= project.getName() %></h2>

    <form method="post" action="${pageContext.request.contextPath}/projects/<%= project.getId() %>/assignEmployees">
        <table border="1">
            <tr>
                <th>Select</th>
                <th>Name</th>
                <th>Email</th>
            </tr>
            <% for(User emp : employees) { %>
                <tr>
                    <td><input type="checkbox" name="employeeIds" value="<%= emp.getId() %>"></td>
                    <td><%= emp.getName() %></td>
                    <td><%= emp.getEmail() %></td>
                </tr>
            <% } %>
        </table>
        <br>
        <button type="submit">Assign Selected Employees</button>
    </form>

    <a href="${pageContext.request.contextPath}/projects/ManagerProjectView/<%= project.getId() %>">⬅ Back to Project</a>
</body>
</html>
