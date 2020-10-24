package com.dxl.action;

import com.dxl.model.Course;
import com.dxl.model.User;
import com.dxl.service.ICourseService;
import com.dxl.service.IUserService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Namespace("/course")
public class CourseAction extends BaseAction {

    @Action(value = "delete")
    public void remove() {
        courseService.remove(delete_ids);
    }

    @Action(value = "update")
    public void update() {
        courseService.update(course);
        writeJson(course.getName() + "修改成功。", null);
    }

    @Action(value = "add")
    public void add() {
        List<Course> byName = courseService.findByName(course.getName());
        if (byName == null || byName.size() == 0) {
            courseService.save(course);
            writeJson(course.getName() + "添加成功。", null);
        } else {
            writeJson("该课程已存在。", null);
        }
    }


    @Action(value = "find_teachedCourse")
    public void teacherSearchByCondition() {
        writeJson(courseService.findTeachedCourse(getUser_id()), getCourseProfilter());
    }

    @Action(value = "search_course")
    public void searchByCondition() {
        if (course_name.equals("") && course_class.equals("")) {//类型 名称都空，查所有
            writeJson(courseService.findAll(), null);
            return;
        }
        if (course_name.equals("") && !course_class.equals("")) {//名称空，类型不空，查类型
            writeJson(courseService.findByClass(course_class), null);
            return;
        }//其余，课程不空，都查课程
        writeJson(courseService.findByName(course_name), null);
        return;
    }

    @Action(value = "arrange_course")
    public void arrangeCourse() {
        String[] course_id = course_ids.split(",");
        String[] teacher_id = teacher_ids.split(",");
        Integer[] courseIds = new Integer[teacher_id.length];
        Integer[] teacherIds = new Integer[teacher_id.length];
        for (int i = 0; i < course_id.length; i++) {
            courseIds[i] = Integer.parseInt(course_id[i]);
            teacherIds[i] = Integer.parseInt(teacher_id[i]);
            User teacher = userService.get(teacherIds[i]);
            if (teacher == null) {
                writeJson("教师编号不存在。", null);
            }
            courseService.arrangeCourse(courseIds[i], teacher);
            writeJson(teacherIds[i] + "添加成功。", null);
        }
    }


    private Integer user_id;
    private String course_name;
    private ICourseService courseService;
    private IUserService userService;
    private String course_class;
    private String teacher_ids;
    private String majors;
    private String class_s;
    private String course_ids;
    private String delete_ids;
    private Course course;

    public IUserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public ICourseService getCourseService() {
        return courseService;
    }

    @Autowired
    public void setCourseService(ICourseService courseService) {
        this.courseService = courseService;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_class() {
        return course_class;
    }

    public void setCourse_class(String course_class) {
        this.course_class = course_class;
    }

    public String getTeacher_ids() {
        return teacher_ids;
    }

    public void setTeacher_ids(String teacher_ids) {
        this.teacher_ids = teacher_ids;
    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public String getClass_s() {
        return class_s;
    }

    public void setClass_s(String class_s) {
        this.class_s = class_s;
    }

    public String getCourse_ids() {
        return course_ids;
    }

    public void setCourse_ids(String course_ids) {
        this.course_ids = course_ids;
    }

    public String getDelete_ids() {
        return delete_ids;
    }

    public void setDelete_ids(String delete_ids) {
        this.delete_ids = delete_ids;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
