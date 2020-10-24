package com.dxl.fc.dao;

import com.dxl.fc.model.AnswerRecord;
import com.dxl.fc.model.AnswerRecordExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRecordMapper {
    long countByExample(AnswerRecordExample example);

    int deleteByExample(AnswerRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AnswerRecord record);

    int insertSelective(AnswerRecord record);

    List<AnswerRecord> selectByExample(AnswerRecordExample example);

    AnswerRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AnswerRecord record, @Param("example") AnswerRecordExample example);

    int updateByExample(@Param("record") AnswerRecord record, @Param("example") AnswerRecordExample example);

    int updateByPrimaryKeySelective(AnswerRecord record);

    int updateByPrimaryKey(AnswerRecord record);
}