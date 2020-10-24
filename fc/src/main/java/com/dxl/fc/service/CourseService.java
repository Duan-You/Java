package com.dxl.fc.service;

import com.dxl.fc.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> find(int count);

    Course getById(int id);

    List<Course> getByIds(List<Integer> ids);

    Course getByCourse(Course course);

    void save(Course course);

    List<String> getCategory();

    int getCountByCategory(String s);
}
