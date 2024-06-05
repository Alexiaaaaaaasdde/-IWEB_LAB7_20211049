<%--
  Created by IntelliJ IDEA.
  User: alepa
  Date: 5/06/2024
  Time: 04:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="org.example.l7_20211049.beans.Job" %>
<jsp:useBean type="java.util.ArrayList<org.example.l7_20211049.beans.Job>" scope="request" id="lista"/>

<!DOCTYPE html>
<html>
<head>
    <title>Lista Trabajos</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
<div class='container'>
    <h1 class='mb-3'>Lista de trabajos en hr</h1>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>">Home</a></li>
            <li class="breadcrumb-item active">Trabajos</li>
        </ol>
    </nav>
    <a class="btn btn-primary mb-3" href="<%=request.getContextPath()%>/JobServlet?action=crear">Crear
        Trabajo</a>
    <table class="table">
        <tr>
            <th>Job ID</th>
            <th>Job Name</th>
            <th>Min Salary</th>
            <th>Max Salary</th>
            <th></th>
            <th></th>
        </tr>
        <%
            for (Job job : lista) {
        %>
        <tr>
            <td><%=job.getJobId()%>
            </td>
            <td><%=job.getJobTitle()%>
            </td>
            <td><%=job.getMinSalary()%>
            </td>
            <td><%=job.getMaxSalary()%>
            </td>
            <td>
                <a class="btn btn-primary"
                   href="<%=request.getContextPath()%>/JobServlet?action=editar&id=<%=job.getJobId()%>">Editar
                    <i class="bi bi-pencil-square"></i>
                </a>
            </td>
            <td>
                <a onclick="return confirm('¿Esta seguro de borrar?')"
                   class="btn btn-danger"
                   href="<%=request.getContextPath()%>/JobServlet?action=borrar&id=<%=job.getJobId()%>">Borrar
                    <i class="bi bi-trash3"></i>
                </a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>

