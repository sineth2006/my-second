/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.project_ii.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.project_ii.dao.AttendanceReportDAO;
import lk.ijse.project_ii.db.DBConnection;
import lk.ijse.project_ii.db.alertMessage.AlertMessege;
import lk.ijse.project_ii.dto.AttendancerReportDTO;

/**
 *
 * @author Sineth
 */
public class AttendanceReportDAOImpl implements AttendanceReportDAO{

    @Override
    public List<String> getAllSubjectNames() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT subjects_name FROM subjects";
        
        // FIX: Extract shared connection instance without closing it
        Connection con = DBConnection.getInstance().getConnection();
        
        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString("subjects_name"));
            }
        }
        return list;
    }

    @Override
    public List<String> getAllStudentNames() throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT student_name FROM student_management";
        
        // Extract shared connection instance without closing it
        Connection con = DBConnection.getInstance().getConnection();
        
        try (PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString("student_name"));
            }
        }
        return list;
    }

    @Override
    public int getSubjectIdByName(String name) throws SQLException {
        String sql = "SELECT subject_id FROM subjects WHERE subjects_name = ?";
        
        // FIX: Extract shared connection instance without closing it
        Connection con = DBConnection.getInstance().getConnection();
        
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, name);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) return rs.getInt("subject_id");
            }
        }
        return -1;
    }

    @Override
    public int getStudentIdByName(String name) throws SQLException {
        String sql = "SELECT student_id FROM student_management WHERE student_name = ?";
        
        // FIX: Extract shared connection instance without closing it
        Connection con = DBConnection.getInstance().getConnection();
        
        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, name);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) return rs.getInt("student_id");
            }
        }
        return -1;
    }

    @Override
    public List<AttendancerReportDTO> getAttendanceReport(int studentId, int subjectId, LocalDate from, LocalDate to) throws SQLException {
        List<AttendancerReportDTO> list = new ArrayList<>();
        String sql = "SELECT a.student_id, s.student_name, sub.subjects_name, a.status, a.attendance_date " +
                     "FROM student_attendance a " +
                     "JOIN student_management s ON a.student_id = s.student_id " +
                     "JOIN class_scheduling sh ON a.scheduling_id = sh.scheduling_id " +
                     "JOIN subjects sub ON sh.subject_id = sub.subject_id " +
                     "WHERE a.student_id = ? AND sub.subject_id = ? AND a.attendance_date BETWEEN ? AND ? " +
                     "GROUP BY a.student_id, a.attendance_date, a.status";

        // FIX: Extract shared connection instance without closing it
        Connection con = DBConnection.getInstance().getConnection();

        try (PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, studentId);
            pst.setInt(2, subjectId);
            pst.setDate(3, Date.valueOf(from));
            pst.setDate(4, Date.valueOf(to));

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    list.add(new AttendancerReportDTO(
                        rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getString("subjects_name"),
                        rs.getString("attendance_date"),
                        rs.getString("status")
                    ));
                }
            }
        }
        return list;
    }
}
