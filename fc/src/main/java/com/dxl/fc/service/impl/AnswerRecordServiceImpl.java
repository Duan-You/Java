package com.dxl.fc.service.impl;

import com.dxl.fc.dao.AnswerRecordMapper;
import com.dxl.fc.model.AnswerRecord;
import com.dxl.fc.model.AnswerRecordExample;
import com.dxl.fc.service.AnswerRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerRecordServiceImpl implements AnswerRecordService {
    @Autowired
    AnswerRecordMapper answerRecordMapper;



    @Override
    public AnswerRecord getByQuestion(Integer id) {
        AnswerRecordExample answerRecordExample = new AnswerRecordExample();
        answerRecordExample.setOrderByClause("answer_date desc");
        answerRecordExample.createCriteria().andQuestionIdEqualTo(id);
        List<AnswerRecord> answerRecords = answerRecordMapper.selectByExample(answerRecordExample);
        if(answerRecords==null||answerRecords.isEmpty())
            return null;
        return answerRecords.get(0);
    }

    @Override
    public List<AnswerRecord> getByStudentAndAnswerer(int student_id, String answerer) {
        AnswerRecordExample answerRecordExample = new AnswerRecordExample();
        answerRecordExample.setOrderByClause("answer_date desc");
        answerRecordExample.createCriteria().andStudentIdEqualTo(student_id).andAnswererEqualTo(answerer);
        return answerRecordMapper.selectByExample(answerRecordExample);
    }

    @Override
    public List<AnswerRecord> getByTeacherAndAnswerer(int teacher_id, String answerer) {
        AnswerRecordExample answerRecordExample = new AnswerRecordExample();
        answerRecordExample.setOrderByClause("answer_date desc");
        answerRecordExample.createCriteria().andTeacherIdEqualTo(teacher_id).andAnswererEqualTo(answerer);
        return answerRecordMapper.selectByExample(answerRecordExample);
    }

    @Override
    public void save(AnswerRecord answerRecord) {
        answerRecordMapper.insert(answerRecord);
    }
}
