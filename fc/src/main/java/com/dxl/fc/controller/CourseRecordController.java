package com.dxl.fc.controller;

import com.dxl.fc.Utils.Msg;
import com.dxl.fc.model.*;
import com.dxl.fc.service.CourseRecordService;
import com.dxl.fc.service.CourseService;
import com.dxl.fc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/course_record")
public class CourseRecordController {

    @Autowired
    CourseRecordService courseRecordService;
    @Autowired
    CourseService courseService;
    @Autowired
    TeacherService teacherService;

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public Msg find(int count) {
        List<CourseRecord> courseRecords = courseRecordService.find(count);//获得courses
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ids.add(courseRecords.get(i).getCourseId());
        }

        List<Course> courses = courseService.getByIds(ids);
        for (int i = 0; i < count; i++) {
            ids.add(i, courseRecords.get(i).getTeacherId());
        }
        List<Teacher> teachers = teacherService.getByIds(ids);
        return Msg.success().add("courses", courses).add("courseRecords", courseRecords);
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String getHtml(@RequestParam("course_record_id") int course_record_id, @RequestParam("course_id") int course_id, @RequestParam("teacher_id") int teacher_id, Map<String, Object> map) {
        map.put("course", courseService.getById(course_id));
        map.put("teacher", teacherService.getById(teacher_id));
        map.put("course_record_id", course_record_id);
        map.put("resource_href", "/fc/resource/check?course_record_id=" + course_record_id);
        return "courseInfo";
    }

    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    @ResponseBody
    public Msg getData(@RequestParam("teacher_id") int teacher_id) {
        List<CourseRecord> courseRecords = courseRecordService.getByTeacher(teacher_id);
        List<CoursePage> coursePages = new ArrayList<>();
        for (int i = 0; i < courseRecords.size(); i++) {
            CoursePage coursePage = new CoursePage();


            Course course = courseService.getById(courseRecords.get(i).getCourseId());
            Teacher teacher = teacherService.getById(teacher_id);

            coursePage.setCourse_category(course.getCategory());
            coursePage.setCourse_id(course.getId());
            coursePage.setCourse_name(course.getName());
            coursePage.setCourse_point_system(course.getPointSystem());


            coursePage.setCourse_record_id(courseRecords.get(i).getId());
            coursePage.setCourse_school(course.getSchool());
            coursePage.setCourse_date(courseRecords.get(i).getRecordDate());


            coursePages.add(coursePage);
        }
        return Msg.success().add("coursePages", coursePages);
    }

    @RequestMapping(value = "/getByTeacher", method = RequestMethod.GET)
    @ResponseBody
    public Msg getByTeacher(@RequestParam("teacher_id") int teacher_id) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<CourseRecord> courseRecordList = courseRecordService.getByTeacher(teacher_id);

        for (int i = 0; i < courseRecordList.size(); i++) {
            Map<String, Object> data = new HashMap<>();


            Course course = courseService.getById(courseRecordList.get(i).getCourseId());
            data.put("course", course);
            data.put("courseRecord", courseRecordList.get(i));

            mapList.add(data);
        }
        return Msg.success().add("datas", mapList);

    }

}
