package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(String id);

    User save(User user);

    void deleteById(String id);
}
