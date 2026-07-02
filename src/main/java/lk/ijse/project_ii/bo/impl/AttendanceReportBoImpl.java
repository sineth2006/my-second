/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.project_ii.bo.impl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.project_ii.bo.AttendanceReportBo;
import lk.ijse.project_ii.dao.AttendanceReportDAO;
import lk.ijse.project_ii.dao.impl.AttendanceReportDAOImpl;
import lk.ijse.project_ii.dto.AttendancerReportDTO;

/**
 *
 * @author Sineth
 */
public class AttendanceReportBoImpl implements AttendanceReportBo  {
 private final AttendanceReportDAO reportDAO = new AttendanceReportDAOImpl();
    @Override
    public List<String> loadSubjects() throws SQLException {
         return reportDAO.getAllSubjectNames();
    }

    @Override
    public List<String> loadStudents() throws SQLException {
     return reportDAO.getAllStudentNames();
    }

    @Override
    public List<AttendancerReportDTO> generateReport(String studentName, String subjectName, LocalDate from, LocalDate to) throws SQLException {
        int studentId = reportDAO.getStudentIdByName(studentName);
        int subjectId = reportDAO.getSubjectIdByName(subjectName);

        if (studentId == -1 || subjectId == -1) {
            return new ArrayList<>(); // Empty fallback list if references fail
        }
        return reportDAO.getAttendanceReport(studentId, subjectId, from, to);
    }
    
    
}
