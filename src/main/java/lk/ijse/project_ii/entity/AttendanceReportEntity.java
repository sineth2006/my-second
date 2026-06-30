/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.project_ii.entity;

/**
 *
 * @author Sineth
 */
public class AttendanceReportEntity {
    private int student_id;
    private String student_name;
    private String subject;
    private String date;
    private String status;

    public AttendanceReportEntity() {
    }

    public AttendanceReportEntity(int student_id, String student_name, String subject, String date, String status) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.subject = subject;
        this.date = date;
        this.status = status;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}
