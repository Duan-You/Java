package com.dxl.fc.service;

import com.dxl.fc.model.ResourceRecord;

import java.util.List;

public interface ResourceRecordService {
    List<ResourceRecord> getByStudent(int student_id);

    ResourceRecord getByResourseAndStudent(Integer id, int student_id);
}
