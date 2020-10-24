package com.dxl.fc.service;

import com.dxl.fc.model.Teacher;

import java.util.List;

public interface TeacherService {
    boolean existCell(String cell);

    void save(Teacher teacher);

    Teacher login(String cell, String password);

    List<Teacher> getByIds(List<Integer> ids);

    Teacher getById(int teacher_id);

    List<Teacher> getBySchool(int school_id);

    int getCount();
}
