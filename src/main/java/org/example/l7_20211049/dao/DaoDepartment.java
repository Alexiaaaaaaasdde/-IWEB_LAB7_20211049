package org.example.l7_20211049.dao;
import org.example.l7_20211049.beans.Departments;
import org.example.l7_20211049.beans.Employee;
import org.example.l7_20211049.beans.Location;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoDepartment extends DaoBase {

    public List<Departments> lista() {

        List<Departments> list = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from departments d left join employees m on d.manager_id = m.employee_id left join locations l on d.location_id = l.location_id order by d.department_id")) {

            while (rs.next()) {
                Departments department = new Departments();
                department.setDepartmentId(rs.getInt(1));
                department.setDepartmentName(rs.getString(2));
                Employee manager = null;
                if(rs.getInt("m.employee_id") != 0){
                    manager = new Employee();
                    manager.setEmployeeId(rs.getInt("m.employee_id"));
                    manager.setFirstName(rs.getString("m.first_name"));
                    manager.setLastName(rs.getString("m.last_name"));
                    department.setManager(manager);
                }
                Location location = null;
                if(rs.getInt("l.location_id") != 0){
                    location = new Location();
                    location.setLocationId(rs.getInt("l.location_id"));
                    location.setCity(rs.getString("l.city"));
                    department.setLocation(location);
                }
                list.add(department);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return list;
    }
}