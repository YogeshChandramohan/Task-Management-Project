<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.example.demo.entity.Project,com.example.demo.entity.Project" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>All Project List</title>
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600;700&display=swap" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/UserList.css">
</head>
<body>
    <!-- Header -->
    <header class="header">
        <h1><i class="fa fa-users-cog"></i> All Projects</h1>
    </header>

    <div class="container">

		<main class="main-content">
		            <h2>All Projects</h2>
		            <table class="styled-table">
		                <thead>
		                    <tr>
		                        <th>ID</th>
		                        <th>Name</th>
		                        
		                        <th>Start Date</th>
		                        <th>Due Date</th>
		                        <th>Description</th>
								<th>Manager</th>
		                        <th>Actions</th>
								<th>View</th>
		                    </tr>
		                </thead>
		                <tbody>
		                <%
		                    List<Project> projects = (List<Project>) request.getAttribute("projects");
		                    if (projects != null && !projects.isEmpty()) {
		                        for (Project m : projects) {
		                %>
		                    <tr>
		                        <td><%= m.getId() %></td>
		                        <td><%= m.getName() %></td>
								<td><%= m.getDescription() %></td>
		                        <td><%= m.getSdate() %></td>	
		                        <td><%= m.getEdate() %></td>
								<td>
								    <%= (m.getManager() != null) ? m.getManager().getName() : "No Manager" %>
								</td>
								
								<td>
		                            <a href="${pageContext.request.contextPath}/projects/edit/<%= m.getId() %>" class="btn edit"><i class="fa fa-edit"></i> Edit</a>
		                            <a href="${pageContext.request.contextPath}/projects/delete/<%= m.getId() %>" class="btn delete" onclick="return confirm('Are you sure you want to delete this Project?');"><i class="fa fa-trash"></i> Delete</a>
		                        </td>
								<td>
								     <a href="${pageContext.request.contextPath}/projects/view/<%= m.getId() %>" class="btn view"><i class="fa fa-eye"></i> View</a>
								</td>
		                    </tr>
		                <%
		                        }
		                    } else {
		                %>
		                    <tr>
		                        <td colspan="7" style="text-align:center;">No Project Found</td>
		                    </tr>
		                <%
		                    }
		                %>
		                </tbody>
		            </table>

		            <!-- ✅ Add New Manager Button -->
					<div class="add-new-container">
							<a href="${pageContext.request.contextPath}/project/AddProject" class="btn add"><i class="fa fa-user-plus"></i> Add New Project</a>
							<a href="${pageContext.request.contextPath}/admin/dashboard" class="btn home"><i class="fa fa-home"></i> Move to Dashboard</a>
						</div>
		        </main>
				
    </div>
	
</body>
</html>
