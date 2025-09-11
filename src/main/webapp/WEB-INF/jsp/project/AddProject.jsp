<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.example.demo.entity.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add New Project</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/AddProject.css">
</head>
<body>
    <div class="form-container">
        <h2>Add New Project</h2>

        <form action="${pageContext.request.contextPath}/project/addProject" method="post" class="styled-form">
            <!-- Project Name -->
            <div class="form-group">
                <label for="name">Project Name:</label>
                <input type="text" id="name" name="name" class="input-field" required>
            </div>

            <!-- Description -->
            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" class="input-field" required></textarea>
            </div>

            <!-- Start Date -->
            <div class="form-group">
                <label for="sdate">Start Date:</label>
                <input type="date" id="sdate" name="sdate" class="input-field" required>
            </div>

            <!-- Due Date -->
            <div class="form-group">
                <label for="edate">Due Date:</label>
                <input type="date" id="edate" name="edate" class="input-field" required>
            </div>

            <!-- Manager Dropdown -->
            <div class="form-group">
                <label for="manager">Manager:</label>
                <select id="manager" name="managerId" class="input-field" required>
                    <option value="">-- Select Manager --</option>
                    <%
                        List<User> managers = (List<User>) request.getAttribute("managers");
                        if (managers != null) {
                            for (User m : managers) {
                    %>
                        <option value="<%= m.getId() %>"><%= m.getName() %></option>
                    <%
                            }
                        }
                    %>
                </select>
            </div>

            <!-- Buttons -->
            <div class="form-actions">
                <button type="submit" class="btn">Save</button>
                <a href="${pageContext.request.contextPath}/projects" class="btn cancel">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>
