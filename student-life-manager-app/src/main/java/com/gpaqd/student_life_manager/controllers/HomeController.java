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

	@GetMapping("/")
	public String home(Model model) {
		return "index";
	}
}
