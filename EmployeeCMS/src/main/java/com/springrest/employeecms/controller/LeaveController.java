package com.springrest.employeecms.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.employeecms.dto.LeaveResponseDto;
import com.springrest.employeecms.enums.LeaveStatusEnum;
import com.springrest.employeecms.model.Employee;
import com.springrest.employeecms.model.Leave;
import com.springrest.employeecms.repository.EmployeeRepository;
import com.springrest.employeecms.repository.LeaveRepository;

@RestController
@RequestMapping("/api/leave")
public class LeaveController {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private LeaveRepository leaveRepository;
	
	//path: /api/leave/add
	@PostMapping("/add")
	public ResponseEntity<String> postLeave(Principal principal, @RequestBody Leave leave){
		String userString = principal.getName();
		// fetch employee by username
		Employee employee = employeeRepository.getEmployeeByUsername(userString);
		
		// attach this employee to leave
		leave.setEmployee(employee);
		
		//set the state of leave to Pending
		leave.setLeaveStatus(LeaveStatusEnum.PENDING);
		
		//post leave
		leaveRepository.save(leave);
		return ResponseEntity.status(HttpStatus.OK).body("Leave Added Successfully");
	}
	//path: /api/leave/all/{status}
	@GetMapping("/all/{status}")
	public List<LeaveResponseDto> getPendingLeave(Principal principal,
										@PathVariable("status") String status){
		// fetch all leaves that have status as Pending
		String username = principal.getName(); //Username of Employee
		
		// convert status into enum
		LeaveStatusEnum statusEnum = LeaveStatusEnum.valueOf(status);
		List<Leave> leaves = leaveRepository.getAllLeavesByStatus(username, statusEnum);
		
		//convert leaves into Dto
		List<LeaveResponseDto> listDto = new ArrayList<>();
		for(Leave l : leaves) {
			// convert l to dto
			LeaveResponseDto dto = new LeaveResponseDto();
			dto.setId(l.getId());
			dto.setFromDate(l.getFromDate());
			dto.setToDate(l.getToDate());
			dto.setNumDays(l.getNumDays());
			dto.setLeaveStatus(l.getLeaveStatus().toString());
			listDto.add(dto);
		}
		return listDto;
	}
}
