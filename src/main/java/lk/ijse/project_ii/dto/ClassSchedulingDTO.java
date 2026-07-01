/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.project_ii.dto;

/**
 *
 * @author Sineth
 */
public class ClassSchedulingDTO {
     private int scheduling_id;
    private int course_id;
    private int subject_id;
    private int lecture_id;
    private String class_date;
    private String class_time;

    public ClassSchedulingDTO() {
    }

    public ClassSchedulingDTO(int scheduling_id, int course_id, int subject_id, int lecture_id, String class_date, String class_time) {
        this.scheduling_id = scheduling_id;
        this.course_id = course_id;
        this.subject_id = subject_id;
        this.lecture_id = lecture_id;
        this.class_date = class_date;
        this.class_time = class_time;
    }

    public int getScheduling_id() {
        return scheduling_id;
    }

    public void setScheduling_id(int scheduling_id) {
        this.scheduling_id = scheduling_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getLecture_id() {
        return lecture_id;
    }

    public void setLecture_id(int lecture_id) {
        this.lecture_id = lecture_id;
    }

    public String getClass_date() {
        return class_date;
    }

    public void setClass_date(String class_date) {
        this.class_date = class_date;
    }

    public String getClass_time() {
        return class_time;
    }

    public void setClass_time(String class_time) {
        this.class_time = class_time;
    }

    @Override
    public String toString() {
        return "ClassSchedulingDTO{" + "scheduling_id=" + scheduling_id + ", course_id=" + course_id + ", subject_id=" + subject_id + ", lecture_id=" + lecture_id + ", class_date=" + class_date + ", class_time=" + class_time + '}';
    }
    
}
