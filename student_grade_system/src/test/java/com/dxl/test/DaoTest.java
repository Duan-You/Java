package com.dxl.test;

import com.dxl.model.Course;
import com.dxl.model.Grade;
import com.dxl.model.User;
import com.dxl.service.ICourseService;
import com.dxl.service.IGradeService;
import com.dxl.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class DaoTest {


    @Test
    public void test() {
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml", "classpath:spring-hibernate.xml"});
        IUserService userService = (IUserService) ac.getBean("userService");
        ICourseService courseService = (ICourseService) ac.getBean("courseService");
        IGradeService gradeService = (IGradeService) ac.getBean("gradeService");

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUserId("062"+i);
            user.setPassword("123123");
            user.setName("段小龙");
            user.setNation("回");
            user.setMajor("计算机科学与技术");
//            user.setUserClass("学生");
//            user.setClass_("1603");
//            user.setUserClass("教师");
            user.setUserClass("管理员");
            user.setInstitute("数学与计算机科学学院");
            user.setDate(new Date());
            user.setGender("男");
            userService.save(user);
        }

    }

    @Test
    public void test1() {
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml", "classpath:spring-hibernate.xml"});
        IUserService userService = (IUserService) ac.getBean("userService");
        ICourseService courseService = (ICourseService) ac.getBean("courseService");
        IGradeService gradeService = (IGradeService) ac.getBean("gradeService");
//        for (int i = 1; i <= 10; i++) {
//            Course course = new Course();
//            course.setClass_("数学");
//            course.setCredit("4");
//            course.setGpa("4");
//            course.setName("高等数学"+i);
//            course.setUser(userService.get(17));
//            courseService.save(course);
//        }
        Course course = new Course();
        course.setClass_("计算机");
        course.setCredit("4");
        course.setGpa("4");
        course.setName("数据结构");
        course.setUser(userService.get(17));
        courseService.save(course);
    }

    @Test
    public void test2() {
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml", "classpath:spring-hibernate.xml"});
        IUserService userService = (IUserService) ac.getBean("userService");
        ICourseService courseService = (ICourseService) ac.getBean("courseService");
        IGradeService gradeService = (IGradeService) ac.getBean("gradeService");
        for (int i = 1; i <= 10; i++) {
            Grade grade = new Grade();
//            grade.setCredit("3");
//            grade.setGrade("90");
//            grade.setComment("优秀");
            grade.setDate(new Date());
            grade.setTerm("大一第一学期");
//            grade.setGpa("3");
            grade.setWay("考试");
            grade.setProperty("初修");
            Course course = courseService.get(12);
            grade.setCourse(course);
            grade.setUser(userService.get(i));
            gradeService.save(grade);
        }
    }

    @Test
    public void test3() {
        ApplicationContext ac = new ClassPathXmlApplicationContext(new String[]{"classpath:spring.xml", "classpath:spring-hibernate.xml"});
        IUserService userService = (IUserService) ac.getBean("userService");
        ICourseService courseService = (ICourseService) ac.getBean("courseService");
        IGradeService gradeService = (IGradeService) ac.getBean("gradeService");
        Grade grade = gradeService.findByCourseAndStudent(1,1);
        if (gradeService.findByCourseAndStudent(1,111) == null) {
            System.out.println("hello");
        }else{
            System.out.println(grade.getId());
        }


    }

}
