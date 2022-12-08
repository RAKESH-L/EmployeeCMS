package com.springrest.employeecms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springrest.employeecms.model.AccoutHolder;

public interface AccountHolderRepository  extends JpaRepository<AccoutHolder, Long>{

	@Query("select ahl.accountHolder  from AccountHolderLoan ahl where ahl.loan.id=?1")
	List<AccoutHolder> getAHByLoanId(Long lid);

}
