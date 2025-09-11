<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.demo.entity.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit User</title>
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;600;700&display=swap" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/EditUser.css">
</head>
<body>

    <div class="form-container">
        <h2><i class="fa fa-user-edit"></i> Edit User</h2>

        <form action="${pageContext.request.contextPath}/users/update" method="post">
            <input type="hidden" name="id" value="${user.id}">
            <input type="hidden" name="role" value="${user.role}">

            <label>Name</label>
            <input type="text" name="name" value="${user.name}" required>

            <label>Email</label>
            <input type="email" name="email" value="${user.email}" required>

            <label>Mobile Number</label>
            <input type="text" name="mobile" value="${user.mobile}" required>

            <label>Password</label>
            <input type="password" name="password" value="${user.password}" required>

            <div class="form-actions">
                <button type="submit" class="btn update"><i class="fa fa-save"></i> Update</button>
                <a href="${pageContext.request.contextPath}/users" class="btn cancel"><i class="fa fa-times"></i> Cancel</a>
            </div>
        </form>
    </div>

</body>
</html>
