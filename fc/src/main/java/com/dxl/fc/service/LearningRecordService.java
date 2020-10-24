package com.dxl.fc.service;

import com.dxl.fc.model.CourseRecord;
import com.dxl.fc.model.LearningRecord;

import java.util.List;

public interface LearningRecordService {
    void add(Integer id, int course_record_id);

    LearningRecord getBystudentAndCourseRecord(Integer id, int course_record_id);

    List<LearningRecord> getByStudent(int student_id);

    List<LearningRecord> getByCourseRecords(List<CourseRecord> courseRecords);

    List<LearningRecord> getByCourseRecord(int course_record_id);

    LearningRecord getById(int learning_record_id);

    void update(LearningRecord learningRecord);
}
