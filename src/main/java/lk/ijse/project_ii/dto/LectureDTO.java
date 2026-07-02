/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lk.ijse.project_ii.dto;

/**
 *
 * @author Sineth
 */
public class LectureDTO {
     private int lecture_id;
    private String lecture_name;
    private String email;
    private String telephone_number;
    private int subject_id;
    private int user_id;

    public int getLecture_id() {
        return lecture_id;
    }

    public void setLecture_id(int lecture_id) {
        this.lecture_id = lecture_id;
    }

    public String getLecture_name() {
        return lecture_name;
    }

    public void setLecture_name(String lecture_name) {
        this.lecture_name = lecture_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone_number() {
        return telephone_number;
    }

    public void setTelephone_number(String telephone_number) {
        this.telephone_number = telephone_number;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public LectureDTO() {
    }

    public LectureDTO(int lecture_id, String lecture_name, String email, String telephone_number, int subject_id, int user_id) {
        this.lecture_id = lecture_id;
        this.lecture_name = lecture_name;
        this.email = email;
        this.telephone_number = telephone_number;
        this.subject_id = subject_id;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "LectureDTO{" + "lecture_id=" + lecture_id + ", lecture_name=" + lecture_name + ", email=" + email + ", telephone_number=" + telephone_number + ", subject_id=" + subject_id + ", user_id=" + user_id + '}';
    }
    
}
