/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.project_ii.bo.impl;

import java.util.ArrayList;
import java.util.List;
import lk.ijse.project_ii.bo.ClassSchedulingBo;
import lk.ijse.project_ii.dao.ClassSchedulingDAO;

import lk.ijse.project_ii.dao.impl.ClassSchedulingDAOImpl;
import lk.ijse.project_ii.dto.ClassSchedulingDTO;
import lk.ijse.project_ii.entity.ClassShedulingEntity;

/**
 *
 * @author Sineth
 */
public class ClassSchedulingBoImpl implements ClassSchedulingBo {
private final ClassSchedulingDAO schedulingDAO = new ClassSchedulingDAOImpl();


    @Override
    public boolean saveSchedule(ClassSchedulingDTO dto) throws Exception {
         if (schedulingDAO.findById(dto.getScheduling_id()) != null) {
            throw new RuntimeException(dto.getScheduling_id() + " is already scheduled!!!");
        }
        
        return schedulingDAO.save(new ClassShedulingEntity(
            dto.getScheduling_id(),
            dto.getCourse_id(),
            dto.getSubject_id(),
            dto.getLecture_id(),
            dto.getClass_date(),
            dto.getClass_time()
        ));
    }
    @Override
    public boolean updateSchedule(ClassSchedulingDTO dto) throws Exception {
          
        return schedulingDAO.update(new ClassShedulingEntity(
            dto.getScheduling_id(),
            dto.getCourse_id(),
            dto.getSubject_id(),
            dto.getLecture_id(),
            dto.getClass_date(),
            dto.getClass_time()
        ));
    }

    @Override
    public boolean deleteSchedule(int id) throws Exception {
       return schedulingDAO.delete(id);
    }

    @Override
    public ArrayList<ClassSchedulingDTO> getAllSchedules() throws Exception {
        List<ClassShedulingEntity> entities = schedulingDAO.findAll();

        ArrayList<ClassSchedulingDTO> dtoList = new ArrayList<>();
        
        for (ClassShedulingEntity entity : entities) {
            dtoList.add(new ClassSchedulingDTO(
                entity.getScheduling_id(),
                entity.getCourse_id(),
                entity.getSubject_id(),
                entity.getLecture_id(),
                entity.getClass_date(),
                entity.getClass_time()
            ));
    }
        return dtoList;
    }
    @Override
    public int getCourseId(String courseName) throws Exception {
          return schedulingDAO.getCourseIdByName(courseName);
    }

    @Override
    public ArrayList<String> getCourseNames() throws Exception {
         return schedulingDAO.getAllCourseNames();
    }

    @Override
    public String getCourseName(int courseId) throws Exception {
       return schedulingDAO.getCourseNameById(courseId);
    }

    @Override
    public int getLectureId(String name) throws Exception {
         return schedulingDAO.getLectureIdByName(name);
    }

    @Override
    public String getLectureName(int lectureId) throws Exception {
       return schedulingDAO.getLectureNameById(lectureId);
    }

    @Override
    public ArrayList<String> getLectureNames() throws Exception {
         return schedulingDAO.getAllLectureNames();
    }

    @Override
    public String getSubjectName(int id) throws Exception {
        return schedulingDAO.getSubjectNameById(id);
    }

    @Override
    public int getSubjectId(String name) throws Exception {
     return schedulingDAO.getSubjectIdByName(name);
    }

    @Override
    public ArrayList<String> getSubjectNames() throws Exception {
        return schedulingDAO.getAllSubjectNames();
    }
    
}
