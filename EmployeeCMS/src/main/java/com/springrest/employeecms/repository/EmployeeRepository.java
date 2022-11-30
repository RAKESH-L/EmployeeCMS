package com.springrest.employeecms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrest.employeecms.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
