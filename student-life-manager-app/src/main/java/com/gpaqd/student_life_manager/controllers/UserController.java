package com.gpaqd.student_life_manager.controllers;


import com.gpaqd.student_life_manager.entity.Course;
import com.gpaqd.student_life_manager.entity.User;
import com.gpaqd.student_life_manager.entity.pk.CourseId;
import com.gpaqd.student_life_manager.service.CourseService;
import com.gpaqd.student_life_manager.service.CourseServiceImpl;
import com.gpaqd.student_life_manager.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final CourseService courseService;

    @Autowired
    public UserController(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
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

    @GetMapping("/courses")
    public String showCourses(Model model, HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        if(loggedInUser == null) {
            return "redirect:/auth/login";
        }

        List<Course> courses = courseService.findAllByUser(loggedInUser);
        model.addAttribute("courses", courses);

        return "/user/courses";
    }

    @GetMapping("/courses/add")
    public String showAddCourseForm(Model model, HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        if(loggedInUser == null) {
            return "redirect:/auth/login";
        }

        Course newCourse = courseService.createNewEmptyCourseForUser(loggedInUser);

        model.addAttribute("course", newCourse);

        return "/user/course-form";
    }

    @PostMapping("/courses/add")
    public String processAddCourse(@ModelAttribute("course") Course course, HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");

        courseService.save(course);
        return "redirect:/user/courses";
    }
}
