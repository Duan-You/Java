package com.dxl.fc.model;

import java.util.Date;

public class Resource {
    private Integer id;

    private String name;

    private String category;

    private String content;

    private Date resDate;

    private Integer courseRecordId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getResDate() {
        return resDate;
    }

    public void setResDate(Date resDate) {
        this.resDate = resDate;
    }

    public Integer getCourseRecordId() {
        return courseRecordId;
    }

    public void setCourseRecordId(Integer courseRecordId) {
        this.courseRecordId = courseRecordId;
    }
}