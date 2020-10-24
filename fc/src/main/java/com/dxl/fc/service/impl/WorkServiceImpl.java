package com.dxl.fc.service.impl;

import com.dxl.fc.dao.WorkMapper;
import com.dxl.fc.model.Work;
import com.dxl.fc.model.WorkExample;
import com.dxl.fc.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
    WorkMapper workMapper;

    @Override
    public Work getById(Integer workId) {
        return workMapper.selectByPrimaryKey(workId);
    }

    @Override
    public List<Work> getByCourseRecord(int course_record_id) {
        WorkExample workExample = new WorkExample();
        workExample.setOrderByClause("start_date desc");
        workExample.createCriteria().andCourseRecordIdEqualTo(course_record_id);
        return workMapper.selectByExample(workExample);
    }

    @Override
    public void save(Work work) {
        workMapper.insert(work);
    }

    @Override
    public void update(Work work) {
        workMapper.updateByPrimaryKeySelective(work);
    }
}
