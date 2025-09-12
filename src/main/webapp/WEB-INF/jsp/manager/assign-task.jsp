<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.demo.entity.Task, com.example.demo.entity.User, com.example.demo.entity.Project, java.util.List" %>

<%
    Project project = (Project) request.getAttribute("project");
    User employee = (User) request.getAttribute("employee");
    List<Task> tasks = (List<Task>) request.getAttribute("tasks");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Assign Tasks</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AssignTask.css">
</head>
<body>

<div class="container">
    <h2>Assign Task to <%= employee.getName() %></h2>
    <p><b>Project:</b> <%= project.getName() %></p>

    <!-- ✅ Form to assign a new task -->
    <form action="${pageContext.request.contextPath}/projects/<%= project.getId() %>/assignTask/<%= employee.getId() %>" method="post">
        <label>Task Title:</label>
        <input type="text" name="title" required>

        <label>Description:</label>
        <textarea name="description" required></textarea>

        <label>Due Date:</label>
        <input type="date" name="dueDate" required>

        <button type="submit" class="btn">Add Task</button>
    </form>

    <hr>

    <!-- ✅ List of already assigned tasks -->
    <h3>Tasks Assigned</h3>
    <table>
        <tr>
            <th>Title</th>
            <th>Description</th>
            <th>Due Date</th>
            <th>Status</th>
        </tr>
        <% for(Task t : tasks) { %>
            <tr>
                <td><%= t.getTitle() %></td>
                <td><%= t.getDescription() %></td>
                <td><%= t.getDueDate() %></td>
                <td><%= t.getStatus() %></td>
            </tr>
        <% } %>
    </table>

    <a href="${pageContext.request.contextPath}/projects/ManagerProjectView/<%= project.getId() %>" class="btn">Back to Project</a>
</div>

</body>
</html>
