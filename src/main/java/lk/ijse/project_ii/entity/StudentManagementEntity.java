/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.project_ii.entity;

/**
 *
 * @author Sineth
 */
public class StudentManagementEntity {
    private int student_id;
    private String student_name;
    private String telephone_number;
    private String email;
    private int course_id;

     public StudentManagementEntity() {
    }
     
     
    public StudentManagementEntity(int student_id, String student_name, String telephone_number, String email, int course_id) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.telephone_number = telephone_number;
        this.email = email;
        this.course_id = course_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "StudentManagementEntity{" + "student_id=" + student_id + ", student_name=" + student_name + ", telephone_number=" + telephone_number + ", email=" + email + ", course_id=" + course_id + '}';
    }

   
}
