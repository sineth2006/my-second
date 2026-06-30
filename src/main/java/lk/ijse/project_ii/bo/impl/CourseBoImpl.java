/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.project_ii.bo.impl;

import java.util.List;
import lk.ijse.project_ii.bo.CourseBo;
import lk.ijse.project_ii.dao.CourseDAO;
import lk.ijse.project_ii.dao.impl.CourseDAOImpl;
import lk.ijse.project_ii.dto.CourseDTO;
import lk.ijse.project_ii.entity.CourseManagementEntity;

/**
 *
 * @author Sineth
 */
public class CourseBoImpl implements CourseBo {

      private final CourseDAO courseDAO = new CourseDAOImpl();
    @Override
    public boolean addCourse(CourseDTO dto) throws Exception {
        
          if (courseDAO.existByName(dto.getName())) {
            throw new IllegalArgumentException(dto.getName() + " is already taken!!!");
        }
        CourseManagementEntity entity = new CourseManagementEntity(
                dto.getId(),
                dto.getName(),
                dto.getDuration()
        );

        return courseDAO.save(entity);
    
    }

    @Override
    public boolean updateCourse(CourseDTO dto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteCourse(int id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<CourseDTO> getAllCourses() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
