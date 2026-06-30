/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.project_ii.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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

        String sql = "INSERT INTO course_management(course_id, course_name, duration) VALUES (?, ?, ?)";

        try (PreparedStatement prepare = connect.prepareStatement(sql)) {

            prepare.setInt(1, course.getCourse_id());
            prepare.setString(2, course.getCourse_name());
            prepare.setString(3, course.getCourse_duration());

            return prepare.executeUpdate() > 0;
    }
}

    @Override
    public boolean update(CourseManagementEntity entity) throws Exception {
        Connection connect = DBConnection.getInstance().getConnection();

        String sql ="UPDATE course_management SET course_name=?, duration=? WHERE course_id=?";

        PreparedStatement stm = connect.prepareStatement(sql);

        stm.setString(1, entity.getCourse_name());
        stm.setString(2, entity.getCourse_duration());
        stm.setInt(3, entity.getCourse_id());

        return stm.executeUpdate() > 0;
}

    @Override
    public boolean delete(Integer id) throws Exception {
         Connection connect = DBConnection.getInstance().getConnection();
         String sql = "DELETE FROM course_management WHERE course_id =?";
                
                PreparedStatement stm = connect.prepareStatement(sql);
                
                stm.setInt(1, id);
                
                int result = stm.executeUpdate();
                return result>0;
               
    }

    @Override
    public CourseManagementEntity findById(Integer id) throws Exception {
       return null;
    }

    @Override
    public List<CourseManagementEntity> findAll() throws Exception {
       Connection conn = DBConnection.getInstance().getConnection();

        String sql = "SELECT * FROM course_management";
        PreparedStatement stm = conn.prepareStatement(sql);

        ResultSet rs = stm.executeQuery();
           List<CourseManagementEntity> list = new ArrayList<>();
           
                while (rs.next()) {
            list.add(new CourseManagementEntity(rs.getInt("course_id"), rs.getString("course_name"), rs.getString("duration")));
        }
        return list;
    }
    
}
