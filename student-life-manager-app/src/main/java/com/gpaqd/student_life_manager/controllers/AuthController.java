package com.gpaqd.student_life_manager.controllers;

import com.gpaqd.student_life_manager.entity.User;
import com.gpaqd.student_life_manager.service.AuthService;
import com.gpaqd.student_life_manager.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private AuthService authService;

    @Autowired
    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "/auth/login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               Model model,
                               HttpSession session) {

        if(authService.authenticate(username, password)) {

            session.setAttribute("loggedInUser", username);
            return "redirect:/user/dashboard";
        } else {
            model.addAttribute("error", "Incorrect username or password. Please try again!");
            return "auth/login";
        }
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {

        model.addAttribute("newUser", new User());
        return "/auth/register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("newUser") User newUser, Model model) {

        if(authService.registerUser(newUser)) {
            model.addAttribute("message", "User created: " + newUser.getUsername());
            return "redirect:/auth/login";
        }
        model.addAttribute("error", "Username already taken!");
        return "/auth/register";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
