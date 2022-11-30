package com.springrest.employeecms.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.employeecms.enums.EmployeeAccessStatusEnum;
import com.springrest.employeecms.model.Employee;
import com.springrest.employeecms.model.Manager;
import com.springrest.employeecms.model.User;
import com.springrest.employeecms.repository.EmployeeRepository;
import com.springrest.employeecms.repository.ManagerRepository;
import com.springrest.employeecms.repository.UserRepository;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@PostMapping("/add/{id}")
	public ResponseEntity<String> addEmployee(@PathVariable("id") Long managerId,
												@RequestBody Employee employee){
		Optional<Manager> optional = managerRepository.findById(managerId);
		if(!optional.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Manager ID is Invalid");
		
		Manager manager = optional.get();
		
		employee.setManager(manager);
		employee.setCreatedAt(LocalDate.now());
		
		User user = employee.getUser();
		user.setRole("EMPLOYEE");
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user = userRepository.save(user);
		
		employee.setUser(user);
		
		employee.setAccessType(EmployeeAccessStatusEnum.PENDING);
		
		employeeRepository.save(employee);
		
		return ResponseEntity.status(HttpStatus.OK).body("Employee Sign up Success");
	}
	
	@PutMapping("/status/{status}/{id}")
	public ResponseEntity<String> EmployeeAccessStatusUpdate(@PathVariable("status") String status,
															@PathVariable("id") Long empId){
		EmployeeAccessStatusEnum accessStatus= null;
		try {
			accessStatus = EmployeeAccessStatusEnum.valueOf(status);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unknown Status");
			// TODO: handle exception
		}
		
		Optional<Employee> optional = employeeRepository.findById(empId);
		
		if(!optional.isPresent())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Employee ID is Invalid");
		
		Employee employee = optional.get();
		
		employee.setAccessType(accessStatus);
		
		employeeRepository.save(employee);
		
		return ResponseEntity.status(HttpStatus.OK).body("Employee Status Updated");
	}
}