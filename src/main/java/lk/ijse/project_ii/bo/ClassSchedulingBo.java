/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.ijse.project_ii.bo;

import java.util.ArrayList;
import lk.ijse.project_ii.dto.ClassSchedulingDTO;

/**
 *
 * @author Sineth
 */
public interface ClassSchedulingBo extends SuperBo {
     boolean saveSchedule(ClassSchedulingDTO dto) throws Exception;
    boolean updateSchedule(ClassSchedulingDTO dto) throws Exception;
    boolean deleteSchedule(int id) throws Exception;
    ArrayList<ClassSchedulingDTO> getAllSchedules() throws Exception;
    
    // Course Mapping Methods
    int getCourseId(String courseName) throws Exception;
    ArrayList<String> getCourseNames() throws Exception;
    String getCourseName(int courseId) throws Exception;
    
    // Lecture Mapping Methods
    int getLectureId(String lectureName) throws Exception;
    String getLectureName(int lectureId) throws Exception;
    ArrayList<String> getLectureNames() throws Exception;
    
    // Subject Mapping Methods
    String getSubjectName(int id) throws Exception;
    int getSubjectId(String name) throws Exception;
    ArrayList<String> getSubjectNames() throws Exception;
}
