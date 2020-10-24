package com.dxl.fc.service;

import com.dxl.fc.model.AnswerRecord;

import java.util.List;

public interface AnswerRecordService {

    AnswerRecord getByQuestion(Integer id);

    List<AnswerRecord> getByStudentAndAnswerer(int student_id, String answerer);

    List<AnswerRecord> getByTeacherAndAnswerer(int teacher_id, String answerer);

    void save(AnswerRecord answerRecord);
}
