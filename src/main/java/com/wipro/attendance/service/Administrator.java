package com.wipro.attendance.service;

import java.util.*;

import com.wipro.attendance.bean.AttendanceBean;
import com.wipro.attendance.dao.AttendanceDAO;
import com.wipro.attendance.util.InvalidInputException;

public class Administrator {

    AttendanceDAO dao = new AttendanceDAO();

    public String addRecord(AttendanceBean bean) {

        try {

            if (bean == null || bean.getPersonName() == null ||
                bean.getStatus() == null || bean.getAttendanceDate() == null)
                throw new InvalidInputException();

            if (bean.getPersonName().length() < 2)
                return "INVALID NAME";

            if (!(bean.getStatus().equals("Present") ||
                  bean.getStatus().equals("Absent")))
                return "INVALID STATUS";

            if (dao.recordExists(bean.getPersonName(), bean.getAttendanceDate()))
                return "ALREADY EXISTS";

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
