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
import lk.ijse.project_ii.dao.StudentDAO;
import lk.ijse.project_ii.db.DBConnection;
import lk.ijse.project_ii.db.alertMessage.AlertMessege;
import lk.ijse.project_ii.entity.StudentManagementEntity;

/**
 *
 * @author Sineth
 */
public class StudentDAOImpl implements StudentDAO{

    
   private Connection connect;
   private PreparedStatement prepare;
   private ResultSet result;
   private AlertMessege alert=new AlertMessege();
   
    @Override
    public boolean checkStudentExists(int id) throws SQLException {
          String checkstudentid="SELECT * FROM student_management WHERE student_id = ?";
           connect = DBConnection.getInstance().getConnection();
              prepare=connect.prepareStatement(checkstudentid);
              prepare.setInt(1, id);
              result=prepare.executeQuery();
               return result.next();
    }

    @Override
    public boolean insertStudent(StudentManagementEntity entity) throws SQLException {
          String insertdata="INSERT INTO student_management(student_id,student_name,telephone_number,email,course_id)VALUES(?,?,?,?,?)";
               connect = DBConnection.getInstance().getConnection();
                prepare=connect.prepareStatement(insertdata);
                prepare.setInt(1, entity.getStudent_id());
                prepare.setString(2, entity.getStudent_name());
                prepare.setString(3, entity.getTelephone_number());
                prepare.setString(4, entity.getEmail());
                prepare.setInt(5, entity.getCourse_id());

                return prepare.executeUpdate() > 0;  
    }

    @Override
    public boolean updateStudent(StudentManagementEntity entity) throws SQLException {
         String sql = "UPDATE student_management SET student_id=?, student_name=?, telephone_number=?, email=? ,course_id=? WHERE student_id =?";
        connect = DBConnection.getInstance().getConnection();
    
        PreparedStatement stm = connect.prepareStatement(sql);
       
        stm.setInt(1, entity.getStudent_id());
        stm.setString(2, entity.getStudent_name());
        stm.setString(3, entity.getTelephone_number());
        stm.setString(4, entity.getEmail());
       
        stm.setInt(5, entity.getCourse_id());
         stm.setInt(6, entity.getStudent_id());
         
       return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteStudent(int id) throws SQLException {
         connect = DBConnection.getInstance().getConnection();
         String sql = "DELETE FROM student_management WHERE student_id =?";
                
                PreparedStatement stm = connect.prepareStatement(sql);
                
                stm.setInt(1, id);
                return stm.executeUpdate()>0;
    }

    @Override
    public int getCourseIdByName(String name) throws SQLException {
     connect = DBConnection.getInstance().getConnection();
    String checkid="SELECT course_id FROM course_management WHERE course_name = ?";
     PreparedStatement pst = connect.prepareStatement(checkid);
              pst.setString(1, name);
              result=pst.executeQuery();
               if(result.next()){
                   //return this course_id instead of check_id because when check database table he cant finfd name check_id in table that is why i put column name in that return type
                 return result.getInt("course_id");
               }
               return -1;
    }
    @Override
    public ArrayList<String> getAllCourseNames() throws SQLException {
       connect = DBConnection.getInstance().getConnection();
        String sql = "SELECT course_name FROM course_management";
        PreparedStatement pst = connect.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();
        ArrayList<String> names = new ArrayList<>();
        while (rst.next()) {
            names.add(rst.getString("course_name"));
        }
        return names;
    }
      @Override
    public ArrayList<StudentManagementEntity> findAll() throws Exception { // 🚨 Must match 'throws Exception'
        connect = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM student_management";
        PreparedStatement pst = connect.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();
        
        ArrayList<StudentManagementEntity> list = new ArrayList<>(); 
         
        while (rst.next()) {
            list.add(new StudentManagementEntity(
                rst.getInt("student_id"),
                rst.getString("student_name"), 
                rst.getString("telephone_number"), 
                rst.getString("email"),
                rst.getInt("course_id")
            ));
        }
        return list;
    }
    @Override
public String getCourseNameById(int selectedCourseId) throws Exception {
    connect = DBConnection.getInstance().getConnection();
    String checkname = "SELECT course_name FROM course_management WHERE course_id = ?";
    PreparedStatement pst = connect.prepareStatement(checkname);
    pst.setInt(1, selectedCourseId);
    ResultSet rst = pst.executeQuery();
    if (rst.next()) {
        return rst.getString("course_name");
    }
    return null;
}

    
}
