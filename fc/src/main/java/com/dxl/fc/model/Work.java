package com.dxl.fc.model;

import java.util.Date;

public class Work {
    private Integer id;

    private String name;

    private String info;

    private Date startDate;

    private Date deadDate;

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDeadDate() {
        return deadDate;
    }

    public void setDeadDate(Date deadDate) {
        this.deadDate = deadDate;
    }

    public Integer getCourseRecordId() {
        return courseRecordId;
    }

    public void setCourseRecordId(Integer courseRecordId) {
        this.courseRecordId = courseRecordId;
    }
}