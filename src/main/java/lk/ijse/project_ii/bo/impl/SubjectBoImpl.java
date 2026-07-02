/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.project_ii.bo.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.project_ii.bo.SubjectBo;
import lk.ijse.project_ii.dao.SubjectDAO;
import lk.ijse.project_ii.dao.impl.SubjectDAOImpl;
import lk.ijse.project_ii.dto.SubjectDTO;
import lk.ijse.project_ii.entity.SubjectManagementEntity;

/**
 *
 * @author Sineth
 */
public class SubjectBoImpl implements SubjectBo {
  private final SubjectDAO subjectDAO = new SubjectDAOImpl();
    @Override
    public boolean addSubject(SubjectDTO dto) throws SQLException {
          if (subjectDAO.exist(dto.getSubject_id())) {
        throw new RuntimeException(dto.getSubject_id() + " is already taken!!!"); 
          }
         return subjectDAO.add(new SubjectManagementEntity(
                 dto.getSubject_id(),
                 dto.getSubject_name(),
                 dto.getCourse_id()
                 
    ));

    }

    @Override
    public boolean updateSubject(SubjectDTO dto) throws SQLException {
         return subjectDAO.update(new SubjectManagementEntity(
                 dto.getSubject_id(),
                 dto.getSubject_name(),
                 dto.getCourse_id()
                 
    ));

    }

    @Override
    public boolean deleteSubject(int id) throws SQLException {
       return subjectDAO.delete(id);
    }

    @Override
    public boolean isSubjectExist(int id) throws SQLException {
     return subjectDAO.exist(id);
         }

    @Override
    public ArrayList<SubjectDTO> getAllSubjects() throws SQLException {
         ArrayList<SubjectManagementEntity> entities = subjectDAO.getAll();
        ArrayList<SubjectDTO> dtos = new ArrayList<>();
        for (SubjectManagementEntity ent : entities) {
            dtos.add(new SubjectDTO(
                    ent.getSubject_id(),
                    ent.getSubject_name(),
                    ent.getCourse_id()));
        }
        return dtos;
    }

    @Override
    public int getCourseIdByName(String courseName) throws SQLException {
          return subjectDAO.getCourseIdByName(courseName);
    }

    @Override
    public String getCourseNameById(int courseId) throws SQLException {
       return subjectDAO.getCourseNameById(courseId);
    }

    @Override
    public ArrayList<String> getCourseNames() throws Exception {
         return subjectDAO.getAllCourseNames();
    }
    
}
