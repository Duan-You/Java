package com.dxl.fc.service;

import com.dxl.fc.model.Student;

public interface StudentService {
    boolean existCell(String cell);

    void save(Student student);


    Student login(String cell, String password);
    Student getStudentByCell(String cell);

    Student getById(Integer studentId);

    int getCount();
}
