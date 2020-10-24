package com.dxl.fc.service.impl;

import com.dxl.fc.dao.QuestionRecordMapper;
import com.dxl.fc.model.QuestionRecord;
import com.dxl.fc.model.QuestionRecordExample;
import com.dxl.fc.service.QuestionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionRecordServiceImpl implements QuestionRecordService {
    @Autowired
    QuestionRecordMapper questionRecordMapper;


    @Override
    public List<QuestionRecord> getBystudntAndQuestioner(int student_id, String questioner) {
        QuestionRecordExample questionRecordExample =new QuestionRecordExample();
        questionRecordExample.setOrderByClause("question_date desc");
        questionRecordExample.createCriteria().andStudentIdEqualTo(student_id).andQuestionerEqualTo(questioner);
        return questionRecordMapper.selectByExample(questionRecordExample);
    }

    @Override
    public QuestionRecord getById(Integer questionId) {
        return questionRecordMapper.selectByPrimaryKey(questionId);
    }

    @Override
    public void save(QuestionRecord questionRecord) {
        questionRecordMapper.insert(questionRecord);
    }
}
