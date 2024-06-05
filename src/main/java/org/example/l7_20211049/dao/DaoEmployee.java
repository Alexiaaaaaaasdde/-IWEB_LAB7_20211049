package org.example.l7_20211049.dao;

import org.example.l7_20211049.beans.Departments;
import org.example.l7_20211049.beans.Employee;
import org.example.l7_20211049.beans.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoEmployee extends DaoBase{
    // Método para listar todos los empleados
    public List<Employee> listarEmployees(){
        List<Employee> listaemployees = new ArrayList<>();
        String query = "SELECT * FROM employees e INNER JOIN jobs j ON e.job_id = j.job_id LEFT JOIN employees m ON e.manager_id = m.employee_id LEFT JOIN hr.departments d ON e.employee_id = d.manager_id JOIN departments d ON e.department_id = d.department_id ORDER BY e.employee_id";

        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Employee employee = mapResultSetToEmployee(rs);
                listaemployees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaemployees;
    }

    // Método para obtener un empleado por su ID
    public Employee getEmployeeById(int employeeId) {
        Employee employee = null;
        String query = "SELECT * FROM employees e INNER JOIN jobs j ON e.job_id = j.job_id LEFT JOIN employees m ON e.manager_id = m.employee_id LEFT JOIN departments d ON e.department_id = d.department_id WHERE e.employee_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, employeeId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    employee = mapResultSetToEmployee(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    // Método para añadir un nuevo empleado
    public void addEmployee(Employee employee) {
        // Definimos la consulta SQL para insertar un nuevo empleado en la base de datos.
        String query = "INSERT INTO employees (first_name, last_name, email, phone_number, hire_date, job_id, salary, manager_id, department_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Intentamos establecer una conexión con la base de datos y preparar la sentencia SQL.
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            // Llamamos al método para asignar los valores del objeto Employee a los parámetros de la sentencia SQL.
            setEmployeeParameters(employee, pstmt);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            // En caso de que ocurra una excepción SQL, imprimimos la traza de la pila.
            e.printStackTrace();
        }
    }

    // Método para actualizar un empleado existente
    public void updateEmployee(Employee employee) {
        String query = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, phone_number = ?, hire_date = ?, job_id = ?, salary = ?, manager_id = ?, department_id = ? WHERE employee_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            // Llamamos al método para asignar los valores del objeto Employee a los parámetros de la sentencia SQL.
            setEmployeeParameters(employee, pstmt);
            pstmt.setInt(11, employee.getEmployeeId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para borrar un empleado por su ID
    public void deleteEmployee(int id) {
        String query = "DELETE FROM employees WHERE employee_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Employee mapResultSetToEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt(1));
        employee.setFirstName(rs.getString(2));
        employee.setLastName(rs.getString(3));
        employee.setEmail(rs.getString(4));
        employee.setPhoneNumber(rs.getString(5));
        employee.setHireDate(rs.getString(6));

        Job job = new Job();
        job.setJobId(rs.getString("e.job_id"));
        job.setJobTitle(rs.getString("job_title"));
        employee.setJob(job);

        employee.setSalary(rs.getBigDecimal(8));
        employee.setCommissionPct(rs.getBigDecimal(9));

        Employee manager = null;
        if(rs.getInt("m.employee_id") != 0){
            manager = new Employee();
            manager.setEmployeeId(rs.getInt("m.employee_id"));
            manager.setFirstName(rs.getString("m.first_name"));
            manager.setLastName(rs.getString("m.last_name"));
            employee.setManager(manager);
        }

        Departments department = new Departments();
        department.setDepartmentId(rs.getInt(11));
        department.setDepartmentName(rs.getString("department_name"));
        employee.setDepartment(department);

        return employee;
    }

    private void setEmployeeParameters(Employee employee, PreparedStatement pstmt) throws SQLException {
        pstmt.setString(1, employee.getFirstName());
        pstmt.setString(2, employee.getLastName());
        pstmt.setString(3, employee.getEmail());
        pstmt.setString(4, employee.getPhoneNumber());
        pstmt.setString(5, employee.getHireDate());
        pstmt.setString(6, employee.getJob().getJobId());
        pstmt.setBigDecimal(7, employee.getSalary());

        if (employee.getCommissionPct() == null) {
            pstmt.setNull(8, Types.DECIMAL);
        } else {
            pstmt.setBigDecimal(8, employee.getCommissionPct());
        }

        if (employee.getManager() == null || employee.getManager().getEmployeeId() == 0) {
            pstmt.setNull(9, Types.INTEGER);
        } else {
            pstmt.setInt(9, employee.getManager().getEmployeeId());
        }

        if (employee.getDepartment() == null || employee.getDepartment().getDepartmentId() == 0) {
            pstmt.setNull(10, Types.INTEGER);
        } else {
            pstmt.setInt(10, employee.getDepartment().getDepartmentId());
        }
    }

}
