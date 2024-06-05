<%@ page import="org.example.l7_20211049.beans.Job" %>
<%@ page import="org.example.l7_20211049.beans.Employee" %>
<%@ page import="org.example.l7_20211049.beans.Departments" %><%--
  Created by IntelliJ IDEA.
  User: alepa
  Date: 5/06/2024
  Time: 04:09
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<jsp:useBean id="listaTrabajos" type="java.util.ArrayList<org.example.l7_20211049.beans.Job>" scope="request" />
<jsp:useBean id="listaJefes" type="java.util.ArrayList<org.example.l7_20211049.beans.Employee>" scope="request" />
<jsp:useBean id="listaDepartamentos" type="java.util.ArrayList<org.example.l7_20211049.beans.Departments>" scope="request" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <title>Nuevo empleado</title>
</head>
<body>
<div class='container'>
    <div class="row justify-content-center">
        <form method="POST" action="EmployeeServlet?action=guardar" class="col-md-6 col-lg-6">
            <h1 class='mb-3'>Nuevo usuario</h1>
            <hr>
            <div class="mb-3">
                <label for="first_name">First Name</label>
                <input type="text" class="form-control form-control-sm" name="first_name" id="first_name">
            </div>
            <div class="mb-3">
                <label for="last_name">Last Name</label>
                <input type="text" class="form-control form-control-sm" name="last_name" id="last_name">
            </div>
            <div class="mb-3">
                <label for="email">Email</label>
                <input type="text" class="form-control form-control-sm" name="email" id="email">
            </div>
            <div class="mb-3">
                <label for="phone">Phone number</label>
                <input type="text" class="form-control form-control-sm" name="phone" id="phone">
            </div>
            <div class="mb-3">
                <label for="hire_date">Hire date</label>
                <input type="text" class="form-control form-control-sm" name="hire_date" id="hire_date">
            </div>
            <div class="mb-3">
                <label for="job_id">Job</label>
                <select name="job_id" class="form-select" id="job_id">
                    <% for(Job job: listaTrabajos){ %>
                    <option value="<%=job.getJobId()%>"> <%=job.getJobTitle()%> </option>
                    <% } %>
                </select>
            </div>
            <div class="mb-3">
                <label for="salary">Salary</label>
                <input type="text" class="form-control form-control-sm" name="salary" id="salary">
            </div>
            <div class="mb-3">
                <label for="commission">Commision PCT</label>
                <input type="text" class="form-control form-control-sm" name="commission" id="commission">
            </div>
            <div class="mb-3">
                <label for="manager_id">Manager</label>
                <select name="manager_id" class="form-select" id="manager_id">
                    <option value="0">-- Sin jefe --</option>
                    <% for(Employee manager: listaJefes){ %>
                    <option value="<%=manager.getEmployeeId()%>"> <%=manager.getFullNameEmployee()%> </option>
                    <% } %>
                </select>
            </div>
            <div class="mb-3">
                <label for="department_id">Department</label>
                <select name="department_id" class="form-select" id="department_id">
                    <option value="0">-- Sin departamento --</option>
                    <% for(Departments department: listaDepartamentos){ %>
                    <option value="<%=department.getDepartmentId()%>"> <%=department.getDepartmentName()%> </option>
                    <% } %>
                </select>
            </div>
            <a href="<%= request.getContextPath()%>/EmployeeServlet" class="btn btn-danger">Cancelar</a>
            <input type="submit" value="Guardar" class="btn btn-primary"/>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>