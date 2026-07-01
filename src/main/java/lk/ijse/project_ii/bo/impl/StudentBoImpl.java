/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.project_ii.bo.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.project_ii.bo.StudentBo;
import lk.ijse.project_ii.dao.StudentDAO;
import lk.ijse.project_ii.dao.impl.StudentDAOImpl;
import lk.ijse.project_ii.dto.StudentDTO;
import lk.ijse.project_ii.entity.StudentManagementEntity;

/**
 *
 * @author Sineth
 */
public class StudentBoImpl implements StudentBo {

      private final StudentDAO studentDAO = new StudentDAOImpl();
      
    @Override
    public boolean saveStudent(StudentDTO dto) throws Exception {
         if (studentDAO.checkStudentExists(dto.getStudent_id())) {
            throw new RuntimeException(dto.getStudent_id() + "is already taken!!!");
        }
        StudentManagementEntity entity = new StudentManagementEntity(
                dto.getStudent_id(),
                dto.getStudent_name(),
                dto.getTelephone_number(),
                dto.getEmail(),
                dto.getCourse_id()
        );
        return studentDAO.insertStudent(entity);
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws Exception {
        StudentManagementEntity entity = new StudentManagementEntity(
                dto.getStudent_id(),
                dto.getStudent_name(),
                dto.getTelephone_number(),
                dto.getEmail(),
                dto.getCourse_id()
        );
        return studentDAO.updateStudent(entity);
    }

    @Override
    public boolean deleteStudent(int id) throws Exception {
     return studentDAO.deleteStudent(id);
    }

    @Override
    public int getCourseId(String courseName) throws Exception {
        return studentDAO.getCourseIdByName(courseName);
    }

    @Override
    public ArrayList<String> getCourseNames() throws Exception {
       return studentDAO.getAllCourseNames();
    }
    
    @Override
    
    public ArrayList<StudentDTO> getAllStudents() throws Exception {
    ArrayList<StudentManagementEntity> entities = studentDAO.findAll(); // Calls the template method from CrudDAO
    ArrayList<StudentDTO> dtoList = new ArrayList<>();
    
    for (StudentManagementEntity entity : entities) {
        dtoList.add(new StudentDTO(
            entity.getStudent_id(),
            entity.getStudent_name(),
            entity.getTelephone_number(),
            entity.getEmail(),
            entity.getCourse_id()
        ));
    }
    return dtoList;
}
    @Override
public String getCourseName(int selectedCourseId) throws Exception {
    return studentDAO.getCourseNameById(selectedCourseId);
}

}
