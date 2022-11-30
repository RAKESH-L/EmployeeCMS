package com.springrest.employeecms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.springrest.employeecms.enums.TicketPriorityEnum;

@Entity
public class Ticket {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto generated by Hibernate
    private Long id;
	
	@Column(length = 2000)
	private String issue;
	
	@Enumerated(EnumType.STRING)
	private TicketPriorityEnum priority;
	
	@ManyToOne
	private Employee employee;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public TicketPriorityEnum getPriority() {
		return priority;
	}

	public void setPriority(TicketPriorityEnum priority) {
		this.priority = priority;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", issue=" + issue + ", priority=" + priority + ", employee=" + employee + "]";
	}
	
	
}
