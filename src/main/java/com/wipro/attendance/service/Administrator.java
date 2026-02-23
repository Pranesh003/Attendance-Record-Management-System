package com.wipro.attendance.service;

import java.util.Date;
import java.util.List;

import com.wipro.attendance.bean.AttendanceBean;
import com.wipro.attendance.dao.AttendanceDAO;
import com.wipro.attendance.util.InvalidInputException;

public class Administrator {

    private final AttendanceDAO dao = new AttendanceDAO();

    public String addRecord(AttendanceBean bean) {

        try {

            if (bean == null || bean.getPersonName() == null || bean.getStatus() == null
                    || bean.getAttendanceDate() == null) {
                throw new InvalidInputException();
            }

            String personName = bean.getPersonName().trim();
            String status = bean.getStatus().trim();

            if (personName.length() < 2) {
                return "INVALID NAME";
            }

            if (!(status.equalsIgnoreCase("Present") || status.equalsIgnoreCase("Absent"))) {
                return "INVALID STATUS";
            }

            bean.setPersonName(personName);
            bean.setStatus(status.substring(0, 1).toUpperCase() + status.substring(1).toLowerCase());

            if (dao.recordExists(bean.getPersonName(), bean.getAttendanceDate())) {
                return "ALREADY EXISTS";
            }

            String id = dao.generateRecordID(bean.getPersonName(), bean.getAttendanceDate());
            bean.setRecordId(id);

            return dao.createRecord(bean);

        } catch (InvalidInputException e) {
            return "INVALID INPUT";
        }
    }

    public AttendanceBean viewRecord(String name, Date date) {
        return dao.fetchRecord(name, date);
    }

    public List<AttendanceBean> viewAllRecords() {
        return dao.fetchAllRecords();
    }
}
