package com.wipro.attendance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wipro.attendance.bean.AttendanceBean;
import com.wipro.attendance.util.DBUtil;

public class AttendanceDAO {

    public String createRecord(AttendanceBean bean) {
        String result = "FAIL";
        String sql = "INSERT INTO ATTENDANCE_TB VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bean.getRecordId());
            ps.setString(2, bean.getPersonName());
            ps.setDate(3, new java.sql.Date(bean.getAttendanceDate().getTime()));
            ps.setString(4, bean.getStatus());
            ps.setString(5, bean.getRemarks());

            int row = ps.executeUpdate();
            if (row > 0) {
                result = bean.getRecordId();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public AttendanceBean fetchRecord(String personName, Date attendanceDate) {
        AttendanceBean bean = null;
        String sql = "SELECT RECORDID, PERSONNAME, ATTENDANCE_DATE, STATUS, REMARKS FROM ATTENDANCE_TB WHERE PERSONNAME=? AND ATTENDANCE_DATE=?";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, personName);
            ps.setDate(2, new java.sql.Date(attendanceDate.getTime()));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    bean = new AttendanceBean();
                    bean.setRecordId(rs.getString("RECORDID"));
                    bean.setPersonName(rs.getString("PERSONNAME"));
                    bean.setAttendanceDate(rs.getDate("ATTENDANCE_DATE"));
                    bean.setStatus(rs.getString("STATUS"));
                    bean.setRemarks(rs.getString("REMARKS"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public boolean recordExists(String personName, Date attendanceDate) {
        return fetchRecord(personName, attendanceDate) != null;
    }

    public List<AttendanceBean> fetchAllRecords() {
        List<AttendanceBean> list = new ArrayList<>();
        String sql = "SELECT RECORDID, PERSONNAME, ATTENDANCE_DATE, STATUS, REMARKS FROM ATTENDANCE_TB";

        try (Connection con = DBUtil.getDBConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                AttendanceBean bean = new AttendanceBean();
                bean.setRecordId(rs.getString("RECORDID"));
                bean.setPersonName(rs.getString("PERSONNAME"));
                bean.setAttendanceDate(rs.getDate("ATTENDANCE_DATE"));
                bean.setStatus(rs.getString("STATUS"));
                bean.setRemarks(rs.getString("REMARKS"));
                list.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public String generateRecordID(String personName, Date attendanceDate) {
        String id = "";

        try (Connection con = DBUtil.getDBConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT ATTENDANCE_SEQ.NEXTVAL FROM DUAL")) {

            rs.next();
            int seq = rs.getInt(1);

            SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
            String datePart = f.format(attendanceDate);

            id = datePart + personName.substring(0, 2).toUpperCase() + seq;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }
}
