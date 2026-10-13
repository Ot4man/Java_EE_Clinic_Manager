<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ClinicManager - Register</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="https://unpkg.com/lucide@latest"></script>
</head>
<body>

<div class="auth-container">
    <div class="auth-card">
        <div class="auth-header">
            <h1><i data-lucide="activity" style="color: var(--primary);"></i> ClinicManager</h1>
            <p>Create your new account</p>
        </div>

        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
        <div style="background: rgba(239, 68, 68, 0.1); color: var(--danger); padding: 10px; border-radius: 8px; margin-bottom: 1rem; border: 1px solid rgba(239, 68, 68, 0.2); text-align: center;">
            <%= error %>
        </div>
        <%
            }
        %>

        <form action="${pageContext.request.contextPath}/register" method="post">
            <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 1rem;">
                <div class="form-group">
                    <label>First Name</label>
                    <input type="text" name="firstName" class="form-control" required placeholder="First name">
                </div>
                <div class="form-group">
                    <label>Last Name</label>
                    <input type="text" name="lastName" class="form-control" required placeholder="Last name">
                </div>
            </div>

            <div class="form-group">
                <label>Email Address</label>
                <input type="email" name="email" class="form-control" required placeholder="Email address">
            </div>

            <div class="form-group">
                <label>Phone Number</label>
                <input type="text" name="phone" class="form-control" required placeholder="Phone number">
            </div>

            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" class="form-control" required minlength="6" placeholder="Password (min 6 characters)">
            </div>

            <div class="form-group">
                <label>Confirm Password</label>
                <input type="password" name="confirmPassword" class="form-control" required minlength="6" placeholder="Confirm password">
            </div>

            <button type="submit" class="btn-primary">
                Register <i data-lucide="user-plus" size="18"></i>
            </button>
        </form>

        <div class="auth-footer">
            <p>Already have an account? <a href="${pageContext.request.contextPath}/auth/login.jsp">Login here</a></p>
        </div>
    </div>
</div>

<script>
    lucide.createIcons();
</script>
</body>
</html>