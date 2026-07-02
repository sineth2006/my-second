/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.project_ii.bo.impl;

import java.sql.SQLException;
import java.util.List;
import lk.ijse.project_ii.bo.AttendanceMarkingBo;
import lk.ijse.project_ii.dao.AttendanceMarkingDAO;
import lk.ijse.project_ii.dao.impl.AttendanceMarkingDAOImpl;
import lk.ijse.project_ii.dto.AttendanceMarkingDTO;

/**
 *
 * @author Sineth
 */
public class AttendanceMarkingBoImpl implements AttendanceMarkingBo {
  private final AttendanceMarkingDAO attendanceDAO = new AttendanceMarkingDAOImpl();
    @Override
    public List<Integer> loadAvailableSchedules() throws SQLException {
        return attendanceDAO.getAllSchedulingIds();
    }

    @Override
    public List<AttendanceMarkingDTO> generateAttendanceSheet(int scheduleId) throws SQLException {
      return attendanceDAO.getStudentsBySchedule(scheduleId);
    }

    @Override
    public boolean saveAttendance(List<AttendanceMarkingDTO> items) throws SQLException {
         return attendanceDAO.saveAttendanceBatch(items);
    }
    
}
