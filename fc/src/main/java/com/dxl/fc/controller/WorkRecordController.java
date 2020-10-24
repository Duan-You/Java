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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/work_record")
public class WorkRecordController {
    @Autowired
    WorkRecordService workRecordService;
    @Autowired
    StudentService studentService;
    @Autowired
    WorkService workService;
    @Autowired
    CourseService courseService;
    @Autowired
    CourseRecordService courseRecordService;
    @Autowired
    TeacherService teacherService;

    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    @ResponseBody
    public Msg getData(@RequestParam("student_id") int student_id) {
        //学生查作业记录，作业记录--》作业信息--》课程记录信息--》教师和课程信息
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<WorkRecord> workRecords = workRecordService.getByStudent(student_id);
        //学生，教师，课程，作业
        for (int i = 0; i < workRecords.size(); i++) {
            Map<String, Object> data = new HashMap<>();
            data.put("workRecord", workRecords.get(i));
            Student student = studentService.getById(student_id);
            data.put("student",student);
            Work work = workService.getById(workRecords.get(i).getWorkId());
            data.put("work",work);
            CourseRecord courseRecord = courseRecordService.getById(work.getCourseRecordId());
            data.put("courseRecord",courseRecord);
            Course course = courseService.getById(courseRecord.getCourseId());
            data.put("course",course);
            Teacher teacher = teacherService.getById(courseRecord.getTeacherId());
            data.put("teacher",teacher);
            mapList.add(data);
        }
        return Msg.success().add("datas", mapList);
    }
}
