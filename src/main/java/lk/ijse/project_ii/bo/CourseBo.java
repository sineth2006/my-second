/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.ijse.project_ii.bo;

import java.util.List;
import lk.ijse.project_ii.dao.CourseDAO;
import lk.ijse.project_ii.dao.impl.CourseDAOImpl;
import lk.ijse.project_ii.dto.CourseDTO;

/**
 *
 * @author Sineth
 */
public interface CourseBo extends SuperBo{
    
      boolean addCourse(CourseDTO dto) throws Exception;
    boolean updateCourse(CourseDTO dto) throws Exception;
    boolean deleteCourse(int id) throws Exception;
    List<CourseDTO> getAllCourses() throws Exception;
}
