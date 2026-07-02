/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.ijse.project_ii.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.project_ii.dto.SubjectDTO;

/**
 *
 * @author Sineth
 */
public interface SubjectBo {
       boolean addSubject(SubjectDTO dto) throws SQLException;
    boolean updateSubject(SubjectDTO dto) throws SQLException;
    boolean deleteSubject(int id) throws SQLException;
    boolean isSubjectExist(int id) throws SQLException;
    ArrayList<SubjectDTO> getAllSubjects() throws SQLException;
    int getCourseIdByName(String courseName) throws SQLException;
    String getCourseNameById(int courseId) throws SQLException;
    ArrayList<String> getCourseNames() throws Exception;
}
