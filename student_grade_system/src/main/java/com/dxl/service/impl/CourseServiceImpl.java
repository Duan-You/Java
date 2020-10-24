package com.dxl.service.impl;

import com.dxl.dao.IBaseDao;
import com.dxl.model.Course;
import com.dxl.model.Grade;
import com.dxl.model.User;
import com.dxl.service.ICourseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service("courseService")
public class CourseServiceImpl implements ICourseService {

    private IBaseDao<Course> baseDao;

    public IBaseDao<Course> getBaseDao() {
        return baseDao;
    }

    @Autowired
    public void setBaseDao(IBaseDao<Course> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void save(Course course) {
        baseDao.save(course);
    }


    @Override
    public void remove(String ids) {
        baseDao.executeHql("delete from Course c where c.id in("+ids+")");
    }

    @Override
    public void update(Course course) {
        baseDao.update(course);
    }

    @Override
    public Course get(Integer id) {
        return baseDao.get(Course.class, id);
    }

    @Override
    public List<Course> findTeachedCourse(Integer user_id) {
        return baseDao.find("from Course c where c.user.id = " + user_id);
    }

    @Override
    public List<Course> findAll() {
        return baseDao.find("from Course");
    }

    @Override
    public List<Course> findByName(String course_name) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("course_name", course_name);
        return baseDao.find("from Course c where c.name = :course_name", params);
    }

    @Override
    public List<Course> findByClass(String course_class) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("course_class", course_class);
        return baseDao.find("from Course c where c.class_ = :course_class", params);
    }

    @Override
    public Course arrangeCourse(Integer courseId, User teacher) {
        Course course = baseDao.get(Course.class, courseId);
        course.setUser(teacher);
        return course;
    }
}
