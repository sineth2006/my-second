/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.project_ii.entity;

/**
 *
 * @author Sineth
 */
public class SubjectManagementEntity {
    
    private int subject_id;
    private String subject_name;
     private int course_id;

    public SubjectManagementEntity() {
    }

    public SubjectManagementEntity(int subject_id, String subject_name, int course_id) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.course_id = course_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "SubjectManagementEntity{" + "subject_id=" + subject_id + ", subject_name=" + subject_name + ", course_id=" + course_id + '}';
    }

}
