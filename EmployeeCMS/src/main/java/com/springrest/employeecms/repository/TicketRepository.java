package com.springrest.employeecms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springrest.employeecms.enums.TicketStateEnum;
import com.springrest.employeecms.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long>{

	@Query("select t from Ticket t where t.employee.user.username=?1 AND t.stateEnum=?2")
	List<Ticket> getAllTicketByState(String username, TicketStateEnum stateEnum);

}
