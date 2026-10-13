<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>Add Patient</title>
</head>
<body>

<h1>Add Patient</h1>
<%
    String error = (String) request.getAttribute("error");

    if (error != null) {
%>

<p style="color:#782424;">
    <%= error %>
</p>

<%
    }
%>
<form action="${pageContext.request.contextPath}/admin/patients/new"
      method="post">

    <label>First name:</label>
    <input type="text"
           name="firstName"
           required>

    <br><br>

    <label>Last name:</label>
    <input type="text"
           name="lastName"
           required>

    <br><br>

    <label>Email:</label>
    <input type="email"
           name="email"
           required>

    <br><br>

    <label>Phone:</label>
    <input type="text"
           name="phone"
           required>

    <br><br>

    <label>Password:</label>
    <input type="password"
           name="password"
           minlength="6"
           required>

    <br><br>

    <label>CIN:</label>
    <input type="text"
           name="cin">

    <br><br>

    <label>Birth date:</label>
    <input type="date"
           name="birthDate">

    <br><br>

    <label>Gender:</label>
    <select name="gender">

        <option value="">Select</option>
        <option value="MALE">Male</option>
        <option value="FEMALE">Female</option>

    </select>

    <br><br>

    <label>Address:</label>
    <textarea name="address"></textarea>

    <br><br>

    <label>Blood type:</label>
    <select name="bloodType">

        <option value="">Select</option>
        <option value="A+">A+</option>
        <option value="A-">A-</option>
        <option value="B+">B+</option>
        <option value="B-">B-</option>
        <option value="AB+">AB+</option>
        <option value="AB-">AB-</option>
        <option value="O+">O+</option>
        <option value="O-">O-</option>

    </select>

    <br><br>

    <button type="submit">
        Add Patient
    </button>

</form>

<br>

<a href="${pageContext.request.contextPath}/admin/patients">
    Back to Patients
</a>
<a href="${pageContext.request.contextPath}/admin/patients/new">
    Add Patient
</a>

<br><br>
</body>
</html>