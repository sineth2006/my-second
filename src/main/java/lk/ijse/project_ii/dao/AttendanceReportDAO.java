/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.ijse.project_ii.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import lk.ijse.project_ii.dto.AttendancerReportDTO;

/**
 *
 * @author Sineth
 */
public interface AttendanceReportDAO  {
      List<String> getAllSubjectNames() throws SQLException;
    List<String> getAllStudentNames() throws SQLException;
    int getSubjectIdByName(String name) throws SQLException;
    int getStudentIdByName(String name) throws SQLException;
    List<AttendancerReportDTO> getAttendanceReport(int studentId, int subjectId, LocalDate from, LocalDate to) throws SQLException;
}
