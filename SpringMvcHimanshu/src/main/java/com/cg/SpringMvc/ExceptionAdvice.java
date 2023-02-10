package com.cg.SpringMvc;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cg.SpringMvc.exception.EmployeeNotFoundException;
import com.cg.SpringMvc.exception.EmployeeRegistraitionError;

@ControllerAdvice
public class ExceptionAdvice {
	
	// For UI Pages
		@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
		@ExceptionHandler(EmployeeNotFoundException.class)
		public String HandleEmployeeNotFoundException(Exception e,Model model) {
			System.out.println("Inside employee not found");
			model.addAttribute("error",e.getMessage());
			return "custom_exception";
		}
		@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
		@ExceptionHandler(EmployeeRegistraitionError.class)
		public String HandleEmployeeRegistrationException(Exception e,Model model) {
			System.out.println("Inside employee not found");
			model.addAttribute("error",e.getMessage());
			return "custom_exception";
		}
		

		
		@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
		@ExceptionHandler(Exception.class)
		public String handleError(Exception e,Model model) {
			model.addAttribute("error",e.getMessage());
			return "custom_exception";
		}
		
		


		

}
