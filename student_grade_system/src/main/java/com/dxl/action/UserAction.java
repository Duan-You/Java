package com.dxl.action;

import com.dxl.model.User;
import com.dxl.pageModel.PUser;
import com.dxl.service.IUserService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Namespace("/user")
public class UserAction extends BaseAction {


    @Action(value = "add")
    public void add() {
        List<User> byUserId = userService.findByUserId(user.getUserId());
        if (byUserId == null || byUserId.size() == 0) {
            user.setDate(new Date());
            userService.save(user);
            writeJson(user.getName() + "添加成功。", null);
        } else {
            writeJson("该用户已经存在。", null);
        }
    }


    @Action(value = "login", results = {
            @Result(name = "student", location = "/WEB-INF/page/admin_student.jsp"),
            @Result(name = "teacher", location = "/WEB-INF/page/admin_teacher.jsp"),
            @Result(name = "administrator", location = "/WEB-INF/page/admin_administrator.jsp"),
            @Result(name = "input", location = "/login.jsp")
    })
    public String login() {
        user = userService.login(user.getUserId(), user.getPassword());
        if (user != null) {
            Map session = ActionContext.getContext().getSession();
            session.put("loginUser", user);
            if (user.getUserClass().equals("学生")) {
                return "student";
            }
            if (user.getUserClass().equals("教师")) {
                return "teacher";
            }
            if (user.getUserClass().equals("管理员")) {
                return "administrator";
            }
        }
        return INPUT;
    }

    @Action(value = "delete")
    public void remove() {
        userService.remove(delete_ids);
    }

    @Action(value = "update")
    public void update() {
        user.setDate(new Date());
        userService.update(user);
        writeJson(user.getName() + "修改成功。", null);
    }

    @Action(value = "logout", results = {
            @Result(name = "success", location = "/login.jsp")
    })
    public String logout() {
        Map session = ActionContext.getContext().getSession();
        session.remove("loginUser");
        return SUCCESS;
    }

    @Action(value = "search_user")
    public void searchUser() {
        if (username.equals("") || username == null) {
            writeJson(userService.searchByClass(user_class), null);
        } else {
            writeJson(userService.searchByNameAndClass(username, user_class), null);
        }
    }


    private User user = new User();
    private static final Logger logger = Logger.getLogger(UserAction.class);
    private IUserService userService;
    private String user_class;
    private String username;
    private String delete_ids;

    public String getUser_class() {
        return user_class;
    }

    public void setUser_class(String user_class) {
        this.user_class = user_class;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public IUserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDelete_ids() {
        return delete_ids;
    }

    public void setDelete_ids(String delete_ids) {
        this.delete_ids = delete_ids;
    }
}
