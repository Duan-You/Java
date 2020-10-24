package com.dxl.service.impl;

import com.dxl.dao.IBaseDao;
import com.dxl.model.User;
import com.dxl.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);
    private IBaseDao<User> baseDao;

    public IBaseDao<User> getBaseDao() {
        return baseDao;
    }

    @Autowired
    public void setBaseDao(IBaseDao<User> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public User save(User user) {
        baseDao.save(user);
        return user;
    }

    @Override
    public User login(String id, String password) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("password", password);
        User user = baseDao.get("from User u where u.userId = :id and u.password = :password", params);
        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public void remove(String ids) {
        baseDao.executeHql("delete from User u where u.id in(" + ids + ")");
    }

    @Override
    public void update(User user) {
        baseDao.update(user);
    }

    @Override
    public User get(Integer id) {
        return baseDao.get(User.class, id);
    }

    @Override
    public List<User> searchByNameAndClass(String username, String user_class) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);
        params.put("user_class", user_class);
        return baseDao.find("from User u where u.name = :username and u.userClass = :user_class", params);
    }

    @Override
    public List<User> searchByClass(String user_class) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_class", user_class);
        return baseDao.find("from User u where u.userClass = :user_class", params);
    }

    @Override
    public List<User> findByMajorAndClass(String major, String class_) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("major", major);
        params.put("class_", class_);
        return baseDao.find("from User u where u.major = :major and u.class_ = :class_", params);
    }

    @Override
    public List<User> findByUserId(String userId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        return baseDao.find("from User u where u.userId = :userId", params);
    }

}
