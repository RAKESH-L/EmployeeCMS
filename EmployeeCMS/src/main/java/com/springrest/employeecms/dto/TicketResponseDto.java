package com.springrest.employeecms.dto;

public class TicketResponseDto {

	private Long id;
	private String issue;
	private String priority;
	private String stateEnum;
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
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getStateEnum() {
		return stateEnum;
	}
	public void setStateEnum(String stateEnum) {
		this.stateEnum = stateEnum;
	}
	@Override
	public String toString() {
		return "TicketResponseDto [id=" + id + ", issue=" + issue + ", priority=" + priority + ", stateEnum="
				+ stateEnum + "]";
	}
	
	
}
