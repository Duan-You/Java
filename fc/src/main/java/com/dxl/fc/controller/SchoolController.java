package com.dxl.fc.controller;

import com.dxl.fc.Utils.Msg;
import com.dxl.fc.model.School;
import com.dxl.fc.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/school")
public class SchoolController {

    @Autowired
    SchoolService schoolService;


    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Msg register(@RequestBody School school) {
        //查询号码是否已经存在
        if (schoolService.existCell(school.getCell())) {
            return Msg.fail().add("cell", "手机号已经存在。");
        }
        //注册学生信息
        schoolService.save(school);
        return Msg.success().add("school", school);
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public Msg login(@RequestParam("cell") String cell,
                     @RequestParam("password") String password,
                     HttpSession session) {
        School school = schoolService.login(cell, password);
        if (school == null) {
            return Msg.fail().add("msg","手机号或密码错误");
        }
        session.setAttribute("login_user", school);
        session.setAttribute("myInfoHref", "/fc/school/info");
        return Msg.success().add("login_school",school);

    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String getInfoHtml() {
        return "administrator";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("login_user");
        httpSession.removeAttribute("myInfoHref");
        return "redirect:/";
    }
}
