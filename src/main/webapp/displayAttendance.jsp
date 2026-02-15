<%@ page import="com.wipro.attendance.bean.AttendanceBean" %>

<%
AttendanceBean bean = (AttendanceBean) request.getAttribute("record");

if (bean == null) {
%>
    <h3>No matching records exists! Please try again!</h3>
<%
} else {
%>
    <h3>Attendance Record</h3>
    Record ID: <%= bean.getRecordId() %> <br>
    Name: <%= bean.getPersonName() %> <br>
    Date: <%= bean.getAttendanceDate() %> <br>
    Status: <%= bean.getStatus() %> <br>
    Remarks: <%= bean.getRemarks() %> <br>
<%
}
%>
