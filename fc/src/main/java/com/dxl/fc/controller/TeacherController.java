package com.dxl.fc.controller;

import com.dxl.fc.Utils.Msg;
import com.dxl.fc.model.*;
import com.dxl.fc.service.SchoolService;
import com.dxl.fc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    SchoolService schoolService;

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Msg register(@RequestBody Teacher teacher) {
        //查询号码是否已经存在
        if (teacherService.existCell(teacher.getCell())) {
            return Msg.fail().add("cell", "手机号已经存在。");
        }
        //注册学生信息
        teacherService.save(teacher);
        return Msg.success().add("teacher", teacher);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Msg login(@RequestParam("cell") String cell,
                     @RequestParam("password") String password,
                     HttpSession session) {
        Teacher teacher = teacherService.login(cell, password);
        if (teacher == null) {
            return Msg.fail().add("msg","手机号或密码错误");
        }
        session.setAttribute("login_user", teacher);
        session.setAttribute("myInfoHref", "/fc/teacher/info");
        return Msg.success().add("login_teacher",teacher);

    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String getInfoHtml() {
        return "admin_teacher";
    }

    @RequestMapping(value = "/getDataBySchool", method = RequestMethod.GET)
    @ResponseBody
    public Msg getDataBySchool(@RequestParam("school_id") int school_id) {

        List<Map<String, Object>> mapList = new ArrayList<>();
        List<Teacher> teachers = teacherService.getBySchool(school_id);
        for (int i = 0; i < teachers.size(); i++) {
            Map<String, Object> data = new HashMap<>();
            data.put("teacher",teachers.get(i));
            School school = schoolService.getById(school_id);
            data.put("school",school);
            mapList.add(data);
        }

        return Msg.success().add("datas",mapList);

    }

}
