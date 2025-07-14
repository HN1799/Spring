package com.ps.graphql_demo.service;

import com.ps.graphql_demo.entity.Address;
import com.ps.graphql_demo.entity.Student;
import com.ps.graphql_demo.entity.Subject;
import com.ps.graphql_demo.repo.AddressRepository;
import com.ps.graphql_demo.repo.StudentRepository;
import com.ps.graphql_demo.repo.SubjectRepository;
import com.ps.graphql_demo.request.CreateStudentRequest;
import com.ps.graphql_demo.request.CreateSubjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	SubjectRepository subjectRepository;

	@Autowired
	StudentRepository studentRepository;
	
	public Student getStudentById (long id) {
		return studentRepository.findById(id).get();
	}

	public Student createStudent (CreateStudentRequest createStudentRequest) {
		Student student = new Student(createStudentRequest);

		Address address = new Address();
		address.setStreet(createStudentRequest.getStreet());
		address.setCity(createStudentRequest.getCity());

		address = addressRepository.save(address);

		student.setAddress(address);
		student = studentRepository.save(student);

		List<Subject> subjectsList = new ArrayList<Subject>();

		if(createStudentRequest.getSubjectsLearning() != null) {
			for (CreateSubjectRequest createSubjectRequest :
					createStudentRequest.getSubjectsLearning()) {
				Subject subject = new Subject();
				subject.setSubjectName(createSubjectRequest.getSubjectName());
				subject.setMarksObtained(createSubjectRequest.getMarksObtained());
				subject.setStudent(student);

				subjectsList.add(subject);
			}

			subjectRepository.saveAll(subjectsList);

		}

		student.setLearningSubjects(subjectsList);

		return student;
	}
}
