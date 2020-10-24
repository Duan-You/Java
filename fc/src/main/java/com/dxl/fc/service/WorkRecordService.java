package com.dxl.fc.service;

import com.dxl.fc.model.WorkRecord;

import java.util.List;

public interface WorkRecordService {
    List<WorkRecord> getByStudent(int student_id);

    WorkRecord getDataByWorkAndStudent(Integer id, int student_id);
}
