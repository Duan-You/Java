package com.dxl.fc.service;

import com.dxl.fc.model.QuestionRecord;

import java.util.List;

public interface QuestionRecordService {

    List<QuestionRecord> getBystudntAndQuestioner(int student_id, String question);

    QuestionRecord getById(Integer questionId);

    void save(QuestionRecord questionRecord);
}
