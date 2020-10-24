package com.dxl.fc.controller;

import com.dxl.fc.Utils.Msg;
import com.dxl.fc.model.*;
import com.dxl.fc.service.AnswerRecordService;
import com.dxl.fc.service.QuestionRecordService;
import com.dxl.fc.service.StudentService;
import com.dxl.fc.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/answer_record")
public class AnswerRecordController {
    @Autowired
    AnswerRecordService answerRecordService;
    @Autowired
    QuestionRecordService questionRecordService;

    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;


    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    @ResponseBody
    public Msg getData(@RequestParam("student_id") int student_id) {
        List<AnswerRecord> answerRecords = answerRecordService.getByStudentAndAnswerer(student_id, "学生");
        List<AnswerPage> answerPages = new ArrayList<>();
        for (int i = 0; i < answerRecords.size(); i++) {
            AnswerPage answerPage = new AnswerPage();
            QuestionRecord questionRecord = questionRecordService.getById(answerRecords.get(i).getQuestionId());


            answerPage.setAnswer(answerRecords.get(i).getAnswer());
            answerPage.setAnswer_id(answerRecords.get(i).getId());
            answerPage.setAnswerDate(answerRecords.get(i).getAnswerDate());

            Teacher teacher = teacherService.getById(answerRecords.get(i).getTeacherId());
            answerPage.setTeacherId(teacher.getId());
            answerPage.setTeacher_name(teacher.getName());
            answerPage.setQuestion(questionRecord.getQuestion());
            answerPage.setQuestion_id(questionRecord.getId());
            answerPage.setQuestionDate(questionRecord.getQuestionDate());


            answerPages.add(answerPage);
        }
        return Msg.success().add("answerPages", answerPages);
    }

    @RequestMapping(value = "/getTeacherData", method = RequestMethod.GET)
    @ResponseBody
    public Msg getTeacherData(@RequestParam("teacher_id") int teacher_id) {
        List<AnswerRecord> answerRecords = answerRecordService.getByTeacherAndAnswerer(teacher_id, "学生");
        List<AnswerPage> answerPages = new ArrayList<>();
        for (int i = 0; i < answerRecords.size(); i++) {
            AnswerPage answerPage = new AnswerPage();
            QuestionRecord questionRecord = questionRecordService.getById(answerRecords.get(i).getQuestionId());


            answerPage.setAnswer(answerRecords.get(i).getAnswer());
            answerPage.setAnswer_id(answerRecords.get(i).getId());
            answerPage.setAnswerDate(answerRecords.get(i).getAnswerDate());
            Student student= studentService.getById(answerRecords.get(i).getStudentId());
            answerPage.setStudentId(student.getId());
            answerPage.setStudent_gender(student.getGender());
            answerPage.setStudent_name(student.getName());

            Teacher teacher = teacherService.getById(teacher_id);
            answerPage.setTeacherId(teacher.getId());
            answerPage.setTeacher_name(teacher.getName());
            answerPage.setQuestion(questionRecord.getQuestion());
            answerPage.setQuestion_id(questionRecord.getId());
            answerPage.setQuestionDate(questionRecord.getQuestionDate());


            answerPages.add(answerPage);
        }
        return Msg.success().add("answerPages", answerPages);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Msg add(@RequestParam("question_id") int question_id,@RequestParam("teacher_id") int teacher_id,@RequestParam("student_id") int student_id,@RequestParam("answer") String answer) {
        AnswerRecord answerRecord = new AnswerRecord();
        answerRecord.setAnswer(answer);
        answerRecord.setAnswerDate(new Date());
        answerRecord.setAnswerer("学生");
        answerRecord.setQuestionId(question_id);
        answerRecord.setStudentId(student_id);
        answerRecord.setTeacherId(teacher_id);

        answerRecordService.save(answerRecord);
        return Msg.success().add("msg", "回答提交成功。");
    }

}
