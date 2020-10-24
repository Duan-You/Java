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

import java.util.*;

@Controller
@RequestMapping(value = "/work")
public class WorkController {


    @Autowired
    StudentService studentService;
    @Autowired
    WorkService workService;
    @Autowired
    WorkRecordService workRecordServiceService;
    @Autowired
    CourseService courseService;
    @Autowired
    CourseRecordService courseRecordService;
    @Autowired
    TeacherService teacherService;

    @RequestMapping(value = "/getDataByCourseRecordAndStudent", method = RequestMethod.GET)
    @ResponseBody
    public Msg getDataByCourseRecordAndStudent(@RequestParam("course_record_id") int course_record_id,@RequestParam("student_id") int student_id) {
        //学生查作业记录，作业记录--》作业信息--》课程记录信息--》教师和课程信息
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<Work> works = workService.getByCourseRecord(course_record_id);
        //学生，教师，课程，作业
        for (int i = 0; i < works.size(); i++) {
            Map<String, Object> data = new HashMap<>();
            data.put("work", works.get(i));
            Student student = studentService.getById(student_id);
            data.put("student",student);
            WorkRecord workRecord = workRecordServiceService.getDataByWorkAndStudent(works.get(i).getId(),student_id);
            CourseRecord courseRecord = courseRecordService.getById(course_record_id);
            data.put("courseRecord",courseRecord);
            Course course = courseService.getById(courseRecord.getCourseId());
            data.put("course",course);
            Teacher teacher = teacherService.getById(courseRecord.getTeacherId());
            data.put("teacher",teacher);
            mapList.add(data);
        }
        return Msg.success().add("datas", mapList);
    }


    @RequestMapping(value = "/getDataByCourseRecord", method = RequestMethod.GET)
    @ResponseBody
    public Msg getDataByCourseRecord(@RequestParam("course_record_id") int course_record_id) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<Work> works = workService.getByCourseRecord(course_record_id);
        //学生，教师，课程，作业
        for (int i = 0; i < works.size(); i++) {
            Map<String, Object> data = new HashMap<>();
            data.put("work", works.get(i));
            CourseRecord courseRecord = courseRecordService.getById(course_record_id);
            data.put("courseRecord",courseRecord);
            Course course = courseService.getById(courseRecord.getCourseId());
            data.put("course",course);
            Teacher teacher = teacherService.getById(courseRecord.getTeacherId());
            data.put("teacher",teacher);
            mapList.add(data);
        }
        return Msg.success().add("datas", mapList);
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Msg add(@RequestParam("course_record_id") int course_record_id,@RequestParam("start_date") Date start_date,@RequestParam("name") String name,@RequestParam("dead_date") Date dead_date) {

        Work work = new Work();
        work.setCourseRecordId(course_record_id);
        work.setDeadDate(dead_date);
        work.setName(name);
        work.setStartDate(start_date);
        workService.save(work);

        return Msg.success().add("msg", "成功。");
    }


}
