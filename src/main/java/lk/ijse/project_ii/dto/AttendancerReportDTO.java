/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.project_ii.dto;

/**
 *
 * @author Sineth
 */
public class AttendancerReportDTO {
     private int studentId;
    private String studentName;
    private String subjectName;
    private String attendanceDate;
    private String status;

    public AttendancerReportDTO() {
    }

    public AttendancerReportDTO(int studentId, String studentName, String subjectName, String attendanceDate, String status) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.subjectName = subjectName;
        this.attendanceDate = attendanceDate;
        this.status = status;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AttendancerReportDTO{" + "studentId=" + studentId + ", studentName=" + studentName + ", subjectName=" + subjectName + ", attendanceDate=" + attendanceDate + ", status=" + status + '}';
    }
    
}
