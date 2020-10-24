package com.dxl.fc.controller;

import com.dxl.fc.Utils.Msg;
import com.dxl.fc.model.Course;
import com.dxl.fc.model.CourseRecord;
import com.dxl.fc.model.Teacher;
import com.dxl.fc.service.CourseRecordService;
import com.dxl.fc.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/course")
public class CourseController {

    @Autowired
    CourseService courseService;
    @Autowired
    CourseRecordService courseRecordService;

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public Msg find(int count) {
        List<Course> courses = courseService.find(count);//获得courses
        return Msg.success().add("courses", courses);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Msg add(Course course, HttpSession session) {
        System.out.println(course.getName());
        course.setSchool("陕西理工大学");
        courseService.save(course);
        Course byCourse = courseService.getByCourse(course);
        CourseRecord courseRecord = new CourseRecord();
        courseRecord.setRecordDate(new Date());
        Teacher login_user = (Teacher) session.getAttribute("login_user");
        courseRecord.setTeacherId(login_user.getId());
        courseRecord.setCourseId(byCourse.getId());
        courseRecordService.save(courseRecord);
        return Msg.success().add("msg", "开课成功。");
    }

    @RequestMapping(value = "/getChart", method = RequestMethod.GET)
    @ResponseBody
    public Msg getChart(@RequestParam("school_id") int school_id) {

        List<Integer> datas = new ArrayList<>();
        List<String> labels = courseService.getCategory();

        for (int i = 0; i < labels.size(); i++) {
            int count = courseService.getCountByCategory(labels.get(i));
            datas.add(count);
        }


        return Msg.success().add("labels", labels).add("datas", datas);

    }


}
