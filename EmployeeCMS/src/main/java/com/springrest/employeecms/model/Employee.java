package com.springrest.employeecms.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.springrest.employeecms.enums.EmployeeAccessStatusEnum;

@Entity
public class Employee {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto generated by Hibernate
    private Long id;

	private String name; // varchar(255) in mysql
    private String jobTitle;
    private LocalDate createdAt;
    
    @Enumerated(EnumType.STRING)
    private EmployeeAccessStatusEnum accessType;
	
    @OneToOne
    private User user;
    
    @ManyToOne
    private Manager manager;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public EmployeeAccessStatusEnum getAccessType() {
		return accessType;
	}

	public void setAccessType(EmployeeAccessStatusEnum accessType) {
		this.accessType = accessType;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", jobTitle=" + jobTitle + ", createdAt=" + createdAt
				+ ", accessType=" + accessType + ", user=" + user + ", manager=" + manager + "]";
	}

	
	
    
    
}
