package com.dxl.fc.service.impl;

import com.dxl.fc.dao.WorkRecordMapper;
import com.dxl.fc.model.WorkRecord;
import com.dxl.fc.model.WorkRecordExample;
import com.dxl.fc.service.WorkRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkRecordServiceImpl implements WorkRecordService {

    @Autowired
    WorkRecordMapper workRecordMapper;

    @Override
    public List<WorkRecord> getByStudent(int student_id) {
        WorkRecordExample workRecordExample = new WorkRecordExample();
        workRecordExample.createCriteria().andStudentIdEqualTo(student_id);
        return workRecordMapper.selectByExample(workRecordExample);
    }

    @Override
    public WorkRecord getDataByWorkAndStudent(Integer id, int student_id) {
        WorkRecordExample workRecordExample = new WorkRecordExample();
        workRecordExample.createCriteria().andStudentIdEqualTo(student_id).andWorkIdEqualTo(id);
        List<WorkRecord> workRecords = workRecordMapper.selectByExample(workRecordExample);
        if (workRecords == null || workRecords.isEmpty())
            return null;
        else return workRecords.get(0);
    }
}
