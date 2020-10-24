package com.dxl.fc.controller;

import com.dxl.fc.Utils.Msg;
import com.dxl.fc.model.*;
import com.dxl.fc.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/learning_record")
public class LearningRecordController {

    @Autowired
    LearningRecordService learningRecordService;
    @Autowired
    CourseRecordService courseRecordService;
    @Autowired
    CourseService courseService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    @ResponseBody
    public Msg add(@RequestParam("course_record_id") int course_record_id, HttpSession session) {
        Student student = (Student) session.getAttribute("login_user");
        if (student == null) {
            return Msg.fail().add("msg", "同学，您尚未登录。");
        }
        LearningRecord record = learningRecordService.getBystudentAndCourseRecord(student.getId(), course_record_id);
        if (record != null) {
            return Msg.fail().add("msg", "同学，您已经参加了该课程。");
        }
        learningRecordService.add(student.getId(), course_record_id);
        return Msg.success().add("msg", "参加成功。");
    }

    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    @ResponseBody
    public Msg getData(@RequestParam("student_id") int student_id) {
        List<LearningRecord> learningRecords = learningRecordService.getByStudent(student_id);
        List<CoursePage> coursePages = new ArrayList<>();
        for (int i = 0; i < learningRecords.size(); i++) {
            CoursePage coursePage = new CoursePage();


            Integer courseRecordId = learningRecords.get(i).getCourseRecordId();
            CourseRecord courseRecord = courseRecordService.getById(courseRecordId);
            Course course = courseService.getById(courseRecord.getCourseId());
            Teacher teacher = teacherService.getById(courseRecord.getTeacherId());

            coursePage.setStudent_id(student_id);
            coursePage.setCourse_category(course.getCategory());
            coursePage.setCourse_id(course.getId());
            coursePage.setCourse_name(course.getName());
            coursePage.setCourse_point_system(course.getPointSystem());
            coursePage.setCourse_record_id(courseRecordId);
            coursePage.setCourse_school(course.getSchool());
            coursePage.setLearning_record_id(learningRecords.get(i).getId());

            coursePage.setLearning_time(learningRecords.get(i).getLearningTime());
            coursePage.setScore(learningRecords.get(i).getScore());
            coursePage.setTeacher_id(teacher.getId());
            coursePage.setTeacher_name(teacher.getName());
            coursePage.setTeacher_gender(teacher.getGender());
            coursePage.setTeacher_title(teacher.getTitle());

            coursePages.add(coursePage);
        }
        return Msg.success().add("coursePages", coursePages);
    }

    @RequestMapping(value = "/getStudents", method = RequestMethod.GET)
    @ResponseBody
    public Msg getStudents(@RequestParam("course_record_id") int course_record_id) {


        List<Map<String, Object>> mapList = new ArrayList<>();
        List<LearningRecord> learningRecords = learningRecordService.getByCourseRecord(course_record_id);

        for (int i = 0; i < learningRecords.size(); i++) {
            Map<String, Object> data = new HashMap<>();
            Student student = studentService.getById(learningRecords.get(i).getStudentId());
            CourseRecord courseRecord = courseRecordService.getById(course_record_id);
            Course course = courseService.getById(courseRecord.getCourseId());

            data.put("course",course);
            data.put("student",student);
            data.put("courseRecord",courseRecord);
            data.put("learningRecord",learningRecords.get(i));

            mapList.add(data);
        }
        return Msg.success().add("datas", mapList);
    }


    @RequestMapping(value = "/getChart", method = RequestMethod.GET)
    @ResponseBody
    public Msg getChart(@RequestParam("student_id") int student_id) {

        List<LearningRecord> learningRecords = learningRecordService.getByStudent(student_id);
        List<String> courses = new ArrayList<>();
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < learningRecords.size(); i++) {


            Integer courseRecordId = learningRecords.get(i).getCourseRecordId();
            CourseRecord courseRecord = courseRecordService.getById(courseRecordId);
            Course course = courseService.getById(courseRecord.getCourseId());
            courses.add(course.getName());
            if (learningRecords.get(i).getScore() == null || learningRecords.get(i).getScore().equals("")) {
                scores.add(0);
            } else {
                scores.add(Integer.parseInt(learningRecords.get(i).getScore()));
            }


        }
        return Msg.success().add("courses", courses).add("scores", scores);
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    @ResponseBody
    public Msg update(@RequestParam("learning_record_id") int learning_record_id,@RequestParam("score") String score) {


        LearningRecord learningRecord = learningRecordService.getById(learning_record_id);
        learningRecord.setScore(score);
        learningRecordService.update(learningRecord);

        return Msg.success().add("courses", "成功。");
    }


    @RequestMapping(value = "/getLineChart", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Msg getLineChart(@RequestParam("course_record_id") int course_record_id) {

        List<Integer> datas = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        List<LearningRecord> learningRecords = learningRecordService.getByCourseRecord(course_record_id);

        for (int i = 0; i < learningRecords.size(); i++) {
            LearningRecord learningRecord = learningRecords.get(i);
            Student student = studentService.getById(learningRecord.getStudentId());

            if(learningRecord.getScore()==null||learningRecord.getScore().equals("")){
                datas.add(0);
            }else{
                datas.add(Integer.parseInt(learningRecord.getScore()));
            }

            labels.add(student.getId()+student.getName());
        }


        return Msg.success().add("labels", labels).add("datas", datas);

    }

}
