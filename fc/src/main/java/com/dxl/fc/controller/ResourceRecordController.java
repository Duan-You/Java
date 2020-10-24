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
@RequestMapping(value = "/resource_record")
public class ResourceRecordController {
    @Autowired
    ResourceRecordService resourceRecordService;
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
    @Autowired
    ResourceService resourceService;

    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    @ResponseBody
    public Msg getData(@RequestParam("student_id") int student_id) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<ResourceRecord>  resourceRecords =resourceRecordService.getByStudent(student_id);
        //学生，教师，课程，资料
        for (int i = 0; i < resourceRecords.size(); i++) {
            Map<String, Object> data = new HashMap<>();
            Student student = studentService.getById(student_id);
            data.put("student",student);
            Resource resource=resourceService.getById(resourceRecords.get(i).getResId());
            data.put("resource",resource);
            CourseRecord courseRecord = courseRecordService.getById(resource.getCourseRecordId());
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
