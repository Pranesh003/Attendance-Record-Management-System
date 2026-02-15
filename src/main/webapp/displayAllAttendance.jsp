<%@ page import="java.util.List" %>
<%@ page import="com.wipro.attendance.bean.AttendanceBean" %>

<%
List<AttendanceBean> list = 
(List<AttendanceBean>) request.getAttribute("records");

if (list == null || list.isEmpty()) {
%>
    <h3>No records available!</h3>
<%
} else {
%>
    <h3>All Attendance Records</h3>
    <table border="1">
        <tr>
            <th>Record ID</th>
            <th>Name</th>
            <th>Date</th>
            <th>Status</th>
            <th>Remarks</th>
        </tr>

<%
    for (AttendanceBean bean : list) {
%>
        <tr>
            <td><%= bean.getRecordId() %></td>
            <td><%= bean.getPersonName() %></td>
            <td><%= bean.getAttendanceDate() %></td>
            <td><%= bean.getStatus() %></td>
            <td><%= bean.getRemarks() %></td>
        </tr>
<%
    }
%>
    </table>
<%
}
%>
