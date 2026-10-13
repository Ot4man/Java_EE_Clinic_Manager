<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ClinicManager - Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="https://unpkg.com/lucide@latest"></script>
</head>
<body>

<div class="auth-container">
    <div class="auth-card">
        <div class="auth-header">
            <h1><i data-lucide="activity" style="color: var(--primary);"></i> ClinicManager</h1>
            <p>Welcome back! Please login to your account.</p>
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

        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="form-group">
                <label>Email Address</label>
                <input type="email" name="email" class="form-control" required placeholder="Enter your email">
            </div>

            <div class="form-group">
                <label>Password</label>
                <input type="password" name="password" class="form-control" required placeholder="Enter your password">
            </div>

            <button type="submit" class="btn-primary">
                Login <i data-lucide="arrow-right" size="18"></i>
            </button>
        </form>

        <div class="auth-footer">
            <p>Don't have an account? <a href="${pageContext.request.contextPath}/auth/register.jsp">Create one now</a></p>
        </div>
    </div>
</div>

<script>
    lucide.createIcons();
</script>
</body>
</html>