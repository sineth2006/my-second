/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.project_ii.entity;

/**
 *
 * @author Sineth
 */
public class AttendanceMarkingEntity {
    
    // --- NESTED ENUM INSIDE THE CLASS ---
    // This allows the enum to be public without breaking Java's file naming rules
    public static enum AttendanceStatus {
        present, absent
    }

    private int attendance_id;
    private int scheduling_id;
    private int student_id;
    private AttendanceStatus status;

    public AttendanceMarkingEntity() {
    }

    public AttendanceMarkingEntity(int attendance_id, int scheduling_id, int student_id, AttendanceStatus status) {
        this.attendance_id = attendance_id;
        this.scheduling_id = scheduling_id;
        this.student_id = student_id;
        this.status = status;
    }

    public int getAttendanceId() {
        return attendance_id;
    }

    public void setAttendanceId(int attendance_id) {
        this.attendance_id = attendance_id;
    }

    public int getSchedulingId() {
        return scheduling_id;
    }

    public void setSchedulingId(int scheduling_id) {
        this.scheduling_id = scheduling_id;
    }

    public int getStudentId() {
        return student_id;
    }

    public void setStudentId(int student_id) {
        this.student_id = student_id;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }
}
