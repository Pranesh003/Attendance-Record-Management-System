# 📌 Attendance Record Management System

A full-stack web-based Attendance Management System built using **JSP, Servlets, JDBC, and Oracle 11g XE**, deployed on **Apache Tomcat 10**.

This project demonstrates a clean layered architecture including DAO, Service, and Controller layers with server-side validation and dynamic record generation.

---

## 🚀 Features

- Add Attendance Record
- View Attendance Record (by name & date)
- View All Attendance Records
- Server-side validations
- Unique Record ID generation
- Oracle sequence integration
- MVC architecture (Servlet as Controller)

---

## 🛠️ Technologies Used

- Java
- JSP
- Servlets (Jakarta)
- JDBC
- Oracle 11g XE
- Apache Tomcat 10
- Eclipse IDE
- Git & GitHub

---

## 🗂️ Project Architecture

```
AttendanceSystem
│
├── com.wipro.attendance.bean
├── com.wipro.attendance.dao
├── com.wipro.attendance.service
├── com.wipro.attendance.servlets
├── com.wipro.attendance.util
│
└── webapp
    ├── menu.html
    ├── addAttendance.jsp
    ├── viewAttendance.jsp
    ├── viewAllAttendance.jsp
    ├── displayAttendance.jsp
    ├── displayAllAttendance.jsp
    ├── success.html
    └── error.html
```

---

## 🗄️ Database Design

### Table: `ATTENDANCE_TB`

| Column           | Type         |
|------------------|-------------|
| RECORDID         | VARCHAR2(12) |
| PERSONNAME       | VARCHAR2(50) |
| ATTENDANCE_DATE  | DATE         |
| STATUS           | VARCHAR2(10) |
| REMARKS          | VARCHAR2(100)|

### Sequence: `ATTENDANCE_SEQ`

```sql
CREATE SEQUENCE ATTENDANCE_SEQ
START WITH 10
INCREMENT BY 1
MAXVALUE 99;
```

---

## 🔐 Record ID Format

Generated as:

```
YYYYMMDD + First 2 letters of name (uppercase) + Sequence
```

Example:

```
20260216PR11
```

---

# 📸 Application Screenshots

---

## 🏠 1. Main Menu



Main Menu<img width="1390" height="205" alt="Screenshot 2026-02-15 153258" src="https://github.com/user-attachments/assets/4b5c79a0-b812-4d15-a7d1-672ef5bc37f1" />

---

## ➕ 2. Add Attendance Form

User enters Name, Date, Status, and Remarks.

<img width="1390" height="248" alt="Screenshot 2026-02-15 153316" src="https://github.com/user-attachments/assets/32c7284c-1b9f-40db-aaa8-ca8e5a518766" />


---

## 📝 3. Filled Attendance Form

Form filled with sample data.

<img width="1387" height="222" alt="Screenshot 2026-02-15 153353" src="https://github.com/user-attachments/assets/d46c6b99-8666-443a-a7cf-18db2475356a" />

---

## ✅ 4. Successful Record Addition

Displayed after successful insertion.

<img width="1386" height="175" alt="Screenshot 2026-02-15 153405" src="https://github.com/user-attachments/assets/aa0c0db5-1e5e-4d53-8a51-822328ab6d19" />

---

## 🔍 5. View Attendance Form

Search attendance using Name and Date.

<img width="1387" height="214" alt="Screenshot 2026-02-15 153422" src="https://github.com/user-attachments/assets/88626054-88b8-4ece-a98f-40b0e9e4cc4d" />

---

## 📄 6. View Attendance Result

Displays matching attendance record.

<img width="1382" height="244" alt="Screenshot 2026-02-15 153432" src="https://github.com/user-attachments/assets/1379a0a7-2d90-4498-9dc6-c1985c1582b0" />

---

## 📊 7. View All Attendance Records

Displays all records in tabular format.

<img width="1386" height="263" alt="Screenshot 2026-02-15 153451" src="https://github.com/user-attachments/assets/1b2206a9-94b7-436a-81a1-e21fe4d06405" />

---

# 🧠 Validations Implemented

✔ Null input validation  
✔ Name length must be ≥ 2  
✔ Status must be "Present" or "Absent"  
✔ Prevent duplicate attendance entries  
✔ Proper exception handling  

---

# ▶️ How to Run the Project

1. Install Oracle 11g XE
2. Create table and sequence
3. Add `ojdbc8.jar` to project
4. Configure Tomcat 10 in Eclipse
5. Run project on server
6. Open:

```
http://localhost:8080/AttendanceSystem/menu.html
```

---

# 🧩 Key Learnings

- JDBC connection handling
- DAO pattern implementation
- Service layer validation
- Servlet request forwarding
- JSP dynamic rendering
- Oracle sequence integration
- Git version control

---

# 📈 Future Improvements

- Login authentication
- UI enhancement using Bootstrap
- Search by date range
- Export to CSV
- Role-based access control

---

# 👨‍💻 Author

Developed as a full-stack JSP + Servlet + JDBC project demonstrating enterprise-style layered architecture.

---

⭐ If you like this project, consider giving it a star!
