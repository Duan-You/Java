package com.dxl.fc.service.impl;

import com.dxl.fc.dao.CourseRecordMapper;
import com.dxl.fc.model.CourseRecord;
import com.dxl.fc.model.CourseRecordExample;
import com.dxl.fc.service.CourseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseRecordServiceImpl implements CourseRecordService {

    @Autowired
    CourseRecordMapper courseRecordMapper;

    @Override
    public List<CourseRecord> find(int count) {
        CourseRecordExample courseRecordExample = new CourseRecordExample();
        courseRecordExample.setStartRow(0);
        courseRecordExample.setPageSize(4);
        return courseRecordMapper.selectByExample(courseRecordExample);
    }

    @Override
    public CourseRecord getById(int id) {
        return courseRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CourseRecord> getBbyIds(List<Integer> list) {
        CourseRecordExample courseRecordExample = new CourseRecordExample();
        courseRecordExample.createCriteria().andIdIn(list);
        return courseRecordMapper.selectByExample(courseRecordExample);
    }

    @Override
    public List<CourseRecord> getByTeacher(int teacher_id) {
        CourseRecordExample courseRecordExample = new CourseRecordExample();
        courseRecordExample.createCriteria().andTeacherIdEqualTo(teacher_id);
        return courseRecordMapper.selectByExample(courseRecordExample);
    }

    @Override
    public void save(CourseRecord courseRecord) {
        courseRecordMapper.insert(courseRecord);
    }
}
