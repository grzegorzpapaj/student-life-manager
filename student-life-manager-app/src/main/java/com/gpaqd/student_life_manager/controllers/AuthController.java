package com.gpaqd.student_life_manager.controllers;

import com.gpaqd.student_life_manager.entity.User;
import com.gpaqd.student_life_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "/auth/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        System.out.println("Checking if user login correct");
        return "redirect:/";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {

        model.addAttribute("newUser", new User());
        return "/auth/register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("newUser") User newUser, Model model) {

        User existing = userService.findById(newUser.getUsername());

        if(existing != null) {
            model.addAttribute("error", "Username already taken!");
            return "/auth/register";
        }

        User savedUser = userService.save(newUser);
        model.addAttribute("message", "User created: " + newUser.getUsername());
        return "redirect:/auth/login";
    }
}
