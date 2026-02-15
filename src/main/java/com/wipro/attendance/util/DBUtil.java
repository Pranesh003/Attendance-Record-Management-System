package com.wipro.attendance.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    public static Connection getDBConnection() {

        try {
            Class.forName("oracle.jdbc.OracleDriver");

            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe",
                    "system",
                    "84380");

            System.out.println("DATABASE CONNECTED SUCCESSFULLY");
            return con;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
