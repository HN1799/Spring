package com.cg.SpringMvc.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.SpringMvc.entity.Employee;

@Repository 
public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{

	
	public Optional<Employee> findByEmpId( Long empId);
	
	public Optional<Employee> findByName(String name);

	public Optional<Employee> findByEmail(String email);
	

}
