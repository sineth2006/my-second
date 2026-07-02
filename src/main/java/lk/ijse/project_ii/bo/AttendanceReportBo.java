/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.ijse.project_ii.bo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import lk.ijse.project_ii.dto.AttendancerReportDTO;

/**
 *
 * @author Sineth
 */
public interface AttendanceReportBo {
     List<String> loadSubjects() throws SQLException;
    List<String> loadStudents() throws SQLException;
    List<AttendancerReportDTO> generateReport(String studentName, String subjectName, LocalDate from, LocalDate to) throws SQLException;
}
