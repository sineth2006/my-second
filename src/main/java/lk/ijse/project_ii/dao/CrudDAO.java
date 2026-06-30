/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package lk.ijse.project_ii.dao;

import java.util.List;

/**
 *
 * @author Sineth
 */
public interface CrudDAO<T, ID> extends SuperDAO {
    boolean save(T entity) throws Exception;
    boolean update(T entity) throws Exception;
    boolean delete(ID id) throws Exception;
    T findById(ID id) throws Exception;
    List<T> findAll() throws Exception;
}
