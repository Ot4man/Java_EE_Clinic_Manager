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
  <title>Patient Dashboard - ClinicManager</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles.css">
  <script src="https://unpkg.com/lucide@latest"></script>
</head>
<body>
  
  <aside class="sidebar fade-in">
      <div class="brand">
          <div class="brand-icon">
              <i data-lucide="activity"></i>
          </div>
          ClinicManager
      </div>

      <nav>
          <ul class="nav-menu">
              <li class="nav-item"><a href="${pageContext.request.contextPath}/patient/dashboard.jsp" class="active"><i data-lucide="layout-dashboard"></i> Dashboard</a></li>
              <li class="nav-item"><a href="${pageContext.request.contextPath}/patient/profile"><i data-lucide="user"></i> My Profile</a></li>
              <li class="nav-item" style="margin-top: auto;"><a href="${pageContext.request.contextPath}/logout" style="color: var(--danger);"><i data-lucide="log-out"></i> Logout</a></li>
          </ul>
      </nav>
  </aside>

  <main class="main-content">
      <header class="fade-in delay-1">
          <h1>Patient Dashboard</h1>
          <div class="user-profile">
              <div class="text-right">
                  <div style="font-weight: 600; font-size: 0.9rem;"><%= user.getFirstName() %> <%= user.getLastName() %></div>
                  <div style="color: var(--text-muted); font-size: 0.8rem;">Patient</div>
              </div>
              <div class="avatar" style="background-image: url('https://api.dicebear.com/7.x/avataaars/svg?seed=<%= user.getFirstName() %>');"></div>
          </div>
      </header>

      <section class="dashboard-grid fade-in delay-2">
          <div class="stat-card">
              <div class="stat-header">
                  <span>Welcome Back!</span>
                  <i data-lucide="smile" size="18"></i>
              </div>
              <div class="stat-value" style="font-size: 1.5rem;"><%= user.getFirstName() %> <%= user.getLastName() %></div>
              <div class="stat-trend" style="color: var(--text-muted)">
                  <i data-lucide="mail" size="14"></i> <%= user.getEmail() %>
              </div>
          </div>

          <div class="stat-card">
              <div class="stat-header">
                  <span>Upcoming Appointments</span>
                  <i data-lucide="calendar" size="18"></i>
              </div>
              <div class="stat-value">0</div>
              <div class="stat-trend">
                  <i data-lucide="info" size="14"></i> No appointments scheduled
              </div>
          </div>
      </section>

      <section class="test-panel fade-in delay-3">
          <h2>Quick Actions</h2>
          <p>Access your health records or update your personal details directly from your dashboard.</p>
          <div style="display: flex; gap: 1rem;">
              <a href="${pageContext.request.contextPath}/patient/profile" class="btn" style="text-decoration: none;">
                  <i data-lucide="edit-3"></i> Edit Profile
              </a>
              <a href="${pageContext.request.contextPath}/patient/change-password" class="btn" style="text-decoration: none; background: rgba(255,255,255,0.1);">
                  <i data-lucide="key"></i> Change Password
              </a>
          </div>
      </section>
  </main>

  <script>
      lucide.createIcons();
  </script>
</body>
</html>