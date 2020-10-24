package com.dxl.pageModel;

public class PGrade {

    private Integer course_id;
    private String term_year;
    private String term_season;

    private String class_;
    private String major;

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public String getTerm_year() {
        return term_year;
    }

    public void setTerm_year(String term_year) {
        this.term_year = term_year;
    }

    public String getTerm_season() {
        return term_season;
    }

    public void setTerm_season(String term_season) {
        this.term_season = term_season;
    }
}
