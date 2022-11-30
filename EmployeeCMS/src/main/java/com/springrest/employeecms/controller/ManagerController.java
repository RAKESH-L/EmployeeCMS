package com.springrest.employeecms.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.employeecms.dto.ManagerResponseDto;
import com.springrest.employeecms.model.Manager;
import com.springrest.employeecms.model.User;
import com.springrest.employeecms.repository.ManagerRepository;
import com.springrest.employeecms.repository.UserRepository;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/hello")
	public String showHello() {
		return "Hello Manager";
	}
	
	@GetMapping("/auth/hello")
	public String showHelloAuth() {
		return "Hello Manager with Auth!!";
	}
	
	@GetMapping("/auth/role/hello")
	public String showHelloAuthRole() {
		return "Hello Manager with Auth and Role";
	}
	
	@PostMapping("/add")
	public Manager postManager(@RequestBody Manager manager) {
		
		/*Step 1 : Save User info to DB*/
		User user = manager.getUser();
		user.setRole("MANAGER");
		/*
		 String plainTextPassword = user.getPassword(); //rakesh@12
		 String encodedPassword = PasswordEncoder.encode(plainTextPassword);
		 user.setPassword(encodedPassword);
		 */
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.save(user);
		
		/*Step 2: Attach user to manager and save manager*/
		manager.setUser(user);
		manager.setCreatedAt(LocalDate.now());
		manager = managerRepository.save(manager);
		manager.getUser().setPassword("-------");
		return manager;
		
	}
	@GetMapping("/all")
	public List<ManagerResponseDto> getAllManager() {
		List<Manager> list = managerRepository.findAll();
		List<ManagerResponseDto> listDto = new ArrayList<>();
		for(Manager m : list) {
			ManagerResponseDto dto = new ManagerResponseDto();
			dto.setId(m.getId()); //200x (id)
			dto.setName(m.getName()); //200x (id,name)
			listDto.add(dto); //[100x,200x]
		}
		return listDto;
	}
}
