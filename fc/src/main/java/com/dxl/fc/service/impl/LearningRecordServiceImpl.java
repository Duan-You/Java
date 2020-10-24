package com.dxl.fc.service.impl;

import com.dxl.fc.dao.LearningRecordMapper;
import com.dxl.fc.model.CourseRecord;
import com.dxl.fc.model.LearningRecord;
import com.dxl.fc.model.LearningRecordExample;
import com.dxl.fc.service.LearningRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LearningRecordServiceImpl implements LearningRecordService {

    @Autowired
    LearningRecordMapper learningRecordMapper;

    @Override
    public void add(Integer id, int course_record_id) {
        LearningRecord learningRecord = new LearningRecord();
        learningRecord.setRecordDate(new Date());
        learningRecord.setStudentId(id);
        learningRecord.setCourseRecordId(course_record_id);
        learningRecordMapper.insert(learningRecord);
    }

    @Override
    public LearningRecord getBystudentAndCourseRecord(Integer id, int course_record_id) {
        LearningRecordExample learningRecordExample = new LearningRecordExample();
        learningRecordExample.createCriteria().andStudentIdEqualTo(id).andCourseRecordIdEqualTo(course_record_id);
        List<LearningRecord> learningRecords = learningRecordMapper.selectByExample(learningRecordExample);
        if(learningRecords.isEmpty()){
            return null;
        }
        return learningRecords.get(0);
    }

    @Override
    public List<LearningRecord> getByStudent(int student_id) {
        LearningRecordExample learningRecordExample = new LearningRecordExample();
        learningRecordExample.createCriteria().andStudentIdEqualTo(student_id);
        List<LearningRecord> learningRecords = learningRecordMapper.selectByExample(learningRecordExample);
        return learningRecords;
    }

    @Override
    public List<LearningRecord> getByCourseRecords(List<CourseRecord> courseRecords) {
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < courseRecords.size(); i++) {
            integers.add(courseRecords.get(i).getId());
        }
        LearningRecordExample learningRecordExample = new LearningRecordExample();
        learningRecordExample.createCriteria().andCourseRecordIdIn(integers);
        return learningRecordMapper.selectByExample(learningRecordExample);
    }

    @Override
    public List<LearningRecord> getByCourseRecord(int course_record_id) {
        LearningRecordExample learningRecordExample = new LearningRecordExample();
        learningRecordExample.createCriteria().andCourseRecordIdEqualTo(course_record_id);
        return learningRecordMapper.selectByExample(learningRecordExample);
    }

    @Override
    public LearningRecord getById(int learning_record_id) {
        return learningRecordMapper.selectByPrimaryKey(learning_record_id);
    }

    @Override
    public void update(LearningRecord learningRecord) {
        learningRecordMapper.updateByPrimaryKeySelective(learningRecord);
    }
}
