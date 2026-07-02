/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.ijse.project_ii.bo;

import java.sql.SQLException;
import java.util.ArrayList;
import lk.ijse.project_ii.dto.LectureDTO;
import lk.ijse.project_ii.entity.LectureManagementEntity;

/**
 *
 * @author Sineth
 */
public interface LectureManagementBo {
     boolean saveLecture(LectureDTO dto) throws Exception;
    
    boolean updateLecture(LectureDTO dto) throws Exception;
    
    boolean deleteLecture(int id) throws Exception;
    
    ArrayList<LectureDTO> getAllLectures() throws Exception;
    
    boolean existLecture(int id) throws Exception;
    
    int getUserIdByName(String name) throws Exception;
    
    int getSubjectIdByName(String name) throws Exception;
    
    ArrayList<String> getAllUserNamesByPosition(int positionId) throws Exception;
    
    ArrayList<String> getAllSubjectNames() throws Exception;
    
    String getUserNameById(int id) throws Exception;
    
    String getSubjectNameById(int id) throws Exception;
}
