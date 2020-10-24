package com.dxl.fc.service.impl;

import com.dxl.fc.dao.SchoolMapper;
import com.dxl.fc.model.School;
import com.dxl.fc.model.SchoolExample;
import com.dxl.fc.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    SchoolMapper schoolMapper;

    @Override
    public School login(String cell, String password) {
        SchoolExample schoolExample = new SchoolExample();
        schoolExample.createCriteria().andCellEqualTo(cell).andPasswordEqualTo(password);
        List<School> schoolList = schoolMapper.selectByExample(schoolExample);
        if (schoolList.isEmpty()|| schoolList.get(0) == null)
            return null;
        return schoolList.get(0);
    }

    @Override
    public boolean existCell(String cell) {
        SchoolExample schoolExample = new SchoolExample();
        schoolExample.createCriteria().andCellEqualTo(cell);
        List<School> schoolList = schoolMapper.selectByExample(schoolExample);
        if (schoolList.isEmpty()|| schoolList.get(0) == null)
            return false;
        return true;
    }

    @Override
    public void save(School school) {
        schoolMapper.insert(school);
    }

    @Override
    public School getById(int school_id) {
        return schoolMapper.selectByPrimaryKey(school_id);
    }
}
