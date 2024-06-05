package org.example.l7_20211049.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.l7_20211049.beans.Job;
import org.example.l7_20211049.dao.DaoJobs;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "JobServlet", value = "/JobServlet")
public class JobServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action;
        if (request.getParameter("action") == null) {
            action = "lista";
        } else {
            action = request.getParameter("action");
        }

        DaoJobs jobDao = new DaoJobs();

        if (action.equalsIgnoreCase("crear")) {
            String jobId = request.getParameter("jobId");
            String jobTitle = request.getParameter("jobTitle");
            int minSalary = Integer.parseInt(request.getParameter("minSalary"));
            int maxSalary = Integer.parseInt(request.getParameter("maxSalary"));

            Job trabajo = jobDao.getJobById(jobId);
            if (trabajo == null) {
                jobDao.addJob(jobId, jobTitle, minSalary, maxSalary);
            } else {
                jobDao.updateJob(jobId, jobTitle, minSalary, maxSalary);
            }
        }
        response.sendRedirect(request.getContextPath() + "/JobServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action;
        if (request.getParameter("action") == null) {
            action = "lista";
        } else {
            action = request.getParameter("action");
        }

        DaoJobs jobDao = new DaoJobs();
        RequestDispatcher view;

        switch (action) {
            case "lista":
                ArrayList<Job> listaTrabajos = jobDao.obtenerListaTrabajos();
                request.setAttribute("lista", listaTrabajos);
                view = request.getRequestDispatcher("jobs/listaTrabajos.jsp");
                view.forward(request, response);
                break;
            case "crear":
                view = request.getRequestDispatcher("jobs/nuevoTrabajo.jsp");
                view.forward(request, response);
                break;
            case "editar":
                String jobId = request.getParameter("id");
                Job trabajo = jobDao.getJobById(jobId);
                if (trabajo == null) {
                    response.sendRedirect(request.getContextPath() + "/JobServlet");
                } else {
                    request.setAttribute("trabajo", trabajo);
                    view = request.getRequestDispatcher("jobs/editarTrabajo.jsp");
                    view.forward(request, response);
                }
                break;
            case "borrar":
                String jobID = request.getParameter("id");
                if (jobDao.getJobById(jobID) != null) {
                    jobDao.deleteJob(jobID);
                }
                response.sendRedirect(request.getContextPath() + "/JobServlet");
                break;
        }
    }
}
