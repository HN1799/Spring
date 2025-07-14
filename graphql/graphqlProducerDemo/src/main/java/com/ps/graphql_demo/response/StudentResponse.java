package com.ps.graphql_demo.response;

import com.ps.graphql_demo.entity.Student;
import com.ps.graphql_demo.entity.Subject;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class StudentResponse {

	private long id;

	private String firstName;

	private String lastName;

	private String email;
	
	private String street;

	private String city;
	
//	private List<SubjectResponse> learningSubjects;

	// this is for internal use. DO NOT PUT IN SCHEMA
	private Student student;

	public StudentResponse (Student student) {

		this.student = student;

		this.id = student.getId();
		this.firstName = student.getFirstName();
		this.lastName = student.getLastName();
		this.email = student.getEmail();

		this.street = student.getAddress().getStreet();
		this.city = student.getAddress().getCity();


		// if in query we call only for student name and adress still below line
//		will execute whic is unnceccsay solution: query resolver
	/*	if (student.getLearningSubjects() != null) {
			learningSubjects = new ArrayList<SubjectResponse>();
			for (Subject subject: student.getLearningSubjects()) {
				learningSubjects.add(new SubjectResponse(subject));
			}
		}*/
	}

}