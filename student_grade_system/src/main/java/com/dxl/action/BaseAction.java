package com.dxl.action;

import com.alibaba.fastjson.serializer.PropertyFilter;
import com.dxl.model.Course;
import com.dxl.util.FastJSONUtil;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import java.io.IOException;

@ParentPackage("basePackage")
@Namespace("/")
public class BaseAction extends ActionSupport {

    public void writeJson(Object object, PropertyFilter profilter) {
        try {
            String json;
            if (profilter == null) {
                json = FastJSONUtil.toJSONString(object);
            } else {
                json = FastJSONUtil.toJSONString(object, profilter);
            }
            ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
            ServletActionContext.getResponse().getWriter().write(json);
            ServletActionContext.getResponse().getWriter().flush();
            ServletActionContext.getResponse().getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public PropertyFilter getCourseProfilter() {
        PropertyFilter profilter = new PropertyFilter() {
            @Override
            public boolean apply(Object object, String name, Object value) {
                if (object.getClass().equals(Course.class) && name.equals("user")) {
                    return false;
                } else {
                    return true;
                }
            }
        };
        return profilter;
    }

}
