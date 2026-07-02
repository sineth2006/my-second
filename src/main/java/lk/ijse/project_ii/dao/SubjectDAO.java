/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.ijse.project_ii.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.project_ii.entity.SubjectManagementEntity;

/**
 *
 * @author Sineth
 */
public interface SubjectDAO {
      boolean add(SubjectManagementEntity entity) throws SQLException;
    boolean update(SubjectManagementEntity entity) throws SQLException;
    boolean delete(int id) throws SQLException;
    boolean exist(int id) throws SQLException;
    ArrayList<SubjectManagementEntity> getAll() throws SQLException;
    int getCourseIdByName(String courseName) throws SQLException;
    String getCourseNameById(int courseId) throws SQLException;
   ArrayList<String> getAllCourseNames() throws Exception;
}
