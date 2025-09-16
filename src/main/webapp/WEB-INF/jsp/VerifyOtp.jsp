<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Verify OTP</title>
</head>
<body>
    <h2>Email Verification</h2>
    <p>We sent an OTP to your email: ${email}</p>

    <form action="${pageContext.request.contextPath}/verify-otp" method="post">
        <input type="hidden" name="email" value="${email}" />
        <label>Enter OTP:</label>
        <input type="text" name="otp" required />
        <button type="submit">Verify OTP</button>
    </form>

    <p style="color:red;">${error}</p>
</body>
</html>
