package com.gpaqd.student_life_manager.controllers;


import com.gpaqd.student_life_manager.dto.CourseDetailsDTO;
import com.gpaqd.student_life_manager.service.DeadlineService;
import com.gpaqd.student_life_manager.entity.Course;
import com.gpaqd.student_life_manager.entity.Project;
import com.gpaqd.student_life_manager.entity.Lab;
import com.gpaqd.student_life_manager.entity.MyTest;
import com.gpaqd.student_life_manager.entity.User;
import com.gpaqd.student_life_manager.entity.pk.CourseId;
import com.gpaqd.student_life_manager.entity.pk.ProjectId;
import com.gpaqd.student_life_manager.entity.pk.LabId;
import com.gpaqd.student_life_manager.entity.pk.MyTestId;
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
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final CourseService courseService;
    private final DeadlineService deadlineService;

    @Autowired
    public UserController(UserService userService, CourseService courseService, DeadlineService deadlineService) {
        this.userService = userService;
        this.courseService = courseService;
        this.deadlineService = deadlineService;
    }

    // @GetMapping("/dashboard")
    // public String showDashboard(Model model, HttpSession session) {
    //     String loggedInUser = (String) session.getAttribute("loggedInUser");

    //     if (loggedInUser == null) {
    //         return "redirect:/auth/login";
    //     }

    //     User user = userService.findById(loggedInUser);
    //     model.addAttribute("user", user);

    //     return "/user/dashboard";
    // }

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

        CourseDetailsDTO dto = new CourseDetailsDTO();

        model.addAttribute("courseDto", dto);

        return "/user/course-form";
    }

    @PostMapping("/courses/add")
    public String processAddCourse(@ModelAttribute("courseDto") CourseDetailsDTO dto, HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");

        courseService.saveCourseWithDTO(dto, loggedInUser);
        return "redirect:/user/courses";
    }

    @GetMapping("/courses/dashboard/{courseName}")
    public String showCourseDashboard(@PathVariable("courseName") String courseName,
                                      Model model,
                                      HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        System.out.println(loggedInUser);
        System.out.println(courseName);

        if (loggedInUser == null) {
            return "redirect:/auth/login";
        }

        CourseId courseId = new CourseId(courseName, loggedInUser);
        System.out.println(courseId);
        
        CourseDetailsDTO courseDto = courseService.getCourseDetailsDTO(courseId);
        System.out.println(courseDto);

        if (courseDto == null) {
            // Handle course not found, possibly redirect with an error message
            return "redirect:/user/courses?error=CourseNotFound";
        }

        model.addAttribute("course", courseDto);
        // Add any additional attributes needed for the dashboard

        return "user/course-dashboard"; // Path to your course dashboard Thymeleaf template
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        String loggedInUser = (String) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/auth/login";
        }

        User user = userService.findById(loggedInUser);
        model.addAttribute("user", user);

        // Using the DeadlineService
        List<MyTest> next2Tests = deadlineService.getNext2Tests(loggedInUser);
        List<Project> next2Projects = deadlineService.getNext2Projects(loggedInUser);
        List<Lab> next2Labs = deadlineService.getNext2Labs(loggedInUser);

        System.out.println("----------------------------------------------------------------------------");
        System.out.println(next2Tests);
        System.out.println(next2Projects);
        System.out.println(next2Labs);
        System.out.println("----------------------------------------------------------------------------");

        model.addAttribute("next2Tests", next2Tests);
        model.addAttribute("next2Projects", next2Projects);
        model.addAttribute("next2Labs", next2Labs);

        return "user/dashboard";
}

}
