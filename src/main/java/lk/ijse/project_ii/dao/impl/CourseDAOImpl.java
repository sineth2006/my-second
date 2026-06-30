/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.project_ii.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import lk.ijse.project_ii.dao.CourseDAO;
import lk.ijse.project_ii.db.DBConnection;
import lk.ijse.project_ii.entity.CourseManagementEntity;

/**
 *
 * @author Sineth
 */
public class CourseDAOImpl implements CourseDAO {

    @Override
    public boolean existByName(String name) throws Exception {
      Connection connect = DBConnection.getInstance().getConnection();
       String checkCourseName="SELECT * FROM course_management WHERE course_name = ?";
           try(
             PreparedStatement prepare=connect.prepareStatement(checkCourseName)){
              prepare.setString(1, name);
              try(ResultSet result=prepare.executeQuery()){
                  return result.next();
              }
           }
    }

    @Override
    public boolean save(CourseManagementEntity course) throws Exception {
         Connection connect = DBConnection.getInstance().getConnection();
         
         String insertdata="INSERT INTO course_management(course_id,course_name,duration)VALUES(?,?,?)";
              
             try(PreparedStatement prepare=connect.prepareStatement(insertdata)){
                prepare.setInt(1,course.getCourse_id());
                prepare.setString(2,course.getCourse_name());
                prepare.setString(3,course.getCourse_duration());
                 int result=prepare.executeUpdate();
                 return result>0;
             }
    
    }

    @Override
    public boolean update(CourseManagementEntity entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public CourseManagementEntity findById(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CourseManagementEntity> findAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
