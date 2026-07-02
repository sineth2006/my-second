/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.project_ii.bo.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lk.ijse.project_ii.bo.LectureManagementBo;
import lk.ijse.project_ii.dao.LectureDAO;
import lk.ijse.project_ii.dao.impl.LectureDAOImpl;
import lk.ijse.project_ii.dto.LectureDTO;
import lk.ijse.project_ii.entity.LectureManagementEntity;

/**
 *
 * @author Sineth
 */
public class LectureManagementBoImpl implements LectureManagementBo {
     private final LectureDAO lectureDAO = new LectureDAOImpl();
     
    @Override
    public boolean saveLecture(LectureDTO dto) throws SQLException, ClassNotFoundException {
         if (lectureDAO.exist(dto.getLecture_id())) {
            throw new RuntimeException(dto.getLecture_id() + " is already taken!!!");
    }
          
        return lectureDAO.save(new LectureManagementEntity(
            dto.getLecture_id(),
            dto.getLecture_name(),
            dto.getEmail(),
            dto.getTelephone_number(),
            dto.getSubject_id(),
            dto.getUser_id()
        ));
         
    }

    @Override
    public boolean updateLecture(LectureDTO dto) throws SQLException, ClassNotFoundException {
        return lectureDAO.update(new LectureManagementEntity(
            dto.getLecture_id(),
            dto.getLecture_name(),
            dto.getEmail(),
            dto.getTelephone_number(),
            dto.getSubject_id(),
            dto.getUser_id()
        ));
    }

    @Override
    public boolean deleteLecture(int id) throws SQLException, ClassNotFoundException {
     return lectureDAO.delete(id);
    }

    @Override
    public boolean existLecture(int id) throws SQLException, ClassNotFoundException {
        return lectureDAO.exist(id);
    }

    @Override
    public int getUserIdByName(String name) throws SQLException, ClassNotFoundException {
    return lectureDAO.getUserIdByName(name);
    }

    @Override
    public int getSubjectIdByName(String name) throws SQLException, ClassNotFoundException {
    return lectureDAO.getSubjectIdByName(name);
    }

    @Override
    public ArrayList<String> getAllUserNamesByPosition(int positionId) throws SQLException, ClassNotFoundException {
     return lectureDAO.getAllUserNamesByPosition(positionId);
    }

    @Override
    public ArrayList<String> getAllSubjectNames() throws SQLException, ClassNotFoundException {
    return lectureDAO.getAllSubjectNames();
    }

    @Override
    public ArrayList<LectureDTO> getAllLectures() throws SQLException, ClassNotFoundException {
        List<LectureManagementEntity> entities = lectureDAO.getAllLectures();
        ArrayList<LectureDTO> dtoList = new ArrayList<>();
        
        for (LectureManagementEntity entity : entities) {
            dtoList.add(new LectureDTO(
                entity.getLecture_id(),
                entity.getLecture_name(),
                entity.getEmail(),
                entity.getTelephone_number(),
                entity.getSubject_id(),
                entity.getUser_id()
            ));
        }
        return dtoList;
    }

    @Override
    public String getUserNameById(int id) throws SQLException, ClassNotFoundException, Exception {
         return lectureDAO.getUserNameById(id);
    }

    @Override
    public String getSubjectNameById(int id) throws SQLException, ClassNotFoundException, Exception {
        return lectureDAO.getsubjectNameById(id);
    }
    
}
