
<%@ page import="com.clinicmanager.model.Patient" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    Patient patient = (Patient) request.getAttribute("patient");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Patient</title>
</head>
<body>

<h1>Edit Patient</h1>

<form action="${pageContext.request.contextPath}/admin/patients/edit"
      method="post">

    <input type="hidden" name="id"
           value="<%= patient.getId() %>">

    <label>First name:</label>
    <input type="text" name="firstName"
           value="<%= patient.getFirstName() %>" required>
    <br><br>

    <label>Last name:</label>
    <input type="text" name="lastName"
           value="<%= patient.getLastName() %>" required>
    <br><br>

    <label>Email:</label>
    <input type="email" value="<%= patient.getEmail() %>" disabled>
    <br><br>

    <label>Phone:</label>
    <input type="text" name="phone"
           value="<%= patient.getPhone() != null ? patient.getPhone() : "" %>"
           required>
    <br><br>

    <label>CIN:</label>
    <input type="text" name="cin"
           value="<%= patient.getCin() != null ? patient.getCin() : "" %>">
    <br><br>

    <label>Birth date:</label>
    <input type="date" name="birthDate"
           value="<%= patient.getBirthDate() != null ? patient.getBirthDate() : "" %>">
    <br><br>

    <label>Gender:</label>
    <select name="gender">
        <option value="">Select</option>
        <option value="MALE"
                <%= "MALE".equals(patient.getGender()) ? "selected" : "" %>>
            Male
        </option>
        <option value="FEMALE"
                <%= "FEMALE".equals(patient.getGender()) ? "selected" : "" %>>
            Female
        </option>
    </select>
    <br><br>

    <label>Address:</label>
    <textarea name="address"><%= patient.getAddress() != null ? patient.getAddress() : "" %></textarea>
    <br><br>

    <label>Blood type:</label>
    <select name="bloodType">
        <option value="">Select</option>
        <option value="A+" <%= "A+".equals(patient.getBloodType()) ? "selected" : "" %>>A+</option>
        <option value="A-" <%= "A-".equals(patient.getBloodType()) ? "selected" : "" %>>A-</option>
        <option value="B+" <%= "B+".equals(patient.getBloodType()) ? "selected" : "" %>>B+</option>
        <option value="B-" <%= "B-".equals(patient.getBloodType()) ? "selected" : "" %>>B-</option>
        <option value="AB+" <%= "AB+".equals(patient.getBloodType()) ? "selected" : "" %>>AB+</option>
        <option value="AB-" <%= "AB-".equals(patient.getBloodType()) ? "selected" : "" %>>AB-</option>
        <option value="O+" <%= "O+".equals(patient.getBloodType()) ? "selected" : "" %>>O+</option>
        <option value="O-" <%= "O-".equals(patient.getBloodType()) ? "selected" : "" %>>O-</option>
    </select>
    <br><br>

    <button type="submit">Save Changes</button>

</form>

<br>

<a href="${pageContext.request.contextPath}/admin/patients">
    Back to Patients
</a>

</body>
</html>