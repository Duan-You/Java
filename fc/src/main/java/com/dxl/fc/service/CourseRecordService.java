package com.dxl.fc.service;

import com.dxl.fc.model.CourseRecord;

import java.util.List;

public interface CourseRecordService {
    List<CourseRecord> find(int count);

    CourseRecord getById(int id);

    List<CourseRecord> getBbyIds(List<Integer> list);

    List<CourseRecord> getByTeacher(int teacher_id);

    void save(CourseRecord courseRecord);
}
