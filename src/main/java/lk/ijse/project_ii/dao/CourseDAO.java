/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.ijse.project_ii.dao;

import lk.ijse.project_ii.entity.CourseManagementEntity;

/**
 *
 * @author Sineth
 */
public interface CourseDAO extends CrudDAO<CourseManagementEntity, Integer> {
     boolean existByName(String name) throws Exception;
}
