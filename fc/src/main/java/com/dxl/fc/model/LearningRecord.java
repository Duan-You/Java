package com.dxl.fc.model;

import java.util.Date;

public class LearningRecord {
    private Integer id;

    private Integer studentId;

    private Integer courseRecordId;

    private String score;

    private Date recordDate;

    private Integer learningTime;//学时

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseRecordId() {
        return courseRecordId;
    }

    public void setCourseRecordId(Integer courseRecordId) {
        this.courseRecordId = courseRecordId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Integer getLearningTime() {
        return learningTime;
    }

    public void setLearningTime(Integer learningTime) {
        this.learningTime = learningTime;
    }
}