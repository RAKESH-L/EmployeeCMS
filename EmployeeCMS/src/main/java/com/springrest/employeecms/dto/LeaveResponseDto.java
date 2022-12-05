package com.springrest.employeecms.dto;

import java.time.LocalDate;

public class LeaveResponseDto {

	private Long id;
	private LocalDate fromDate;
	private LocalDate toDate;
	private int numDays;
	private String leaveStatus;

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public int getNumDays() {
		return numDays;
	}

	public void setNumDays(int numDays) {
		this.numDays = numDays;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLeaveStatus() {
		return leaveStatus;
	}

	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}

	@Override
	public String toString() {
		return "LeaveResponseDto [id=" + id + ", fromDate=" + fromDate + ", toDate=" + toDate + ", numDays=" + numDays
				+ ", leaveStatus=" + leaveStatus + "]";
	}
	
	
}
