package com.cg.SpringMvc.controller;




import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cg.SpringMvc.dto.EmployeeRegistrationDto;
import com.cg.SpringMvc.entity.Employee;
import com.cg.SpringMvc.exception.EmployeeNotFoundException;
import com.cg.SpringMvc.exception.EmployeeRegistraitionError;
import com.cg.SpringMvc.repository.EmployeeRepository;
import com.cg.SpringMvc.service.EmployeeService;


@Controller
@RequestMapping("/home/admin")
public class AdminController {
	
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private EmployeeRepository empDao;
	

	
	

	
// for paging
	@GetMapping("/emp/{page}")
	public String getAllEmpPage(@PathVariable("page") Integer page,  Model model,Authentication auth) {
		model.addAttribute("loginAz",auth.getName());
		Pageable pageable= PageRequest.of(page, 5);
		Page<Employee> allEmp =empService.findAll(pageable);
		System.out.println("get all emp page "+allEmp);
		System.out.println(allEmp.isEmpty());
		if(allEmp.isEmpty())
		{
			throw new EmployeeNotFoundException("No such Employee in Directory");
		}
//		Page<Employee> allEmp = empDao.findAll(pageable);
		
		model.addAttribute("emp",allEmp);
		
//		Employee myDetails = empService.findById(1);
//		model.addAttribute("myDetails",myDetails);
		model.addAttribute("currentPage",page);
		model.addAttribute("totalPages",allEmp.getTotalPages());
		
		return "employeeall";
		
	}
	

	
//	 to get a register page
	  @GetMapping("/register")
	    public String showRegistrationForm(Model model,Authentication auth) {
		  EmployeeRegistrationDto reg= new EmployeeRegistrationDto();
		  model.addAttribute("user",reg);

		  System.out.println("get mapping register");
	        return "register";   
	        }
		 
	  
	  

//      to register
	    @PostMapping("/register")
	    public String registerUserAccount( @Valid @ModelAttribute("user")  EmployeeRegistrationDto  registrationDto,BindingResult result,Model model) {
	    	 System.out.println(" post register");
	    	 
	    	if(result.hasErrors()) {
	    		System.out.println("has errors");
	    		System.out.println("Dto objcet : "+registrationDto);
	    		return "register";
	    	}
	    	boolean empPresent = empService.isEmpPresent(registrationDto.getEmpId());
	    	if(empPresent) {
	    		System.out.println("inside unsuccesfull");
	    		model.addAttribute("isPresent", "employee with id: "+registrationDto.getEmpId()+" already present");
	    		return "redirect:/home/admin/register?unsuccessempid";
	    		
	    	}
	    	boolean emailPresent = empService.isEmailPresent(registrationDto.getEmail());
	    	if(emailPresent) {
	    		System.out.println("inside unsuccesfull email");
	    		model.addAttribute("isPresent", "employee with id: "+registrationDto.getEmpId()+" already present");
	    		return "redirect:/home/admin/register?unsuccessemail";
	    		
	    	}
	    	
	    	System.out.println("admin1");
	    	Employee emp= empService.saveEmp(registrationDto);
	    	 System.out.println(emp);
	    	 System.out.println("admin2");
	    	
	        return "redirect:/home/admin/register?success";}

}
