package com.dxl.fc.dao;

import com.dxl.fc.model.CourseRecord;
import com.dxl.fc.model.CourseRecordExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRecordMapper {
    long countByExample(CourseRecordExample example);

    int deleteByExample(CourseRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseRecord record);

    int insertSelective(CourseRecord record);

    List<CourseRecord> selectByExample(CourseRecordExample example);

    CourseRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CourseRecord record, @Param("example") CourseRecordExample example);

    int updateByExample(@Param("record") CourseRecord record, @Param("example") CourseRecordExample example);

    int updateByPrimaryKeySelective(CourseRecord record);

    int updateByPrimaryKey(CourseRecord record);
}