<%@ page import="java.util.List" %>
<%@ page import="com.clinicmanager.model.Patient" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<%
    List<Patient> patients =
            (List<Patient>) request.getAttribute("patients");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Patients</title>
</head>
<body>

<h1>Patients</h1>

<table border="1">

    <tr>
        <th>ID</th>
        <th>CIN</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
        <th>Phone</th>
        <th>Birth Date</th>
        <th>Gender</th>
        <th>Blood Type</th>
        <th>Actions</th>
    </tr>

    <% for (Patient patient : patients) { %>

    <tr>

        <td>
            <%= patient.getId() %>
        </td>

        <td>
            <%= patient.getCin() %>
        </td>

        <td>
            <%= patient.getFirstName() %>
        </td>

        <td>
            <%= patient.getLastName() %>
        </td>

        <td>
            <%= patient.getEmail() %>
        </td>

        <td>
            <%= patient.getPhone() %>
        </td>

        <td>
            <%= patient.getBirthDate() %>
        </td>

        <td>
            <%= patient.getGender() %>
        </td>

        <td>
            <%= patient.getBloodType() %>
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/admin/patients/edit?id=<%= patient.getId() %>">
                Edit
            </a>
        </td>
    </tr>

    <% } %>

</table>

</body>
</html>