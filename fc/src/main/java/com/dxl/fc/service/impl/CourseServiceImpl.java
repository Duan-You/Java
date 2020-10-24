package com.dxl.fc.service.impl;

import com.dxl.fc.dao.CourseMapper;
import com.dxl.fc.model.Course;
import com.dxl.fc.model.CourseExample;
import com.dxl.fc.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<Course> find(int count) {
        CourseExample courseExample = new CourseExample();
        courseExample.setStartRow(1);
        courseExample.setPageSize(count);
        return courseMapper.selectByExample(courseExample);
    }

    @Override
    public Course getById(int id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Course> getByIds(List<Integer> ids) {
        CourseExample courseExample = new CourseExample();
        courseExample.createCriteria().andIdIn(ids);
        return courseMapper.selectByExample(courseExample);
    }

    @Override
    public Course getByCourse(Course course) {
        CourseExample courseExample = new CourseExample();
        courseExample.createCriteria().andCategoryEqualTo(course.getCategory()).andNameEqualTo(course.getName())
                .andSchoolEqualTo(course.getSchool());
        List<Course> courses = courseMapper.selectByExample(courseExample);
        if (courses==null||courses.isEmpty()){
            return null;
        }
        return courses.get(0);
    }

    @Override
    public void save(Course course) {
        courseMapper.insert(course);

    }

    @Override
    public List<String> getCategory() {

        
        return courseMapper.getCategory();
    }

    @Override
    public int getCountByCategory(String s) {
        CourseExample courseExample = new CourseExample();
        courseExample.createCriteria().andCategoryEqualTo(s);
        return (int) courseMapper.countByExample(courseExample);
    }
}
