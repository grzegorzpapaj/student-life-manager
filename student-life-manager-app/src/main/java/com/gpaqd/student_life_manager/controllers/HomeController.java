package com.gpaqd.student_life_manager.controllers;

import com.gpaqd.student_life_manager.entity.User;
import com.gpaqd.student_life_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private final UserService userService;

	@Autowired
	public HomeController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String home(Model model) {

		System.out.println("Creating new user object...");
		User user = new User("abcd", "1234");

		System.out.println("Saving the user");
		userService.save(user);

		model.addAttribute("message", "Saved user: " + user.getUsername());

		return "index";
	}
}
