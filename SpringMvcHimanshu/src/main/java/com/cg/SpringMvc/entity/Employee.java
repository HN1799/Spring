package com.cg.SpringMvc.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Employee_mvc")
public class Employee {
	@Id
	@GeneratedValue
	private Integer id;

	@Column(name="Name", nullable=false)
	private String name;
	
	@Column(name="Email",unique=true)
	private String email;
	
	@Column(name="Buisness_unit")
	private String bu;
	
	@Column(name="Contact_no")
	private Long phnNo;
	
	
	@Column(name="emp_id",unique=true)
	private long empId;

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", email=" + email + ", bu=" + bu + ", phnNo=" + phnNo
				+ ", empId=" + empId + "]";
	}
//	use based login
	public Employee(Integer id, String name, String email, String bu, Long phnNo, long empId) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.bu = bu;
		this.phnNo = phnNo;
		this.empId = empId;
	}

	public Employee(String name, String email, String bu, Long phnNo, long empId) {
		super();
		this.name = name;
		this.email = email;
		this.bu = bu;
		this.phnNo = phnNo;
		this.empId = empId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBu() {
		return bu;
	}

	public void setBu(String bu) {
		this.bu = bu;
	}

	public Long getPhnNo() {
		return phnNo;
	}

	public void setPhnNo(Long phnNo) {
		this.phnNo = phnNo;
	}

	public long getEmpId() {
		return empId;
	}

	public void setEmpId(long empId) {
		this.empId = empId;
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
