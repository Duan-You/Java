package com.dxl.fc.service;

import com.dxl.fc.model.Resource;

import java.util.List;

public interface ResourceService {
    List<Resource> getByCourseRecord(int course_record_id);

    Resource getById(Integer resId);

    void save(Resource resource);

    void update(Resource resource);

    List<String> getCategory();

    int getCountByCategory(String s);
}
