package com.dxl.fc.service;

import com.dxl.fc.model.Work;

import java.util.List;

public interface WorkService {
    Work getById(Integer workId);

    List<Work> getByCourseRecord(int course_record_id);

    void save(Work work);

    void update(Work work);
}
