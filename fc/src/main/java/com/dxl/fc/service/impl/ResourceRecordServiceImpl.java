package com.dxl.fc.service.impl;

import com.dxl.fc.dao.ResourceRecordMapper;
import com.dxl.fc.model.ResourceRecord;
import com.dxl.fc.model.ResourceRecordExample;
import com.dxl.fc.service.ResourceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceRecordServiceImpl implements ResourceRecordService {
    @Autowired
    ResourceRecordMapper resourceRecordMapper;

    @Override
    public List<ResourceRecord> getByStudent(int student_id) {
        ResourceRecordExample resourceRecordExample = new ResourceRecordExample();
        resourceRecordExample.createCriteria().andStudentIdEqualTo(student_id);
        return resourceRecordMapper.selectByExample(resourceRecordExample);
    }

    @Override
    public ResourceRecord getByResourseAndStudent(Integer id, int student_id) {
        ResourceRecordExample resourceRecordExample = new ResourceRecordExample();
        resourceRecordExample.createCriteria().andStudentIdEqualTo(student_id).andResIdEqualTo(id);
        List<ResourceRecord> resourceRecords = resourceRecordMapper.selectByExample(resourceRecordExample);
        if (resourceRecords==null||resourceRecords.isEmpty()){
            return null;
        }
        return resourceRecords.get(0);
    }
}
