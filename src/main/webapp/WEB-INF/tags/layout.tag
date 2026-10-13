<%@tag description="Main Layout Tag" pageEncoding="UTF-8"%>
<%@attribute name="title" fragment="true" %>
<%@attribute name="isAuth" type="java.lang.Boolean" required="false" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><jsp:invoke fragment="title"/> - ClinicManager</title>
    <!-- CSS and Fonts -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="https://unpkg.com/lucide@latest"></script>
</head>
<body>
    
    <% if (isAuth != null && isAuth) { %>
        <!-- Auth pages don't need the sidebar -->
        <jsp:doBody/>
    <% } else { %>
        <!-- Main Dashboard Layout -->
        <div style="display: flex; min-height: 100vh;">
            <jsp:doBody/>
        </div>
    <% } %>

    <script>
        lucide.createIcons();
    </script>
</body>
</html>
