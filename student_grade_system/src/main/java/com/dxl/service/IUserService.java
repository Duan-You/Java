package com.dxl.service;

import com.dxl.model.User;
import com.dxl.pageModel.PUser;

import java.util.List;

public interface IUserService {

    public User save(User user);

    public User login(String id, String password);

    public void remove(String ids);

    public void update(User user);

    public User get(Integer id);

    List<User> searchByNameAndClass(String username, String user_class);

    List<User> searchByClass(String user_class);

    List<User> findByMajorAndClass(String major, String class_);

    List<User> findByUserId(String userId);
}
