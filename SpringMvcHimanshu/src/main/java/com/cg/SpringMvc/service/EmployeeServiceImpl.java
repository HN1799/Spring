package com.cg.SpringMvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.SpringMvc.dto.EmployeeRegistrationDto;
import com.cg.SpringMvc.entity.Employee;
import com.cg.SpringMvc.exception.EmployeeNotFoundException;
import com.cg.SpringMvc.exception.EmployeeRegistraitionError;
import com.cg.SpringMvc.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeDao;

	@Override
	public Employee findById(Integer id) {
		Optional<Employee>  emp = employeeDao.findById(id);
		System.out.println("inside Find By Id");
		try {
		System.out.println(emp.get());
		}
		catch (Exception e) {
			throw new EmployeeNotFoundException("No Employee present");
		}
		return emp.get();
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> lst =  null;
		try {
			
			 lst = employeeDao.findAll();
		} catch (Exception e) {
			throw new EmployeeNotFoundException("Cannot find any employee");
		}
		
		return lst;
	}

	@Override
	public Employee saveEmp(EmployeeRegistrationDto emp) {
		System.out.println("service1");
		Employee empSaved= null;
		
		empSaved = new Employee(emp.getId(),emp.getName(),emp.getEmail(),emp.getBu(),Long.valueOf(emp.getPhnNo()),emp.getEmpId());
		
		System.out.println("service2");
		System.out.println(empSaved);
		   Boolean empPresent=isEmpPresent(empSaved.getEmpId());
		   System.out.println(empPresent);
		   	if(empPresent) {
		   		throw new EmployeeRegistraitionError("employee registration error");
		   	}
		
		return employeeDao.save(empSaved);
	}

	@Override
	public boolean isEmpPresent(Long empId) {
		 Optional<Employee> emp = employeeDao.findByEmpId(empId);
		 if(emp.isEmpty())
			 return false;
		 
	
		 if(emp.get()!=null) {
			 return true;
		 }
		
		return false;
	}

	@Override
	public Page<Employee> findAll(Pageable pageable) {
		
		return employeeDao.findAll(pageable);
	}

	@Override
	public boolean isEmailPresent(String email) {
		System.out.println("1");
		 Optional<Employee> emp=null;
		try {
			emp=employeeDao.findByEmail(email);
			System.out.println("inside try service impl");
			if(emp.get()!=null) {
				 return true;
			 }

		} catch (Exception e) {
			System.out.println("inside Exception");
		}
		 System.out.println("2");
		 
		 System.out.println("end of sevice impl");
		return false;
	}

	
	
}
