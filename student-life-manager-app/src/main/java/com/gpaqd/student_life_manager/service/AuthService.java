package com.gpaqd.student_life_manager.service;

import com.gpaqd.student_life_manager.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserService userService;

    @Autowired
    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public boolean authenticate(String username, String password) {
        User user = userService.findById(username);
        return (user != null && user.getPassword().equals(password));
    }

    public boolean registerUser(User newUser) {

        User existing = userService.findById(newUser.getUsername());

        if(existing != null) {
            return false;
        }

        userService.save(newUser);
        return true;
    }
}
