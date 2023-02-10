package com.cg.SpringMvc.dto;

import javax.persistence.GeneratedValue;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EmployeeRegistrationDto {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Name is mandatory") @Size(min = 2, max = 20)
	private String name;
	
	@NotBlank(message = "email is mandatory") @Email
	private String email;
	
	@NotBlank(message = "BU is mandatory")
	@Pattern(regexp="^[A-Za-z]*$" ,message = "Invalid Input Input type must be alphabets")
	private String bu;
	
	@NotNull(message = "Phone Numbeer is mandatory") 
	@Digits(integer = 10,fraction = 0,message = "Phone number must be of 10 digits")
	private Long phnNo;
	
	@NotNull(message = "Employee id is mandatory xxxxxxxxxx")
	@Digits(integer = 8,fraction = 0,message = "Employee id must be of 8 digits")
//	@NumberFormat(style = Style.NUMBER)
	private long empId;
	
	public String getBu() {
		return bu;
	}
	public String getEmail() {
		return email;
	}
	
	public long getEmpId() {
		return empId;
	}
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Long getPhnNo() {
		return phnNo;
	}
	public void setBu(String bu) {
		this.bu = bu;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPhnNo(Long phnNo) {
		System.out.println("this is phone np "+ phnNo);
		this.phnNo = phnNo;
	}
	@Override
	public String toString() {
		return "EmployeeRegistrationDto [id=" + id + ", name=" + name + ", email=" + email + ", bu=" + bu + ", phnNo="
				+ phnNo + ", empId=" + empId + "]";
	}
	public EmployeeRegistrationDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	




 

    

}