/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.ijse.project_ii.dao;

import java.sql.SQLException;
import java.util.List;
import lk.ijse.project_ii.dto.AttendanceMarkingDTO;

/**
 *
 * @author Sineth
 */
public interface AttendanceMarkingDAO {
    List<Integer> getAllSchedulingIds() throws SQLException;
    List<AttendanceMarkingDTO> getStudentsBySchedule(int selectedSchedule) throws SQLException;
    boolean saveAttendanceBatch(List<AttendanceMarkingDTO> items) throws SQLException;
}
