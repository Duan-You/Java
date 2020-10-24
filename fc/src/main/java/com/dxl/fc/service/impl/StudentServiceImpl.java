package com.dxl.fc.service.impl;

import com.dxl.fc.dao.StudentMapper;
import com.dxl.fc.model.Student;
import com.dxl.fc.model.StudentExample;
import com.dxl.fc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public boolean existCell(String cell) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andCellEqualTo(cell);
        List<Student> studentList = studentMapper.selectByExample(studentExample);
        if (studentList.isEmpty()|| studentList.get(0) == null)
            return false;
        return true;
    }

    @Override
    public void save(Student student) {
        studentMapper.insert(student);
    }

    @Override
    public Student getStudentByCell(String cell) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andCellEqualTo(cell);
        List<Student> studentList = studentMapper.selectByExample(studentExample);
        if (studentList.isEmpty()|| studentList.get(0) == null)
            return null;
        return studentList.get(0);
    }

    @Override
    public Student login(String cell, String password) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andCellEqualTo(cell).andPasswordEqualTo(password);
        List<Student> studentList = studentMapper.selectByExample(studentExample);
        if (studentList.isEmpty()|| studentList.get(0) == null)
            return null;
        return studentList.get(0);
    }

    @Override
    public Student getById(Integer studentId) {
        return studentMapper.selectByPrimaryKey(studentId);
    }

    @Override
    public int getCount() {
        StudentExample studentExample = new StudentExample();
        return (int) studentMapper.countByExample(studentExample);
    }
}
