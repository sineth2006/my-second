/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.project_ii.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.project_ii.dao.AttendanceMarkingDAO;
import lk.ijse.project_ii.db.DBConnection;
import lk.ijse.project_ii.db.alertMessage.AlertMessege;
import lk.ijse.project_ii.dto.AttendanceMarkingDTO;

/**
 *
 * @author Sineth
 */
public class AttendanceMarkingDAOImpl implements AttendanceMarkingDAO {
     private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private AlertMessege alert = new AlertMessege();
    
    @Override
    public List<Integer> getAllSchedulingIds() throws SQLException {
         List<Integer> ids = new ArrayList<>();
        String sql = "SELECT scheduling_id FROM class_scheduling";
         connect = DBConnection.getInstance().getConnection();
            ////////////////////////////////
            PreparedStatement pst = connect.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
               ids.add(rs.getInt("scheduling_id"));
            }
            return ids;
    }

    @Override
    public List<AttendanceMarkingDTO> getStudentsBySchedule(int selectedSchedule) throws SQLException {
        List<AttendanceMarkingDTO> list = new ArrayList<>();
         connect = DBConnection.getInstance().getConnection();
          String sql =  "SELECT sm.student_id, sm.student_name "
                   + "FROM student_management sm "
                   + "JOIN class_scheduling cs ON sm.course_id = cs.course_id "
                   + "WHERE cs.scheduling_id = ?";
                       
            prepare = connect.prepareStatement(sql);
            prepare.setInt(1, selectedSchedule); 
            
            result = prepare.executeQuery();
            int count = 0;

            while (result.next()) {
                 count++;
            list.add(new AttendanceMarkingDTO (
            count,
            selectedSchedule,
            result.getInt("student_id"),
                        "Absent"
            
            ));
            }
            return list;
    }

    @Override
    public boolean saveAttendanceBatch(List<AttendanceMarkingDTO> items) throws SQLException {
         connect = DBConnection.getInstance().getConnection();
         
         String sql = "INSERT INTO student_attendance (scheduling_id, student_id, status) VALUES (?, ?, ?)";
        PreparedStatement pst = connect.prepareStatement(sql);
         for (AttendanceMarkingDTO row : items) {
                pst.setInt(1, row.getSchedulingId());
                pst.setInt(2, row.getStudentId());
                pst.setString(3, row.getStatus());
                pst.addBatch();
            }
            
            int[] results = pst.executeBatch();
            return results.length == items.size();
        }
    
    
}
