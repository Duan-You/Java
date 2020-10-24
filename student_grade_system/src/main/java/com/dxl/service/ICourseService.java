package com.dxl.service;

import com.dxl.model.Course;
import com.dxl.model.User;

import java.util.List;

public interface ICourseService {

    public void save(Course course);

    public void remove(String ids);

    public void update(Course course);

    public Course get(Integer id);

    public List<Course> findTeachedCourse(Integer user_id);

    List<Course> findAll();

    List<Course> findByName(String course_name);

    List<Course> findByClass(String course_class);

    Course arrangeCourse(Integer courseId, User teacher);
}
