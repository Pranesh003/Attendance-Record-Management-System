package com.wipro.attendance.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static final String DB_URL = System.getenv().getOrDefault("ATTENDANCE_DB_URL", "jdbc:oracle:thin:@localhost:1521:xe");
    private static final String DB_USER = System.getenv().getOrDefault("ATTENDANCE_DB_USER", "system");
    private static final String DB_PASSWORD = System.getenv().getOrDefault("ATTENDANCE_DB_PASSWORD", "84380");

    public static Connection getDBConnection() {

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            System.out.println("DATABASE CONNECTED SUCCESSFULLY");
            return con;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
