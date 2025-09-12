<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.example.demo.entity.Task, com.example.demo.entity.User" %>

<%
    com.example.demo.entity.User employee =
        (com.example.demo.entity.User) request.getAttribute("employee");
    List<Task> tasks = (List<Task>) request.getAttribute("tasks");
    Long projectId = (Long) request.getAttribute("projectId");
%>

<!DOCTYPE html>
<html>
<head>
    <title>My Tasks</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/EmployeeTasks.css">
</head>
<body>

    <h1>Tasks for Project ID <%= projectId %> (Employee: <%= employee.getName() %>)</h1>

    <table border="1" cellpadding="10" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Description</th>
            <th>Due Date</th>
			<th>Status</th>
			<th>Action</th>
        </tr>
        <%
            if (tasks != null && !tasks.isEmpty()) {
                for (Task t : tasks) {
        %>
        <tr>
            <td><%= t.getId() %></td>
            <td><%= t.getTitle() %></td>
            <td><%= t.getDescription() %></td>
            <td><%= t.getDueDate() %></td>
			<td><%= t.getStatus() %></td>
			<td>
			              <form action="${pageContext.request.contextPath}/employee/task/<%= t.getId() %>/updateStatus" method="post">
			                  <select name="status">
			                      <option value="PENDING" <%= "PENDING".equals(t.getStatus()) ? "selected" : "" %>>Pending</option>
			                      <option value="IN_PROGRESS" <%= "IN_PROGRESS".equals(t.getStatus()) ? "selected" : "" %>>In Progress</option>
			                      <option value="COMPLETED" <%= "COMPLETED".equals(t.getStatus()) ? "selected" : "" %>>Completed</option>
			                  </select>
			                  <button type="submit">Update</button>
			              </form>
			          </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="4" style="text-align:center;">No Tasks Assigned</td>
        </tr>
        <%
            }
        %>
    </table>

    <a href="${pageContext.request.contextPath}/employee/projects/<%= employee.getId() %>" class="btn">
        ⬅ Back to Projects
    </a>

</body>
</html>
