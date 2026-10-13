<%@ page import="com.clinicmanager.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/auth/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Password - ClinicManager</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="https://unpkg.com/lucide@latest"></script>
    <style>
        .profile-container {
            background: var(--bg-panel);
            backdrop-filter: blur(12px);
            border: 1px solid var(--border);
            border-radius: 16px;
            padding: 2rem;
            max-width: 500px;
        }
    </style>
</head>
<body style="display: flex; background-image: radial-gradient(at 0% 0%, rgba(79, 70, 229, 0.15) 0px, transparent 50%), radial-gradient(at 100% 100%, rgba(16, 185, 129, 0.15) 0px, transparent 50%); min-height: 100vh;">

  <aside class="sidebar fade-in">
      <div class="brand">
          <div class="brand-icon">
              <i data-lucide="activity"></i>
          </div>
          ClinicManager
      </div>

      <nav>
          <ul class="nav-menu">
              <li class="nav-item"><a href="${pageContext.request.contextPath}/patient/dashboard.jsp"><i data-lucide="layout-dashboard"></i> Dashboard</a></li>
              <li class="nav-item"><a href="${pageContext.request.contextPath}/patient/profile" class="active"><i data-lucide="user"></i> My Profile</a></li>
              <li class="nav-item" style="margin-top: auto;"><a href="${pageContext.request.contextPath}/logout" style="color: var(--danger);"><i data-lucide="log-out"></i> Logout</a></li>
          </ul>
      </nav>
  </aside>

  <main class="main-content">
      <header class="fade-in delay-1">
          <h1>Change Password</h1>
          <div class="user-profile">
              <div class="text-right">
                  <div style="font-weight: 600; font-size: 0.9rem;"><%= user.getFirstName() %> <%= user.getLastName() %></div>
                  <div style="color: var(--text-muted); font-size: 0.8rem;">Patient</div>
              </div>
              <div class="avatar" style="background-image: url('https://api.dicebear.com/7.x/avataaars/svg?seed=<%= user.getFirstName() %>');"></div>
          </div>
      </header>

      <section class="fade-in delay-2">
          <div class="profile-container">
              <h2 style="margin-bottom: 1.5rem; font-size: 1.25rem;">Security Settings</h2>
              
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

              <form action="${pageContext.request.contextPath}/patient/change-password" method="post">
                  
                  <div class="form-group">
                      <label>Old Password</label>
                      <input type="password" name="oldPassword" class="form-control" required placeholder="Enter current password">
                  </div>

                  <div class="form-group">
                      <label>New Password</label>
                      <input type="password" name="newPassword" class="form-control" required minlength="6" placeholder="Enter new password">
                  </div>

                  <div class="form-group">
                      <label>Confirm New Password</label>
                      <input type="password" name="confirmPassword" class="form-control" required minlength="6" placeholder="Confirm new password">
                  </div>

                  <div style="display: flex; gap: 1rem; margin-top: 1.5rem;">
                      <button type="submit" class="btn-primary" style="width: auto;">
                          <i data-lucide="shield-check" size="18"></i> Update Password
                      </button>
                      <a href="${pageContext.request.contextPath}/patient/profile" class="btn" style="text-decoration: none; background: rgba(255,255,255,0.1); width: auto;">
                          Cancel
                      </a>
                  </div>
              </form>
          </div>
      </section>
  </main>

  <script>
      lucide.createIcons();
  </script>
</body>
</html>