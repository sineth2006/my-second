/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.ijse.project_ii.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.project_ii.entity.StudentManagementEntity;

/**
 *
 * @author Sineth
 */
public interface StudentDAO  {
      boolean checkStudentExists(int id) throws SQLException;
    boolean insertStudent(StudentManagementEntity entity) throws SQLException;
    boolean updateStudent(StudentManagementEntity entity) throws SQLException;
    boolean deleteStudent(int id) throws SQLException;
    int getCourseIdByName(String name) throws SQLException;
     ArrayList<String> getAllCourseNames() throws Exception;
    ArrayList<StudentManagementEntity> findAll() throws Exception;
    String getCourseNameById(int selectedCourseId) throws Exception;

}
