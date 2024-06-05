package org.example.l7_20211049.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DaoBase {
    // Método para obtener la conexión a la base de datos
    public Connection getConnection() throws SQLException {
        try {
            // Cargar el driver JDBC de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Establecer la conexión a la base de datos
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/hr", "root", "root");
    }
}
