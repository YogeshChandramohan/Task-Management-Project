	<%@ page contentType="text/html;charset=UTF-8" language="java" %>
	<%@ page import="java.util.List, com.example.demo.entity.User" %>
	<!DOCTYPE html>
	<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <title>All User List</title>
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
	        <h1><i class="fa fa-users-cog"></i> All Users</h1>
	    </header>
	
	    <div class="container">
	
			<main class="main-content">
			            <h2>All Users</h2>
			            <table class="styled-table">
			                <thead>
			                    <tr>
			                        <th>ID</th>
			                        <th>Name</th>
			                        
			                        <th>Mobile</th>
			                        <th>Email</th>
			                        <th>Role</th>
									<th>Password</th>
			                        <th>Actions</th>
			                    </tr>
			                </thead>
			                <tbody>
			                <%
			                    List<User> users = (List<User>) request.getAttribute("users");
			                    if (users != null && !users.isEmpty()) {
			                        for (User m : users) {
			                %>
			                    <tr>
			                        <td><%= m.getId() %></td>
			                        <td><%= m.getName() %></td>
			                       
			                        <td><%= m.getMobile() %></td>	
			                        <td><%= m.getEmail() %></td>
			                        <td><%= m.getRole() %></td>
									<td><%= m.getPassword() %></td>
									<td>
			                            <a href="${pageContext.request.contextPath}/users/edit/<%= m.getId() %>" class="btn edit"><i class="fa fa-edit"></i> Edit</a>
			                            <a href="${pageContext.request.contextPath}/users/delete/<%= m.getId() %>" class="btn delete" onclick="return confirm('Are you sure you want to delete this User?');"><i class="fa fa-trash"></i> Delete</a>
			                        </td>
			                    </tr>
			                <%
			                        }
			                    } else {
			                %>
			                    <tr>
			                        <td colspan="7" style="text-align:center;">No User Found</td>
			                    </tr>
			                <%
			                    }
			                %>
			                </tbody>
			            </table>
	
			            <!-- ✅ Add New Manager Button -->
						<div class="add-new-container">
								<a href="${pageContext.request.contextPath}/register" class="btn add"><i class="fa fa-user-plus"></i> Add New User</a>
								<a href="${pageContext.request.contextPath}/admin/dashboard" class="btn home"><i class="fa fa-home"></i> Move to Dashboard</a>
							</div>
			        </main>
					
	    </div>
		
	</body>
	</html>
