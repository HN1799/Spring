package com.cg.SpringMvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.SpringMvc.entity.Employee;
import com.cg.SpringMvc.exception.EmployeeNotFoundException;
import com.cg.SpringMvc.repository.EmployeeRepository;
import com.cg.SpringMvc.service.EmployeeService;


@Controller
@RequestMapping("/home/user")
public class UserController {
	

	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private EmployeeRepository empDao;
	
	
	@GetMapping("/emp")
	public String myDetails(Model model) {
		System.out.println("inside usercontroller mydetails");
		Employee emp =null;
		try {
		 emp = empDao.getById(1);
		 System.out.println(emp);
		}
		catch (Exception e) {
			throw new EmployeeNotFoundException("Cannot find Your Details");
		}
		
			
		
		model.addAttribute("emp",emp);
		return "employeeall";
	}
	
}
