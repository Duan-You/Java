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
@RequestMapping(value = "/resource")
public class ResourceController {
    @Autowired
    ResourceService resourceService;

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
    ResourceRecordService resourceRecordService;

    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String getHtml(@RequestParam("course_record_id") int course_record_id, Map<String, Object> map) {
        map.put("course_record_id", course_record_id);
        return "resource";
    }

    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    @ResponseBody
    public Msg getData(@RequestParam("course_record_id") int course_record_id) {
        List<Resource> data = resourceService.getByCourseRecord(course_record_id);
        return Msg.success().add("data", data);
    }


    @RequestMapping(value = "/getDataByCourseRecordAndStudent", method = RequestMethod.GET)
    @ResponseBody
    public Msg getDataByCourseRecordAndStudent(@RequestParam("course_record_id") int course_record_id, @RequestParam("student_id") int student_id) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<Resource> resources = resourceService.getByCourseRecord(course_record_id);
        //学生，教师，课程，资料
        for (int i = 0; i < resources.size(); i++) {
            Map<String, Object> data = new HashMap<>();
            ResourceRecord resourceRecord = resourceRecordService.getByResourseAndStudent(resources.get(i).getId(), student_id);
            if (resourceRecord == null) {
                resourceRecord = new ResourceRecord();
                resourceRecord.setStatus("未下载");
            }
            data.put("resourceRecord", resourceRecord);
            Student student = studentService.getById(student_id);
            data.put("student", student);
            Resource resource = resources.get(i);
            data.put("resource", resource);
            CourseRecord courseRecord = courseRecordService.getById(course_record_id);
            data.put("courseRecord", courseRecord);
            Course course = courseService.getById(courseRecord.getCourseId());
            data.put("course", course);
            Teacher teacher = teacherService.getById(courseRecord.getTeacherId());
            data.put("teacher", teacher);
            mapList.add(data);
        }
        return Msg.success().add("datas", mapList);
    }

    @RequestMapping(value = "/getDataByCourseRecord", method = RequestMethod.GET)
    @ResponseBody
    public Msg getDataByCourseRecord(@RequestParam("course_record_id") int course_record_id) {
        List<Map<String, Object>> mapList = new ArrayList<>();
        List<Resource> resources = resourceService.getByCourseRecord(course_record_id);
        //课程，资料
        for (int i = 0; i < resources.size(); i++) {
            Map<String, Object> data = new HashMap<>();


            Resource resource = resources.get(i);
            data.put("resource", resource);
            CourseRecord courseRecord = courseRecordService.getById(course_record_id);
            data.put("courseRecord", courseRecord);
            Course course = courseService.getById(courseRecord.getCourseId());
            data.put("course", course);
            Teacher teacher = teacherService.getById(courseRecord.getTeacherId());
            data.put("teacher", teacher);
            mapList.add(data);
        }
        return Msg.success().add("datas", mapList);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Msg add(@RequestParam("course_record_id") int course_record_id, @RequestParam("category") String category, @RequestParam("name") String name) {

        Resource resource = new Resource();
        resource.setCategory(category);
        resource.setCourseRecordId(course_record_id);
        resource.setName(name);
        resource.setResDate(new Date());
        resourceService.save(resource);
        return Msg.success().add("msg", "成功。");
    }

    @RequestMapping(value = "/getChart", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Msg getChart(@RequestParam("school_id") int school_id) {

        List<Integer> datas = new ArrayList<>();
        List<String> labels = resourceService.getCategory();

        for (int i = 0; i < labels.size(); i++) {
            int count = resourceService.getCountByCategory(labels.get(i));
            datas.add(count);
        }


        return Msg.success().add("labels", labels).add("datas", datas);

    }


}
