package com.dxl.action;

import com.dxl.model.Course;
import com.dxl.model.Grade;
import com.dxl.model.User;
import com.dxl.pageModel.PGrade;
import com.dxl.service.ICourseService;
import com.dxl.service.IGradeService;
import com.dxl.service.IUserService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Namespace("/grade")
public class GradeAction extends BaseAction {

    @Action(value = "add")
    public void add() {
        Course course = courseService.get(pgrade.getCourse_id());
        List<User> students = userService.findByMajorAndClass(pgrade.getMajor(), pgrade.getClass_());
        if (course == null || students.size() == 0) {
            writeJson("添加失败，核对课程与专业班级。",null);
            return;
        }
        System.out.println(students.size());
        for (int i = 0; i < students.size(); i++) {
            User user = students.get(i);
            System.out.println(user.getName());
            if (gradeService.findByCourseAndStudent(course.getId(), user.getId()) == null) {
                Grade temp = new Grade();
                temp.setProperty(grade.getProperty());
                temp.setWay(grade.getWay());
                temp.setComment(grade.getComment());
                temp.setCourse(course);
                temp.setDate(new Date());
                temp.setUser(user);
                temp.setTerm(pgrade.getTerm_year() + pgrade.getTerm_season());
                gradeService.save(temp);
            }
        }
        writeJson("课程安排成功。",null);
    }

    @Action(value = "delete")
    public void remove() {
        gradeService.remove(delete_ids);
    }

    @Action(value = "update")
    public void update() {
        gradeService.update(grade);
    }

    @Action(value = "update_grades")
    public void updateGrades() {
        gradeService.updateGrades(update_ids, update_credits, update_gpas, update_grades);
    }

    public void get() {
        gradeService.get(grade.getId());
    }

    @Action(value = "find")
    public void findAll() {
        writeJson(gradeService.findAll(), getCourseProfilter());
    }


    @Action(value = "search_grade")
    public void searchGradeByCondition() {
        writeJson(gradeService.searchGradeByCondition(getCourseName(), getTerm()), getCourseProfilter());
    }


    @Action(value = "user_search_grade")
    public void searchUserGradeByCondition() {
        if(courseName.equals("")){
            writeJson(gradeService.searchUserGrade(user_id,term), getCourseProfilter());
            return;
        }
        writeJson(gradeService.searchUserGradeByCondition(getUser_id(), getCourseName(), getTerm()), getCourseProfilter());
    }

    @Action(value = "user_grades")
    public void searchUserGrade() {
        writeJson(gradeService.searchUserGrade(getUser_id()), getCourseProfilter());
    }


    private String delete_ids;
    private String courseName;
    private String term;
    private Integer user_id;
    private Grade grade;
    private PGrade pgrade;
    private String update_ids;//录入成绩的传入数据，以，隔开
    private String update_grades;
    private String update_credits;
    private String update_gpas;
    private IGradeService gradeService;
    private ICourseService courseService;
    private IUserService userService;

    public IGradeService getGradeService() {
        return gradeService;
    }

    @Autowired
    public void setGradeService(IGradeService gradeService) {
        this.gradeService = gradeService;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }


    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getDelete_ids() {
        return delete_ids;
    }

    public void setDelete_ids(String delete_ids) {
        this.delete_ids = delete_ids;
    }

    public String getUpdate_ids() {
        return update_ids;
    }

    public void setUpdate_ids(String update_ids) {
        this.update_ids = update_ids;
    }

    public String getUpdate_grades() {
        return update_grades;
    }

    public void setUpdate_grades(String update_grades) {
        this.update_grades = update_grades;
    }

    public String getUpdate_credits() {
        return update_credits;
    }

    public void setUpdate_credits(String update_credits) {
        this.update_credits = update_credits;
    }

    public String getUpdate_gpas() {
        return update_gpas;
    }

    public void setUpdate_gpas(String update_gpas) {
        this.update_gpas = update_gpas;
    }

    public PGrade getPgrade() {
        return pgrade;
    }

    public void setPgrade(PGrade pgrade) {
        this.pgrade = pgrade;
    }

    public ICourseService getCourseService() {
        return courseService;
    }

    @Autowired
    public void setCourseService(ICourseService courseService) {
        this.courseService = courseService;
    }

    public IUserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
