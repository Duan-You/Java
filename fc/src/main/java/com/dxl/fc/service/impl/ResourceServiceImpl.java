package com.dxl.fc.service.impl;

import com.dxl.fc.dao.ResourceMapper;
import com.dxl.fc.model.Resource;
import com.dxl.fc.model.ResourceExample;
import com.dxl.fc.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    ResourceMapper resourceMapper;


    
    @Override
    public List<Resource> getByCourseRecord(int course_record_id) {
        ResourceExample resourceExample = new ResourceExample();
        resourceExample.setOrderByClause("res_date desc");
        resourceExample.createCriteria().andCourseRecordIdEqualTo(course_record_id);
        return resourceMapper.selectByExample(resourceExample);
    }

    @Override
    public Resource getById(Integer resId) {
        return resourceMapper.selectByPrimaryKey(resId);
    }

    @Override
    public void save(Resource resource) {

        resourceMapper.insert(resource);
    }

    @Override
    public void update(Resource resource) {
        resourceMapper.updateByPrimaryKeySelective(resource);
    }

    @Override
    public List<String> getCategory() {
        return resourceMapper.getCategory();
    }

    @Override
    public int getCountByCategory(String s) {
        ResourceExample resourceExample = new ResourceExample();
        resourceExample.createCriteria().andCategoryEqualTo(s);
        return (int) resourceMapper.countByExample(resourceExample);
    }

}
