package com.dxl.fc.service;

import com.dxl.fc.model.School;

public interface SchoolService {
    School login(String cell, String password);

    boolean existCell(String cell);

    void save(School school);

    School getById(int school_id);
}
