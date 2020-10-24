package com.dxl.fc.controller;


import com.dxl.fc.Utils.Msg;
import com.dxl.fc.model.Student;
import com.dxl.fc.service.StudentService;
import com.dxl.fc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Msg register(@RequestBody Student student) {
        System.out.printf(student.getCell());
        //查询号码是否已经存在
        if (studentService.existCell(student.getCell())) {
            return Msg.fail().add("cell", "手机号已经存在。");
        }
        //注册学生信息
        studentService.save(student);
        return Msg.success().add("student", student);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Msg login(@RequestParam("cell") String cell,
                        @RequestParam("password") String password,
                         HttpSession session) {
        Student student = studentService.login(cell, password);
        if (student == null) {
            return Msg.fail().add("msg","手机号或密码错误");
        }
        session.setAttribute("login_user", student);
        session.setAttribute("myInfoHref", "/fc/student/info");
        return Msg.success().add("login_student",student);

    }

    @RequestMapping(value = "/getChart", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Msg getChart(@RequestParam("school_id") int school_id) {

        List<String> labels = new ArrayList<>();
        List<Integer> datas = new ArrayList<>();
        int count = studentService.getCount();
        int count2 = teacherService.getCount();
        datas.add(count);
        datas.add(count2);
        labels.add("学生人数");
        labels.add("教师人数");

        return Msg.success().add("labels",labels).add("datas",datas);

    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String getInfoHtml() {
        return "student_info";
    }
}
