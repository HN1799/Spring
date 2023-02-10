package com.cg.SpringMvc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cg.SpringMvc.dto.EmployeeRegistrationDto;
import com.cg.SpringMvc.entity.Employee;

public interface EmployeeService {


	List<Employee> getAllEmployee();
	
	
	Employee saveEmp(EmployeeRegistrationDto emp);
	
	boolean isEmpPresent(Long id);

	Employee findById(Integer id);


	Page<Employee> findAll(Pageable pageable);


	boolean isEmailPresent(String email);



	
}
