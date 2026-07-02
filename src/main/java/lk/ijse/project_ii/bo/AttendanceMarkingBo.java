/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.ijse.project_ii.bo;

import java.sql.SQLException;
import java.util.List;
import lk.ijse.project_ii.dto.AttendanceMarkingDTO;

/**
 *
 * @author Sineth
 */
public interface AttendanceMarkingBo {
    List<Integer> loadAvailableSchedules() throws SQLException;
    List<AttendanceMarkingDTO> generateAttendanceSheet(int scheduleId) throws SQLException;
    boolean saveAttendance(List<AttendanceMarkingDTO> items) throws SQLException;
}
