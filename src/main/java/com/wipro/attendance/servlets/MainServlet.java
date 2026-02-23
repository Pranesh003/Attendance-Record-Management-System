package com.wipro.attendance.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.wipro.attendance.bean.AttendanceBean;
import com.wipro.attendance.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final Administrator admin = new Administrator();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");
        if (operation == null || operation.isBlank()) {
            response.sendRedirect("error.html");
            return;
        }

        try {

            if (operation.equals("newRecord")) {

                String name = request.getParameter("personName");
                String dateStr = request.getParameter("attendanceDate");
                String status = request.getParameter("status");
                String remarks = request.getParameter("remarks");

                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);

                AttendanceBean bean = new AttendanceBean();
                bean.setPersonName(name);
                bean.setAttendanceDate(date);
                bean.setStatus(status);
                bean.setRemarks(remarks);

                String result = admin.addRecord(bean);

                if (isFailureResult(result)) {
                    response.sendRedirect("error.html");
                } else {
                    response.sendRedirect("success.html");
                }
            }

            else if (operation.equals("viewRecord")) {

                String name = request.getParameter("personName");
                Date date = new SimpleDateFormat("yyyy-MM-dd")
                        .parse(request.getParameter("attendanceDate"));

                AttendanceBean bean = admin.viewRecord(name, date);

                request.setAttribute("record", bean);
                RequestDispatcher rd = request.getRequestDispatcher("displayAttendance.jsp");
                rd.forward(request, response);
            }

            else if (operation.equals("viewAllRecords")) {

                List<AttendanceBean> list = admin.viewAllRecords();

                request.setAttribute("records", list);
                RequestDispatcher rd = request.getRequestDispatcher("displayAllAttendance.jsp");
                rd.forward(request, response);
            } else {
                response.sendRedirect("error.html");
            }

        } catch (Exception e) {
            response.sendRedirect("error.html");
        }
    }

    private boolean isFailureResult(String result) {
        return result == null
                || result.isBlank()
                || result.equals("FAIL")
                || result.equals("INVALID INPUT")
                || result.equals("INVALID NAME")
                || result.equals("INVALID STATUS")
                || result.equals("ALREADY EXISTS");
    }
}
