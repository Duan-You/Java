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
@RequestMapping(value = "/question_record")
public class QuestionRecordController {

    @Autowired
    QuestionRecordService questionRecordService;
    @Autowired
    AnswerRecordService answerRecordService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;


    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    @ResponseBody
    public Msg getData(@RequestParam("student_id") int student_id) {
        List<QuestionRecord> questionRecords = questionRecordService.getBystudntAndQuestioner(student_id, "学生");
        List<AnswerPage> answerPages = new ArrayList<>();
        for (int i = 0; i < questionRecords.size(); i++) {
            AnswerPage answerPage = new AnswerPage();

            AnswerRecord answerRecord = answerRecordService.getByQuestion(questionRecords.get(i).getId());
            if (answerRecord == null) {
                answerPage.setAnswer("尚未回答。");
                answerPage.setAnswer_id(null);
                answerPage.setAnswerDate(null);
            } else {
                answerPage.setAnswer(answerRecord.getAnswer());
                answerPage.setAnswer_id(answerRecord.getId());
                answerPage.setAnswerDate(answerRecord.getAnswerDate());
            }
            Teacher teacher = teacherService.getById(questionRecords.get(i).getTeacherId());
            answerPage.setTeacherId(teacher.getId());
            answerPage.setTeacher_name(teacher.getName());
            answerPage.setQuestion(questionRecords.get(i).getQuestion());
            answerPage.setQuestion_id(questionRecords.get(i).getId());
            answerPage.setQuestionDate(questionRecords.get(i).getQuestionDate());


            answerPages.add(answerPage);
        }
        return Msg.success().add("answerPages", answerPages);
    }

    @RequestMapping(value = "/getTeacherData", method = RequestMethod.GET)
    @ResponseBody
    public Msg getTeacherData(@RequestParam("teacher_id") int teacher_id) {
        List<QuestionRecord> questionRecords = questionRecordService.getBystudntAndQuestioner(teacher_id, "教师");
        List<AnswerPage> answerPages = new ArrayList<>();
        for (int i = 0; i < questionRecords.size(); i++) {
            AnswerPage answerPage = new AnswerPage();

            AnswerRecord answerRecord = answerRecordService.getByQuestion(questionRecords.get(i).getId());
            if (answerRecord == null) {
                answerPage.setAnswer("尚未回答。");
                answerPage.setAnswer_id(null);
                answerPage.setAnswerDate(null);
            } else {
                answerPage.setAnswer(answerRecord.getAnswer());
                answerPage.setAnswer_id(answerRecord.getId());
                answerPage.setAnswerDate(answerRecord.getAnswerDate());
            }
            Teacher teacher = teacherService.getById(questionRecords.get(i).getTeacherId());
            Student student= studentService.getById(questionRecords.get(i).getStudentId());
            answerPage.setStudentId(student.getId());
            answerPage.setStudent_gender(student.getGender());
            answerPage.setStudent_name(student.getName());

            answerPage.setTeacherId(teacher_id);
            answerPage.setTeacher_name(teacher.getName());
            answerPage.setQuestion(questionRecords.get(i).getQuestion());
            answerPage.setQuestion_id(questionRecords.get(i).getId());
            answerPage.setQuestionDate(questionRecords.get(i).getQuestionDate());


            answerPages.add(answerPage);
        }
        return Msg.success().add("answerPages", answerPages);
    }



    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Msg add(@RequestParam("teacher_id") int teacher_id,@RequestParam("student_id") int student_id,@RequestParam("question") String question) {
        QuestionRecord questionRecord = new QuestionRecord();
        questionRecord.setQuestion(question);
        questionRecord.setQuestioner("学生");
        questionRecord.setQuestionDate(new Date());
        questionRecord.setStudentId(student_id);
        questionRecord.setTeacherId(teacher_id);
        questionRecordService.save(questionRecord);
        return Msg.success().add("msg", "提问成功。");
    }
}
