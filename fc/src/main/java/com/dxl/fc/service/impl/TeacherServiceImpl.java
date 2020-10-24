package com.dxl.fc.service.impl;

import com.dxl.fc.dao.TeacherMapper;
import com.dxl.fc.model.Teacher;
import com.dxl.fc.model.TeacherExample;
import com.dxl.fc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
    @Override
    public boolean existCell(String cell) {
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andCellEqualTo(cell);
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        if (teacherList.isEmpty()|| teacherList.get(0) == null)
            return false;
        return true;
    }

    @Override
    public void save(Teacher teacher) {
        teacherMapper.insert(teacher);
    }

    @Override
    public Teacher login(String cell, String password) {
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andCellEqualTo(cell).andPasswordEqualTo(password);
        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        if (teacherList.isEmpty()|| teacherList.get(0) == null)
            return null;
        return teacherList.get(0);
    }

    @Override
    public List<Teacher> getByIds(List<Integer> ids) {
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andIdIn(ids);
        return teacherMapper.selectByExample(teacherExample);
    }

    @Override
    public Teacher getById(int teacher_id) {
        return teacherMapper.selectByPrimaryKey(teacher_id);
    }

    @Override
    public List<Teacher> getBySchool(int school_id) {
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andSchoolIdEqualTo(school_id);
        return teacherMapper.selectByExample(teacherExample);
    }

    @Override
    public int getCount() {
        TeacherExample teacherExample = new TeacherExample();
        return (int) teacherMapper.countByExample(teacherExample);
    }
}
