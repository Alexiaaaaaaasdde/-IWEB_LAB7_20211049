package org.example.l7_20211049.dao;

import org.example.l7_20211049.beans.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoJobs extends DaoBase{

    // Método para listar todos los trabajos
    public ArrayList<Job> obtenerListaTrabajos() {
        ArrayList<Job> listaTrabajos = new ArrayList<>();
        String query = "SELECT * FROM jobs";

        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Job job = new Job();
                job.setJobId(rs.getString(1));
                job.setJobTitle(rs.getString(2));
                job.setMinSalary(rs.getInt(3));
                job.setMaxSalary(rs.getInt(4));

                listaTrabajos.add(job);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaTrabajos;

    }

    // Método para obtener un trabajo por su ID
    public Job getJobById(String jobId){
        Job job = null;
        String query = "SELECT * FROM jobs WHERE job_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, jobId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    job = new Job();
                    job.setJobId(rs.getString(1));
                    job.setJobTitle(rs.getString(2));
                    job.setMinSalary(rs.getInt(3));
                    job.setMaxSalary(rs.getInt(4));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return job;
    }

    // Método para añadir un nuevo trabajo
    public void addJob(String jobId, String jobTitle, int minSalary, int maxSalary) {
        String query = "INSERT INTO jobs (job_id,job_title,min_salary,max_salary) VALUES (?,?,?,?)";
        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, jobId);
            pstmt.setString(2, jobTitle);
            pstmt.setInt(3, minSalary);
            pstmt.setInt(4, maxSalary);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un trabajo existente
    public void updateJob(String jobId, String jobTitle, int minSalary, int maxSalary) {
        String query = "UPDATE jobs SET job_title = ?, min_salary = ?, max_salary = ? WHERE job_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, jobTitle);
            pstmt.setInt(2, minSalary);
            pstmt.setInt(3, maxSalary);
            pstmt.setString(4, jobId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para borrar un trabajo por su ID
    public void deleteJob(String jobId) {
        String query = "DELETE FROM jobs WHERE job_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, jobId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
