package com.dxl.service.impl;

import com.dxl.dao.IBaseDao;
import com.dxl.model.Course;
import com.dxl.model.Grade;
import com.dxl.model.User;
import com.dxl.pageModel.PGrade;
import com.dxl.service.IGradeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service("gradeService")
public class GradeServiceImpl implements IGradeService {

    private static final Logger logger = Logger.getLogger(GradeServiceImpl.class);
    private IBaseDao<Grade> baseDao;

    public IBaseDao<Grade> getBaseDao() {
        return baseDao;
    }

    @Autowired
    public void setBaseDao(IBaseDao<Grade> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void save(Grade grade) {
        baseDao.save(grade);
    }

    @Override
    public void remove(String ids) {
        baseDao.executeHql("delete from Grade where id in (" + ids + ")");
    }

    @Override
    public Grade get(Integer id) {
        return baseDao.get(Grade.class, id);
    }

    @Override
    public void update(Grade grade) {

        baseDao.update(grade);
    }

    @Override
    public List<Grade> findAll() {
        return baseDao.find("from Grade");
    }

    @Override
    public List<Grade> searchGradeByCondition(String courseName, String term) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("courseName", courseName);
        params.put("term", term);
        return baseDao.find("from Grade g where g.course.name = :courseName and g.term = :term", params);
    }

    @Override
    public List<Grade> searchUserGrade(Integer user_id) {
        return baseDao.find("from Grade g where g.user.id = " + user_id);
    }

    @Override
    public List<Grade> searchUserGradeByCondition(Integer user_id, String courseName, String term) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_id);
        params.put("courseName", courseName);
        params.put("term", term);
        return baseDao.find("from Grade g where g.user.id = :user_id and g.course.name = :courseName and g.term = :term", params);
    }

    @Override
    public void update(List<PGrade> pgrades) {
        for (int i = 0; i < pgrades.size(); i++) {

        }

    }

    @Override
    public void updateGrades(String update_ids, String update_credits, String update_gpas, String update_grades) {
        String[] update_id = update_ids.split(",");
        String[] update_credit = update_credits.split(",");
        String[] update_gpa = update_gpas.split(",");
        String[] update_grade = update_grades.split(",");
        Integer[] ids = new Integer[update_id.length];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = Integer.parseInt(update_id[i]);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", ids[i]);
            params.put("credit", update_credit[i]);
            params.put("gpa", update_gpa[i]);
            params.put("grade", update_grade[i]);
            baseDao.executeUpdate("update Grade g set g.credit = :credit,g.gpa = :gpa,g.grade = :grade where g.id = :id", params);
        }
    }

    @Override
    public void arrangeGrade(Course course, List<User> students) {
        for (int i = 0; i < students.size(); i++) {
            Grade grade = new Grade();
            grade.setCourse(course);
            grade.setUser(students.get(i));
        }

    }

    @Override
    public Grade findByCourseAndStudent(Integer course_id, Integer student_id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("course_id", course_id);
        params.put("student_id", student_id);
        List<Grade> grades = baseDao.find("from Grade g where g.course.id = :course_id and g.user.id = :student_id", params);
        if (grades == null || grades.size() == 0) {
            return null;
        } else {
            return grades.get(0);
        }
    }

    @Override
    public List<Grade> searchUserGrade(Integer user_id, String term) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_id);
        params.put("term", term);
        return baseDao.find("from Grade g where g.user.id = :user_id and g.term = :term", params);
    }

}
