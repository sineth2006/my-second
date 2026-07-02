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
import lk.ijse.project_ii.dao.LectureDAO;
import lk.ijse.project_ii.db.DBConnection;
import lk.ijse.project_ii.db.alertMessage.AlertMessege;
import lk.ijse.project_ii.entity.LectureManagementEntity;

/**
 *
 * @author Sineth
 */
public class LectureDAOImpl implements LectureDAO {
   private Connection connect;
   private PreparedStatement prepare;
   private ResultSet result;
   private AlertMessege alert=new AlertMessege();
    @Override
    public boolean save(LectureManagementEntity entity) throws SQLException, ClassNotFoundException {
         connect = DBConnection.getInstance().getConnection();
          String insertdata="INSERT INTO lecture_management(lecture_id,lecture_name,email,telephone_number,subject_id,user_id)VALUES(?,?,?,?,?,?)";
              
                prepare=connect.prepareStatement(insertdata);
                prepare.setInt(1, entity.getLecture_id());
                prepare.setString(2, entity.getLecture_name());
                prepare.setString(3,entity.getEmail());
                prepare.setString(4,entity.getTelephone_number()); 
                prepare.setInt(5,entity.getSubject_id());
                prepare.setInt(6,entity.getUser_id());
                  return prepare.executeUpdate()>0;
           
    }

    @Override
    public boolean update(LectureManagementEntity entity) throws SQLException, ClassNotFoundException {
        connect = DBConnection.getInstance().getConnection();

        String sql = "UPDATE lecture_management SET lecture_name=?, email=?, telephone_number=?, subject_id=?, user_id=? WHERE lecture_id=?";

        PreparedStatement stm = connect.prepareStatement(sql);

        stm.setString(1, entity.getLecture_name());
        stm.setString(2, entity.getEmail());
        stm.setString(3, entity.getTelephone_number());
        stm.setInt(4, entity.getSubject_id());
        stm.setInt(5, entity.getUser_id());
        stm.setInt(6, entity.getLecture_id());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
         connect = DBConnection.getInstance().getConnection();
          String sql = "DELETE FROM lecture_management WHERE lecture_id =?";
                
                PreparedStatement stm = connect.prepareStatement(sql);
                
                stm.setInt(1, id);
                
                return stm.executeUpdate()>0;
    }

    @Override
    public boolean exist(int id) throws SQLException, ClassNotFoundException {
          Connection connect = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM lecture_management WHERE lecture_id = ?";
        PreparedStatement prepare = connect.prepareStatement(sql);
        prepare.setInt(1, id);
        ResultSet result = prepare.executeQuery();
        return result.next();
    }

    @Override
    public int getUserIdByName(String name) throws SQLException, ClassNotFoundException {
       connect = DBConnection.getInstance().getConnection();
        String checkid="SELECT  user_id FROM users WHERE user_name = ?";
        PreparedStatement pst = connect.prepareStatement(checkid);
              pst.setString(1, name);
              result=pst.executeQuery();
               if(result.next()){
                   //return this course_id instead of check_id because when check database table he cant finfd name check_id in table that is why i put column name in that return type
                 return result.getInt("user_id");
               }
               return -1;
    }

    @Override
    public int getSubjectIdByName(String name) throws SQLException, ClassNotFoundException {
       connect = DBConnection.getInstance().getConnection();
        String checkid="SELECT  subject_id FROM subjects WHERE subjects_name = ?";
         PreparedStatement pst = connect.prepareStatement(checkid);
              pst.setString(1, name);
              result=pst.executeQuery();
               if(result.next()){
                   //return this course_id instead of check_id because when check database table he cant finfd name check_id in table that is why i put column name in that return type
                 return result.getInt("subject_id");
    }
    return -1;
}

    @Override
    public String getUserNameById(int id) throws Exception {
        connect = DBConnection.getInstance().getConnection();
        String checkname="SELECT user_name FROM users WHERE user_id = ?";
          PreparedStatement pst = connect.prepareStatement(checkname);
              pst.setInt(1, id);
              result=pst.executeQuery();
               if(result.next()){
                   //return this course_id instead of check_id because when check database table he cant finfd name check_id in table that is why i put column name in that return type
                 return result.getString("user_name");
               }
               return null;
    }

    @Override
    public String getsubjectNameById(int id) throws Exception {
            connect = DBConnection.getInstance().getConnection();
        String sql = "SELECT subjects_name FROM subjects WHERE subject_id = ?";
        PreparedStatement pst = connect.prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rst = pst.executeQuery();
        if (rst.next()) {
            return rst.getString("subjects_name");
        }
        return null;  
    }

    @Override
    public ArrayList<String> getAllUserNamesByPosition(int positionId) throws SQLException, ClassNotFoundException {
    connect = DBConnection.getInstance().getConnection();

    String sql =
            "SELECT user_name FROM users WHERE position_id = ?";

    PreparedStatement pst = connect.prepareStatement(sql);
    pst.setInt(1, positionId);

    ResultSet rs = pst.executeQuery();

    ArrayList<String> usernames = new ArrayList<>();

    while (rs.next()) {
        usernames.add(rs.getString("user_name"));
    }

    return usernames;
    }

    @Override
    public ArrayList<String> getAllSubjectNames() throws SQLException, ClassNotFoundException {
       connect = DBConnection.getInstance().getConnection();
        
        String sql="SELECT subjects_name FROM subjects";
            PreparedStatement pst = connect.prepareStatement(sql);
             ResultSet rs = pst.executeQuery();
        ArrayList<String> subjects = new ArrayList<>();
        while (rs.next()) {
            subjects.add(rs.getString("subjects_name"));
        }
        return subjects;

    
}
    @Override
    public ArrayList<LectureManagementEntity> getAllLectures() throws SQLException, ClassNotFoundException {
      Connection conn = DBConnection.getInstance().getConnection();

        String sql = "SELECT * FROM lecture_management";
        PreparedStatement stm = conn.prepareStatement(sql);

        ResultSet rs = stm.executeQuery();
         ArrayList<LectureManagementEntity> lecturelist = new ArrayList<>();
         while(rs.next()){
       
        lecturelist.add(new LectureManagementEntity(
                    rs.getInt("lecture_id"),
                    rs.getString("lecture_name"),
                     rs.getString("email"),
                    rs.getString("telephone_number"),
                     rs.getInt("subject_id"),
                    rs.getInt("user_id")
        ));
        
         }
          return lecturelist;
    }
}