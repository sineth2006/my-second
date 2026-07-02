/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.project_ii.dto;

/**
 *
 * @author Sineth
 */
public class AttendanceMarkingDTO {
     private int attendanceId;
    private int schedulingId;
    private int studentId;
    private String status;

    public AttendanceMarkingDTO() {
    }

    public AttendanceMarkingDTO(int attendanceId, int schedulingId, int studentId, String status) {
        this.attendanceId = attendanceId;
        this.schedulingId = schedulingId;
        this.studentId = studentId;
        this.status = status;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getSchedulingId() {
        return schedulingId;
    }

    public void setSchedulingId(int schedulingId) {
        this.schedulingId = schedulingId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AttendanceMarkingDTO{" + "attendanceId=" + attendanceId + ", schedulingId=" + schedulingId + ", studentId=" + studentId + ", status=" + status + '}';
    }
    
}
