<%--
  Created by IntelliJ IDEA.
  User: alepa
  Date: 5/06/2024
  Time: 03:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<jsp:useBean id="trabajo" type="org.example.l7_20211049.beans.Job" scope="request"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <title>Editar un trabajo</title>
</head>
<body>
<div class='container'>
    <h1 class='mb-3'>Editar un trabajo</h1>
    <nav aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<%= request.getContextPath()%>">Home</a></li>
            <li class="breadcrumb-item active">Trabajos</li>
        </ol>
    </nav>

    <form method="POST" action="<%=request.getContextPath()%>/JobServlet?action=crear" class="col-md-6 col-lg-6">
        <input type="hidden" class="form-control" name="jobId" value="<%=trabajo.getJobId()%>">
        <div class="mb-3">
            <label for="jobTitle">Job Title</label>
            <input type="text" class="form-control" id="jobTitle" name="jobTitle" value="<%=trabajo.getJobTitle()%>">
        </div>
        <div class="mb-3">
            <label for="minSalary">Min Salary</label>
            <input type="text" class="form-control" id="minSalary" name="minSalary" value="<%=trabajo.getMinSalary()%>">
        </div>
        <div class="mb-3">
            <label for="maxSalary">Max Salary</label>
            <input type="text" class="form-control" id="maxSalary" name="maxSalary" value="<%=trabajo.getMaxSalary()%>">
        </div>
        <a href="<%=request.getContextPath()%>/JobServlet" class="btn btn-danger">Regresar</a>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"></script>
</body>
</html>


