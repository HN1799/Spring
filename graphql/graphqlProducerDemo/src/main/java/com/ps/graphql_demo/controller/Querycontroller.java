package com.ps.graphql_demo.controller;

import com.ps.graphql_demo.entity.Student;
import com.ps.graphql_demo.model.NameInput;
import com.ps.graphql_demo.response.StudentResponse;
import com.ps.graphql_demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class Querycontroller {

    @Autowired
    StudentService studService;


    @QueryMapping
    public String firstQuery(){
        return "firstString";
    }


    @QueryMapping
    public String secondQuery(){
        return "lastString";

    }
//graphql with input paramaters
    /*
    query
{
fullName(fName:"HImanshu", lName: "nahak")

}
     */
    @QueryMapping
    public String fullName(@Argument  String fName,@Argument String lName){


        return "Hello " +fName +" "+ lName;
    }

    /*
    query
{
fullNameInput(input: {
  fname:"Himanshu",
  lname:"nahak"
})


}


with variables

query

GetFullNameInput($input: NameInput!){
  fullNameInput(input: $input)
}



  variables

  {
  "input":{
    "fname": "Himanshu",
    "lname":"nahak"
  }
}
     */
    @QueryMapping
    public String fullNameInput(@Argument NameInput input){
        return "hello "+ input.getFname() +" " + input.getLname();
    }




    /*
    query{
  getStudent(id: 1) {
    id
    firstName
    lastName
    email
    street
    city
    learningSubjects {
      id
      subjectName
      marksObtained
    }
  }
}
     */

    @QueryMapping
    public StudentResponse getStudent(@Argument Long id){
    Student studentById = studService.getStudentById(id);
    return  new StudentResponse(studentById);
}



}
