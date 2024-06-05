<%@ page import="org.example.l7_20211049.beans.Employee" %><%--
  Created by IntelliJ IDEA.
  User: alepa
  Date: 5/06/2024
  Time: 04:10
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="listaemployees" type="java.util.ArrayList<org.example.l7_20211049.beans.Employee>" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <title>Lista empleados</title>
</head>
<body>
<div class='container'>
    <h1 class='mb-3'>Lista de empleados</h1>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>">Home</a></li>
            <li class="breadcrumb-item active">Empleados</li>
        </ol>
    </nav>
    <a href="<%= request.getContextPath()%>/EmployeeServlet?action=agregar" class="btn btn-primary mb-4"> Agregar nuevo empleado</a>
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Employee</th>
            <th>Email</th>
            <th>Job</th>
            <th>Salary</th>
            <th>Commision</th>
            <th>Manager</th>
            <th>Department</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            int i = 1;
            for (Employee e : listaemployees) {
        %>
        <tr>
            <td><%= i%>
            </td>
            <td><%= e.getFirstName() + " " + e.getLastName()%>
            </td>
            <td><%= e.getEmail()%>
            </td>
            <td><%= e.getJob().getJobTitle()%>
            </td>
            <td><%= e.getSalary()%>
            </td>
            <td><%= e.getCommissionPct() == null ? "Sin comisión" : e.getCommissionPct()%>
            </td>
            <td><%= e.getManager() == null ? "-- Sin jefe --" : e.getManager().getFullNameEmployee() %>
            </td>
            <td><%= e.getDepartment().getDepartmentId() == 0 ? "-- Sin departamento --" : e.getDepartment().getDepartmentName()%>
            </td>
            <td>
                <a class="btn btn-primary"
                   href="<%=request.getContextPath()%>/EmployeeServlet?action=editar&id=<%=e.getEmployeeId()%>">
                    <i class="bi bi-pencil-square"></i>
                </a>
            </td>
            <td>
                <a class="btn btn-danger"
                   href="<%=request.getContextPath()%>/EmployeeServlet?action=borrar&id=<%=e.getEmployeeId()%>">
                    <i class="bi bi-trash3"></i>
                </a>
            </td>
        </tr>
        <%
                i++;
            }
        %>
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>