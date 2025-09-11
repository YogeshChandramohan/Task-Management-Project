<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/UserRegistration.css">
</head>
<body>
    <div class="form-container">
        <h2><i class="fa fa-user-plus"></i> User Registration</h2>

        <form:form action="register" method="post" modelAttribute="user" class="styled-form">
            <div class="form-group">
                <label for="name"><i class="fa fa-user"></i> Name</label>
                <form:input path="name" cssClass="input-field" id="name"/>
            </div>

            <div class="form-group">
                <label for="mobile"><i class="fa fa-phone"></i> Mobile</label>
                <form:input path="mobile" cssClass="input-field" id="mobile"/>
            </div>

            <div class="form-group">
                <label for="email"><i class="fa fa-envelope"></i> Email</label>
                <form:input path="email" cssClass="input-field" id="email"/>
            </div>

            <div class="form-group">
                <label for="password"><i class="fa fa-lock"></i> Password</label>
                <form:password path="password" cssClass="input-field" id="password"/>
            </div>

            <div class="form-group">
                <label for="role"><i class="fa fa-id-badge"></i> Role</label>
                <form:input path="role" cssClass="input-field" id="role"/>
            </div>

            <div class="form-actions">
                <button type="submit" class="btn"><i class="fa fa-check-circle"></i> Register</button><br><br>
				 <a href="${pageContext.request.contextPath}/users" class="btn cancel"><i class="fa fa-times-circle"></i> Cancel</a>
            </div>
        </form:form>
    </div>
</body>
</html>
