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
<jsp:useBean id="listaDepartamentos" type="java.util.ArrayList<org.example.l7_20211049.beans.Departments>" scope="request"/>
<jsp:useBean id="empleado" type="org.example.l7_20211049.beans.Employee" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <title>Editar empleado</title>
</head>
<body>
<div class='container mb-4'>
    <div class="row justify-content-center">
        <h1 class='mb-3'>Editar usuario</h1>
        <hr>
        <form method="POST" action="EmployeeServlet?action=actualizar" class="col-md-6 col-lg-6">
            <input type="hidden" name="employee_id" value="<%= empleado.getEmployeeId()%>"/>
            <div class="mb-3">
                <label for="first_name">First Name</label>
                <input type="text" class="form-control form-control-sm" name="first_name" id="first_name"
                       value="<%= empleado.getFirstName() == null ? "" : empleado.getFirstName()%>">
            </div>
            <div class="mb-3">
                <label for="last_name">Last Name</label>
                <input type="text" class="form-control form-control-sm" name="last_name" id="last_name"
                       value="<%= empleado.getLastName() == null ? "" : empleado.getLastName()%>">
            </div>
            <div class="mb-3">
                <label for="email">Email</label>
                <input type="text" class="form-control form-control-sm" name="email" id="email"
                       value="<%= empleado.getEmail() == null ? "" : empleado.getEmail()%>">
            </div>
            <div class="mb-3">
                <label for="phone">Phone number</label>
                <input type="text" class="form-control form-control-sm" name="phone" id="phone"
                       value="<%= empleado.getPhoneNumber() == null ? "" : empleado.getPhoneNumber()%>">
            </div>
            <div class="mb-3">
                <label for="hire_date">Hire date</label>
                <input type="text" class="form-control form-control-sm" name="hire_date" id="hire_date"
                       value="<%= empleado.getHireDate() == null ? "" : empleado.getHireDate()%>">
            </div>
            <div class="mb-3">
                <label for="job_id">Job</label>
                <select name="job_id" class="form-select" id="job_id">
                    <% for(Job job: listaTrabajos){ %>
                    <option value="<%=job.getJobId()%>"  <%=job.getJobId().equals(empleado.getJob().getJobId())?"selected":""%>   > <%=job.getJobTitle()%> </option>
                    <% } %>
                </select>
            </div>
            <div class="mb-3">
                <label for="salary">Salary</label>
                <input type="text" class="form-control form-control-sm" name="salary" id="salary"
                       value="<%= empleado.getSalary().equals("0.0") ? "" : empleado.getSalary()%>">
            </div>
            <div class="mb-3">
                <label for="commission">Commision PCT</label>
                <input type="text" class="form-control form-control-sm" name="commission" id="commission"
                       value="<%= empleado.getCommissionPct() == null ? "" : empleado.getCommissionPct()%>">
            </div>
            <div class="mb-3">
                <label for="manager_id">Manager</label>
                <select name="manager_id" class="form-select" id="manager_id">
                    <option value="0">-- Sin jefe --</option>
                    <% for(Employee manager: listaJefes){ %>
                    <option value="<%=manager!=null ? manager.getEmployeeId() : 0%>" <%=manager!=null && empleado.getManager()!=null && (manager.getEmployeeId() == empleado.getManager().getEmployeeId())?"selected":""%>  > <%=manager!=null? manager.getFullNameEmployee(): ""%> </option>
                    <% } %>
                </select>
            </div>
            <div class="mb-3">
                <label for="department_id">Department</label>
                <select name="department_id" class="form-select" id="department_id">
                    <option value="0">-- Sin departamento --</option>
                    <% for(Departments department : listaDepartamentos){ %>
                    <option value="<%=department.getDepartmentId()%>"
                            <%= (empleado.getDepartment() != null && department.getDepartmentId() == empleado.getDepartment().getDepartmentId()) ? "selected" : "" %>>
                        <%=department.getDepartmentName()%>
                    </option>
                    <% } %>
                </select>
            </div>

            <a href="<%= request.getContextPath()%>/EmployeeServlet" class="btn btn-danger">Cancelar</a>
            <input type="submit" value="Actualizar" class="btn btn-primary"/>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>