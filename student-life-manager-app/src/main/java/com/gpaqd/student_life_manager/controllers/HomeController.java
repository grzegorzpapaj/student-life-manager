package com.gpaqd.student_life_manager.controllers;

import com.gpaqd.student_life_manager.dao.UserDAOImpl;
import com.gpaqd.student_life_manager.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

	private UserDAOImpl userDAO;

	@Autowired
	public HomeController(UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}

	@GetMapping
	public String home() {

		System.out.println("Creating new user object...");
		User user = new User("abcd", "1234");

		System.out.println("Saving the user");
		userDAO.save(user);

		return "Saved user: " + user.getUsername();
	}
}
