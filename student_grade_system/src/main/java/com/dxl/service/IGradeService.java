package com.dxl.service;

import com.dxl.model.Course;
import com.dxl.model.Grade;
import com.dxl.model.User;
import com.dxl.pageModel.PGrade;

import java.util.List;

public interface IGradeService {

    public void save(Grade grade);

    public void remove(String ids);

    public Grade get(Integer id);

    public void update(Grade grade);

    public List<Grade> findAll();

    public List<Grade> searchGradeByCondition(String courseName, String term);

    public List<Grade> searchUserGrade(Integer user_id);

    public List<Grade> searchUserGradeByCondition(Integer user_id, String courseName, String term);

    void update(List<PGrade> pgrades);

    void updateGrades(String update_ids, String update_credits, String update_gpas, String update_grades);

    void arrangeGrade(Course course, List<User> students);

    Grade findByCourseAndStudent(Integer course_id, Integer student_id);

    public List<Grade> searchUserGrade(Integer user_id, String term);
}
