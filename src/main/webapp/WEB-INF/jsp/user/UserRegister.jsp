<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Registration</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/UserRegistration.css">
    <script>
        let otpVerified = false;

        function sendOtp() {
            const email = document.getElementById("email").value;
            if (!email) {
                alert("Enter email first");
                return;
            }
            fetch("${pageContext.request.contextPath}/send-otp", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: "email=" + encodeURIComponent(email)
            }).then(res => res.text())
              .then(data => {
                  alert(data);
                  document.getElementById("otp-section").style.display = "block";
              });
        }

        function verifyOtp() {
            const email = document.getElementById("email").value;
            const otp = document.getElementById("otp").value;
            fetch("${pageContext.request.contextPath}/verify-otp", {
                method: "POST",
                headers: { "Content-Type": "application/x-www-form-urlencoded" },
                body: "email=" + encodeURIComponent(email) + "&otp=" + encodeURIComponent(otp)
            }).then(res => res.text())
              .then(valid => {
                  if (valid === "true") {
                      alert("OTP Verified!");
                      otpVerified = true;
                      document.getElementById("register-btn").disabled = false;
                  } else {
                      alert("Invalid OTP!");
                      otpVerified = false;
                      document.getElementById("register-btn").disabled = true;
                  }
              });
        }

        function validateBeforeSubmit() {
            if (!otpVerified) {
                alert("Please verify OTP before registering.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="form-container">
    <h2><i class="fa fa-user-plus"></i> User Registration</h2>

    <form:form action="${pageContext.request.contextPath}/register" 
               method="post" 
               modelAttribute="user"
               cssClass="styled-form"
               onsubmit="return validateBeforeSubmit()">

        <div class="form-group">
            <label><i class="fa fa-user"></i> Name</label>
            <form:input path="name" id="name" cssClass="input-field" />
        </div>

        <div class="form-group">
            <label><i class="fa fa-phone"></i> Mobile</label>
            <form:input path="mobile" id="mobile" cssClass="input-field" />
        </div>

        <div class="form-group">
            <label><i class="fa fa-envelope"></i> Email</label>
            <form:input path="email" id="email" cssClass="input-field" />
            <button type="button" class="btn" onclick="sendOtp()"><i class="fa fa-paper-plane"></i> Send OTP</button>
        </div>

        <div class="form-group" id="otp-section" style="display:none;">
            <label><i class="fa fa-key"></i> Enter OTP</label>
            <input type="text" id="otp" class="input-field" />
            <button type="button" class="btn" onclick="verifyOtp()"><i class="fa fa-check-circle"></i> Verify OTP</button>
        </div>

        <div class="form-group">
            <label><i class="fa fa-lock"></i> Password</label>
            <form:password path="password" id="password" cssClass="input-field" />
        </div>

        <div class="form-group">
            <label><i class="fa fa-user-shield"></i> Role</label>
            <form:select path="role" id="role" cssClass="input-field">
                <form:option value="" label="-- Select Role --"/>
                <form:option value="ADMIN" label="Admin"/>
                <form:option value="MANAGER" label="Manager"/>
                <form:option value="EMPLOYEE" label="Employee"/>
            </form:select>
        </div>

        <div class="form-actions">
            <button type="submit" id="register-btn" class="btn submit" disabled>
                <i class="fa fa-user-check"></i> Register
            </button>
            <a href="${pageContext.request.contextPath}/users" class="btn cancel">
                <i class="fa fa-times-circle"></i> Cancel
            </a>
        </div>
    </form:form>
</div>

<!-- FontAwesome CDN for icons -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</body>
</html>
