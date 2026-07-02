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
import lk.ijse.project_ii.dao.ClassSchedulingDAO;
import lk.ijse.project_ii.db.DBConnection;
import lk.ijse.project_ii.entity.ClassShedulingEntity;

/**
 *
 * @author Sineth
 */
public class ClassSchedulingDAOImpl implements ClassSchedulingDAO {

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    @Override
    public int getCourseIdByName(String name) throws Exception {
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
    public ArrayList<String> getAllCourseNames() throws Exception {
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
    public String getCourseNameById(int id) throws Exception {
         connect = DBConnection.getInstance().getConnection();
         String checkid="SELECT course_name FROM course_management WHERE course_id = ?";
         PreparedStatement pst = connect.prepareStatement(checkid);
              pst.setInt(1, id);
              result=pst.executeQuery();
               if(result.next()){
                   
                 return result.getString("course_name");
               }
               return null;
    }

    @Override
    public boolean save(ClassShedulingEntity entity) throws Exception {
        connect = DBConnection.getInstance().getConnection();
        String insertdata="INSERT INTO class_scheduling(scheduling_id,course_id,subject_id,lecture_id,class_date,class_time)VALUES(?,?,?,?,?,?)";
              
                prepare=connect.prepareStatement(insertdata);
                prepare.setInt(1, entity.getScheduling_id());
                prepare.setInt(2, entity.getCourse_id());
                prepare.setInt(3, entity.getSubject_id());
                prepare.setInt(4,entity.getLecture_id());
                prepare.setString(5,entity.getClass_date());
                prepare.setString(6,entity.getClass_time());
                return prepare.executeUpdate()>0;
    }

    @Override
    public boolean update(ClassShedulingEntity entity) throws Exception {
        connect = DBConnection.getInstance().getConnection();
          String sql = "UPDATE class_scheduling SET scheduling_id=?, course_id=?, subject_id=?, lecture_id=? ,class_date=?,class_time=? WHERE scheduling_id =?";
        PreparedStatement stm = connect.prepareStatement(sql);
          stm.setInt(1, entity.getScheduling_id());
        stm.setInt(2, entity.getCourse_id());
        stm.setInt(3, entity.getSubject_id());
        stm.setInt(4, entity.getLecture_id());
        stm.setString(5,entity.getClass_date() );
        stm.setString(6,entity.getClass_time() );
        stm.setInt(7,entity.getScheduling_id() );
          return stm.executeUpdate()>0;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
         
         connect = DBConnection.getInstance().getConnection();
          String sql = "DELETE FROM class_scheduling WHERE scheduling_id =?";
                
                PreparedStatement stm = connect.prepareStatement(sql);
                
                stm.setInt(1, id);
                
               return stm.executeUpdate()>0;
    }

    @Override
    public ClassShedulingEntity findById(Integer id) throws Exception {
        connect = DBConnection.getInstance().getConnection();
         String checkshedulingid="SELECT * FROM class_scheduling WHERE scheduling_id = ?";
            prepare = connect.prepareStatement(checkshedulingid);
        prepare.setInt(1, id);
        result = prepare.executeQuery();
        if (result.next()) {
            return new ClassShedulingEntity(
                result.getInt("scheduling_id"),
                result.getInt("course_id"),
                result.getInt("subject_id"),
                result.getInt("lecture_id"),
                result.getString("class_date"),
                result.getString("class_time"));
        }
        return null; 
    }

    @Override
    public ArrayList<ClassShedulingEntity> findAll() throws Exception {
         connect = DBConnection.getInstance().getConnection();

        String sql = "SELECT * FROM class_scheduling";
        PreparedStatement stm = connect.prepareStatement(sql);

        ResultSet rs = stm.executeQuery();
        ArrayList<ClassShedulingEntity> schedulingList = new ArrayList<>();
        
        while(rs.next()){
       
        schedulingList.add(new ClassShedulingEntity(
                    rs.getInt("scheduling_id"),
                    rs.getInt("course_id"),
                    rs.getInt("subject_id"),
                    rs.getInt("lecture_id"),
                    rs.getString("class_date"),
                    rs.getString("class_time")
        ));
       
    } 
         return schedulingList;
}

    @Override
    public int getLectureIdByName(String name) throws Exception {
      connect = DBConnection.getInstance().getConnection();

        String checkid="SELECT  lecture_id FROM lecture_management WHERE lecture_name = ?";
           PreparedStatement pst = connect.prepareStatement(checkid);
              pst.setString(1, name);
              result=pst.executeQuery();
               if(result.next()){
                   //return this course_id instead of check_id because when check database table he cant finfd name check_id in table that is why i put column name in that return type
                 return result.getInt("lecture_id");
               }
               return -1;
    }

    @Override
    public String getLectureNameById(int id) throws Exception {
         connect = DBConnection.getInstance().getConnection();
         String checkid="SELECT  lecture_name FROM lecture_management WHERE lecture_id = ?";
          PreparedStatement pst = connect.prepareStatement(checkid);
              pst.setInt(1, id);
              result=pst.executeQuery();
               if(result.next()){
                   //return this course_id instead of check_id because when check database table he cant finfd name check_id in table that is why i put column name in that return type
                 return result.getString("lecture_name");
               }
               return null;
    }

    @Override
    public ArrayList<String> getAllLectureNames() throws Exception {//combo box
        connect = DBConnection.getInstance().getConnection();
        
        
        String sql="SELECT lecture_name FROM lecture_management";
            PreparedStatement pst = connect.prepareStatement(sql);
             ResultSet rs = pst.executeQuery();
              ArrayList<String> list = new ArrayList<>();
             while(rs.next()){
                  list.add(rs.getString("lecture_name"));
        }
        return list;
             }

        @Override
        public String getSubjectNameById(int id) throws Exception {
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
        public int getSubjectIdByName(String name) throws Exception {
        connect = DBConnection.getInstance().getConnection();
        String sql = "SELECT subject_id FROM subjects WHERE subjects_name = ?";
        PreparedStatement pst = connect.prepareStatement(sql);
        pst.setString(1, name);
        ResultSet rst = pst.executeQuery();
        if (rst.next()) {
            return rst.getInt("subject_id");
        }
        return -1;
        }

    @Override
    public ArrayList<String> getAllSubjectNames() throws Exception {
      connect = DBConnection.getInstance().getConnection();
        String sql = "SELECT subjects_name FROM subjects";
        PreparedStatement pst = connect.prepareStatement(sql);
        ResultSet rst = pst.executeQuery();
        ArrayList<String> list = new ArrayList<>();
        while (rst.next()) {
            list.add(rst.getString("subjects_name"));
        }
        return list;
    }
    }

   
