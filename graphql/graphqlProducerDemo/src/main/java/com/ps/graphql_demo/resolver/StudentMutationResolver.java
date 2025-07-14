package com.ps.graphql_demo.resolver;


import com.ps.graphql_demo.request.CreateStudentRequest;
import com.ps.graphql_demo.response.StudentResponse;
import com.ps.graphql_demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class StudentMutationResolver {

    @Autowired
    StudentService studentService;

    @MutationMapping
    public StudentResponse createStudent(  @Argument("createStudentRequest")
            CreateStudentRequest createStudentRequest){
        return new StudentResponse(studentService.createStudent(createStudentRequest));
    }



/*
    with variables calling

    mutation createStudent($createStudentRequest: CreateStudentRequest){
  createStudent(createStudentRequest: $createStudentRequest ){
    email
  }
}


vraibles
{
  "createStudentRequest" :{
    "firstName":"Sachin",
    "lastName": "kumar",
    "email": "hi5manshu1017",
    "street": "32",
    "city": "raipur",
    "subjectsLearning":[
      {
        "subjectName":"ai",
        "marksObtained": 22.22
      },
      {
        "subjectName":"python",
        "marksObtained": 93.22
      }
    ]

  }

}
 */
}
