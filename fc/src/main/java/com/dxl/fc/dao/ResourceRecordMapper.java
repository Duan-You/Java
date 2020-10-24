package com.dxl.fc.dao;

import com.dxl.fc.model.ResourceRecord;
import com.dxl.fc.model.ResourceRecordExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRecordMapper {
    long countByExample(ResourceRecordExample example);

    int deleteByExample(ResourceRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ResourceRecord record);

    int insertSelective(ResourceRecord record);

    List<ResourceRecord> selectByExample(ResourceRecordExample example);

    ResourceRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ResourceRecord record, @Param("example") ResourceRecordExample example);

    int updateByExample(@Param("record") ResourceRecord record, @Param("example") ResourceRecordExample example);

    int updateByPrimaryKeySelective(ResourceRecord record);

    int updateByPrimaryKey(ResourceRecord record);
}