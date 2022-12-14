package com.springrest.employeecms.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.employeecms.model.User;
import com.springrest.employeecms.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:8631/")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/login")
	public User login(Principal principal) {
		String username = principal.getName();
		User user = userRepository.findByUsername(username);
		user.setPassword("---------");
		return user;
	}
}
