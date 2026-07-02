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
import lk.ijse.project_ii.dao.SubjectDAO;
import lk.ijse.project_ii.db.DBConnection;
import lk.ijse.project_ii.db.alertMessage.AlertMessege;
import lk.ijse.project_ii.entity.SubjectManagementEntity;

/**
 *
 * @author Sineth
 */
public class SubjectDAOImpl implements SubjectDAO {
      private Connection connect;
   private PreparedStatement prepare;
   private ResultSet result;
   private AlertMessege alert=new AlertMessege();

    @Override
    public boolean add(SubjectManagementEntity entity) throws SQLException {
          connect = DBConnection.getInstance().getConnection();
           String insertdata="INSERT INTO subjects(subject_id,subjects_name,course_id)VALUES(?,?,?)";
              
                prepare=connect.prepareStatement(insertdata);
                prepare.setInt(1, entity.getSubject_id());
                prepare.setString(2, entity.getSubject_name());
                  prepare.setInt(3,entity.getCourse_id());
                  return prepare.executeUpdate()>0;
         
    }

    @Override
    public boolean update(SubjectManagementEntity entity) throws SQLException {
        connect = DBConnection.getInstance().getConnection();
        String sql = "UPDATE subjects SET subject_id=?,subjects_name=? ,course_id=? WHERE subject_id =?";
        PreparedStatement stm = connect.prepareStatement(sql);
        
        stm.setInt(1, entity.getSubject_id());
        stm.setString(2,entity.getSubject_name());
         stm.setInt(3,entity.getCourse_id());
         stm.setInt(4,entity.getSubject_id());
         
          return stm.executeUpdate()>0;
    }

    @Override
    public boolean delete(int id) throws SQLException {
      connect = DBConnection.getInstance().getConnection();
         String sql = "DELETE FROM subjects WHERE subject_id =?";
                
                PreparedStatement stm = connect.prepareStatement(sql);
                
                stm.setInt(1, id);
                
                return stm.executeUpdate()>0;  
    }

    @Override
    public boolean exist(int id) throws SQLException {
         connect = DBConnection.getInstance().getConnection();
       String checksubjectid="SELECT * FROM subjects WHERE subject_id = ?";
        prepare=connect.prepareStatement(checksubjectid);
              prepare.setInt(1, id);
              result= prepare.executeQuery();
               return result.next();
          
    
    }
    @Override
    public ArrayList<SubjectManagementEntity> getAll() throws SQLException {
       connect = DBConnection.getInstance().getConnection();
        
        String sql = "SELECT * FROM subjects";
        PreparedStatement stm = connect.prepareStatement(sql);
           ArrayList<SubjectManagementEntity> subjecttList = new ArrayList<>();
        ResultSet rs = stm.executeQuery();
   while(rs.next()){
       
        subjecttList.add(new SubjectManagementEntity(
                    rs.getInt("subject_id"),
                    rs.getString("subjects_name"),
                    rs.getInt("course_id")
        ));
   }
      return subjecttList;
    }

    @Override
    public int getCourseIdByName(String courseName) throws SQLException {
          connect = DBConnection.getInstance().getConnection();
        String checkid="SELECT course_id FROM course_management WHERE course_name = ?";
     
      PreparedStatement pst = connect.prepareStatement(checkid);
              pst.setString(1, courseName);
              result=pst.executeQuery();
               if(result.next()){
                   //return this course_id instead of check_id because when check database table he cant finfd name check_id in table that is why i put column name in that return type
                 return result.getInt("course_id");
                 
    }
                return -1;
    }
    @Override
    public String getCourseNameById(int courseId) throws SQLException {
         Connection connect = DBConnection.getInstance().getConnection();
        String sql = "SELECT course_name FROM course_management WHERE course_id = ?";
        PreparedStatement pst = connect.prepareStatement(sql);
              pst.setInt(1, courseId);
              result=pst.executeQuery();
               if(result.next()){
                   //return this course_id instead of check_id because when check database table he cant finfd name check_id in table that is why i put column name in that return type
                 return result.getString("course_name");
               }
               return null;
    }

    @Override
    public ArrayList<String> getAllCourseNames() throws Exception {
          Connection connect = DBConnection.getInstance().getConnection();
        String sql = "SELECT course_name FROM course_management";
         ArrayList<String> courses = new ArrayList<>();
        try (PreparedStatement pstm = connect.prepareStatement(sql);
             ResultSet rst = pstm.executeQuery()) {
            while (rst.next()) {
                courses.add(rst.getString("course_name"));
            }
        }
        return courses;
    }
    
}
