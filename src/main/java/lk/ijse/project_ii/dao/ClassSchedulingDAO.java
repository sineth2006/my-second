/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.ijse.project_ii.dao;

import java.util.ArrayList;
import lk.ijse.project_ii.entity.ClassShedulingEntity;

/**
 *
 * @author Sineth
 */
public interface ClassSchedulingDAO extends CrudDAO<ClassShedulingEntity, Integer>  {
        int getCourseIdByName(String name) throws Exception;
        ArrayList<String> getAllCourseNames() throws Exception;
         String getCourseNameById(int id) throws Exception;
         
         int getLectureIdByName(String name) throws Exception;
    String getLectureNameById(int id) throws Exception;
    ArrayList<String> getAllLectureNames() throws Exception;
     String getSubjectNameById(int id) throws Exception;
   int getSubjectIdByName(String name) throws Exception;
   ArrayList<String> getAllSubjectNames() throws Exception;
}
