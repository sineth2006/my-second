/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.ijse.project_ii.bo;

import java.util.ArrayList;
import lk.ijse.project_ii.dto.StudentDTO;


/**
 *
 * @author Sineth
 */
public interface StudentBo extends SuperBo{
     boolean saveStudent(StudentDTO dto) throws Exception;
    boolean updateStudent(StudentDTO dto) throws Exception;
    boolean deleteStudent(int id) throws Exception;
    int getCourseId(String courseName) throws Exception;
    ArrayList<String> getCourseNames() throws Exception;
     ArrayList<StudentDTO> getAllStudents() throws Exception;
     String getCourseName(int selectedCourseId) throws Exception;

}
