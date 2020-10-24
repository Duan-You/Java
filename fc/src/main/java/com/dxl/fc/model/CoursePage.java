package com.dxl.fc.model;

import java.util.Date;

public class CoursePage {

    private Integer student_id;
    private String student_name;

    private String student_gender;
    private Integer teacher_id;
    private Integer course_id;
    private Integer course_record_id;
    private Integer learning_record_id;
    private String score;
    private Integer learning_time;
    private String course_name;
    private String course_category;
    private String course_school;
    private String course_point_system;
    private String teacher_name;
    private String teacher_gender;
    private String teacher_title;
    private Date course_date;

    public CoursePage() {
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_gender() {
        return student_gender;
    }

    public void setStudent_gender(String student_gender) {
        this.student_gender = student_gender;
    }

    public Date getCourse_date() {
        return course_date;
    }

    public void setCourse_date(Date course_date) {
        this.course_date = course_date;
    }

    public String getTeacher_gender() {
        return teacher_gender;
    }

    public void setTeacher_gender(String teacher_gender) {
        this.teacher_gender = teacher_gender;
    }

    public String getTeacher_title() {
        return teacher_title;
    }

    public void setTeacher_title(String teacher_title) {
        this.teacher_title = teacher_title;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Integer teacher_id) {
        this.teacher_id = teacher_id;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public Integer getCourse_record_id() {
        return course_record_id;
    }

    public void setCourse_record_id(Integer course_record_id) {
        this.course_record_id = course_record_id;
    }

    public Integer getLearning_record_id() {
        return learning_record_id;
    }

    public void setLearning_record_id(Integer learning_record_id) {
        this.learning_record_id = learning_record_id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Integer getLearning_time() {
        return learning_time;
    }

    public void setLearning_time(Integer learning_time) {
        this.learning_time = learning_time;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_category() {
        return course_category;
    }

    public void setCourse_category(String course_category) {
        this.course_category = course_category;
    }

    public String getCourse_school() {
        return course_school;
    }

    public void setCourse_school(String course_school) {
        this.course_school = course_school;
    }

    public String getCourse_point_system() {
        return course_point_system;
    }

    public void setCourse_point_system(String course_point_system) {
        this.course_point_system = course_point_system;
    }
}
