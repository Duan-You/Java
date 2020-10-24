package com.dxl.fc.dao;

import com.dxl.fc.model.QuestionRecord;
import com.dxl.fc.model.QuestionRecordExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRecordMapper {
    long countByExample(QuestionRecordExample example);

    int deleteByExample(QuestionRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(QuestionRecord record);

    int insertSelective(QuestionRecord record);

    List<QuestionRecord> selectByExample(QuestionRecordExample example);

    QuestionRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") QuestionRecord record, @Param("example") QuestionRecordExample example);

    int updateByExample(@Param("record") QuestionRecord record, @Param("example") QuestionRecordExample example);

    int updateByPrimaryKeySelective(QuestionRecord record);

    int updateByPrimaryKey(QuestionRecord record);
}