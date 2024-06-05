package org.example.l7_20211049.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.l7_20211049.beans.Departments;
import org.example.l7_20211049.beans.Employee;
import org.example.l7_20211049.beans.Job;
import org.example.l7_20211049.dao.DaoDepartment;
import org.example.l7_20211049.dao.DaoEmployee;
import org.example.l7_20211049.dao.DaoJobs;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "EmployeeServlet", urlPatterns = {"/EmployeeServlet"})

public class EmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        RequestDispatcher view;
        DaoEmployee employeeDao = new DaoEmployee();
        DaoJobs jobDao = new DaoJobs();
        DaoDepartment departmentDao = new DaoDepartment();

        switch (action) {
            case "lista":
                request.setAttribute("listaemployees", employeeDao.listarEmployees());
                view = request.getRequestDispatcher("employees/lista.jsp");
                view.forward(request, response);
                break;
            case "agregar":
                request.setAttribute("listaTrabajos",jobDao.obtenerListaTrabajos());
                request.setAttribute("listaJefes",employeeDao.listarEmployees());
                request.setAttribute("listaDepartamentos", departmentDao.lista());
                view = request.getRequestDispatcher("employees/formularioNuevo.jsp");
                view.forward(request, response);
                break;
            case "editar":
                if (request.getParameter("id") != null) {
                    String employeeIdString = request.getParameter("id");
                    int employeeId = 0;
                    try {
                        employeeId = Integer.parseInt(employeeIdString);
                    } catch (NumberFormatException e) {
                        response.sendRedirect("EmployeeServlet");

                    }

                    Employee emp = employeeDao.getEmployeeById(employeeId);

                    if (emp != null) {
                        request.setAttribute("empleado", emp);
                        request.setAttribute("listaTrabajos",jobDao.obtenerListaTrabajos());
                        request.setAttribute("listaJefes",employeeDao.listarEmployees());
                        request.setAttribute("listaDepartamentos",departmentDao.lista());
                        view = request.getRequestDispatcher("employees/formularioEditar.jsp");
                        view.forward(request, response);
                    } else {
                        response.sendRedirect("EmployeeServlet");
                    }

                } else {
                    response.sendRedirect("EmployeeServlet");
                }
                break;
            case "borrar":
                if (request.getParameter("id") != null) {
                    String employeeIdString = request.getParameter("id");
                    int employeeId = 0;
                    try {
                        employeeId = Integer.parseInt(employeeIdString);
                    } catch (NumberFormatException ex) {
                        response.sendRedirect("EmployeeServlet");
                    }

                    Employee emp = employeeDao.getEmployeeById(employeeId);

                    if (emp != null) {
                        employeeDao.deleteEmployee(employeeId);
                    }
                }
                response.sendRedirect("EmployeeServlet");
                break;
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        DaoEmployee employeeDao = new DaoEmployee();

        Employee employee = new Employee();
        employee.setFirstName(request.getParameter("first_name"));
        employee.setLastName(request.getParameter("last_name"));
        employee.setEmail(request.getParameter("email"));
        employee.setPhoneNumber(request.getParameter("phone"));
        employee.setHireDate(request.getParameter("hire_date"));

        Job job = new Job();
        job.setJobId(request.getParameter("job_id"));
        employee.setJob(job);
        employee.setSalary(new BigDecimal(request.getParameter("salary")));
        employee.setCommissionPct(request.getParameter("commission").isEmpty() ? null : new BigDecimal(request.getParameter("commission")));

        Employee manager = new Employee();
        manager.setEmployeeId(Integer.parseInt(request.getParameter("manager_id")));
        employee.setManager(manager);

        Departments department = new Departments();
        int departmentId = Integer.parseInt(request.getParameter("department_id"));
        if (departmentId != 0) {
            department.setDepartmentId(departmentId);
            employee.setDepartment(department);
        }

        switch (action) {
            case "guardar":
                employeeDao.addEmployee(employee);
                response.sendRedirect("EmployeeServlet");
                break;
            case "actualizar":
                employee.setEmployeeId(Integer.parseInt(request.getParameter("employee_id")));
                employeeDao.updateEmployee(employee);
                response.sendRedirect("EmployeeServlet");
                break;
        }
    }

}