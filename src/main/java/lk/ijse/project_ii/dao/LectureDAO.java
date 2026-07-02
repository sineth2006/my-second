/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.ijse.project_ii.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.project_ii.entity.LectureManagementEntity;

/**
 *
 * @author Sineth
 */
public interface LectureDAO {
     boolean save(LectureManagementEntity entity) throws SQLException, ClassNotFoundException;
    boolean update(LectureManagementEntity entity) throws SQLException, ClassNotFoundException;
    boolean delete(int id) throws SQLException, ClassNotFoundException;
    boolean exist(int id) throws SQLException, ClassNotFoundException;
    int getUserIdByName(String name) throws SQLException, ClassNotFoundException;
    int getSubjectIdByName(String name) throws SQLException, ClassNotFoundException;
     String getUserNameById(int id) throws Exception;
     String getsubjectNameById(int id) throws Exception;
     
       ArrayList<String> getAllUserNamesByPosition(int positionId) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllSubjectNames() throws SQLException, ClassNotFoundException;
    ArrayList<LectureManagementEntity> getAllLectures() throws SQLException, ClassNotFoundException;
}
