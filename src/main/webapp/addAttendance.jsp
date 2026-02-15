<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="MainServlet" method="post">
<input type="hidden" name="operation" value="newRecord">

Name: <input type="text" name="personName"><br>
Date: <input type="date" name="attendanceDate"><br>
Status:
<select name="status">
<option value="Present">Present</option>
<option value="Absent">Absent</option>
</select><br>
Remarks: <input type="text" name="remarks"><br>

<input type="submit" value="Add">
</form>

</body>
</html>