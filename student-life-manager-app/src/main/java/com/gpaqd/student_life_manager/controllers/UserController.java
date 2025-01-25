package com.gpaqd.student_life_manager.controllers;


import com.gpaqd.student_life_manager.entity.User;
import com.gpaqd.student_life_manager.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");

        if (loggedInUser == null) {
            return "redirect:/auth/login";
        }

        User user = userService.findById(loggedInUser);
        model.addAttribute("user", user);

        return "/user/dashboard";
    }
}
