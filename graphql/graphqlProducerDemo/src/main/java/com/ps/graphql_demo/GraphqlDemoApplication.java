package com.ps.graphql_demo;

import com.ps.graphql_demo.entity.Address;
import com.ps.graphql_demo.entity.Student;
import com.ps.graphql_demo.entity.Subject;
import com.ps.graphql_demo.repo.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class GraphqlDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlDemoApplication.class, args);


}


@Bean
CommandLineRunner initData(StudentRepository studRep){
	return args -> {
		Address address = new Address();
		address.setCity("bhilai");
		address.setStreet("street 33");


		Student student = new Student();
		student.setFirstName("Himanshu");
		student.setLastName("Nahak");
		student.setAddress(address);
		student.setEmail("1799himanshu@gmail.com");


		Subject subject1 = new Subject();
		subject1.setSubjectName("Java");
		subject1.setMarksObtained((double) 95);
		subject1.setStudent(student);

		Subject subject2 = new Subject();
		subject2.setSubjectName("English");
		subject2.setMarksObtained((double) 43);
		subject2.setStudent(student);

		student.setLearningSubjects(List.of(subject1,subject2));


		studRep.save(student);



	};
}


}
