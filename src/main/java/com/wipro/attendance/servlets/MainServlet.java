package com.wipro.attendance.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.wipro.attendance.bean.AttendanceBean;
import com.wipro.attendance.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    Administrator admin = new Administrator();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

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

                if (result.equals("FAIL") || result.equals("INVALID INPUT"))
                    response.sendRedirect("error.html");
                else
                    response.sendRedirect("success.html");
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
            }

        } catch (Exception e) {
            response.sendRedirect("error.html");
        }
    }
}
