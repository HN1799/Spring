package com.ps.graphql_demo.resolver;

import com.ps.graphql_demo.entity.Subject;

import com.ps.graphql_demo.enums.SubjectNameFilter;
import com.ps.graphql_demo.response.StudentResponse;
import com.ps.graphql_demo.response.SubjectResponse;


import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

//import org.springframework.graphql.data.method.annotation.Source;


import java.util.ArrayList;
import java.util.List;

//@Component  not component
@Controller
public class QueryFieldResolver {

    @SchemaMapping(typeName = "StudentResponse", field = "fullName")
    public String resolveFullName(StudentResponse studentResponse) {
        return studentResponse.getFirstName() + " " + studentResponse.getLastName();
    }

// this is an student to subject edge
//in schema we are only putting subjectenumn because studentResponse is graphql passing internally
    @SchemaMapping(typeName = "StudentResponse", field = "learningSubjects")
    public List<SubjectResponse> getLearningSubjects (  StudentResponse studentResponse,
                                                      @Argument("subjectNameFilter") SubjectNameFilter  subjectNameFilter) {

        List<SubjectResponse> learningSubjects = new ArrayList<SubjectResponse>();

        if (studentResponse.getStudent().getLearningSubjects() != null) {
            for (Subject subject: studentResponse.getStudent().getLearningSubjects()) {
                if(subjectNameFilter.name().equalsIgnoreCase("All")){
                    learningSubjects.add(new SubjectResponse(subject));

                }
               else if (subjectNameFilter.name().equalsIgnoreCase(subject.getSubjectName())) {
                    learningSubjects.add(new SubjectResponse(subject));
                }
            }
        }

        return learningSubjects;

    }


}

/* var
{
  "id":1,
  "subName": "All"
}


# Welcome to Altair GraphQL Client.
# You can send your request using CmdOrCtrl + Enter.

# Enter your graphQL query here.
query getStudent($id:ID!, $subName:SubjectNameFilter){
getStudent(id:$id) {
  id
  firstName
  lastName
  email
  street
  city
  fullName
  learningSubjects(subjectNameFilter: $subName) {
    id
    subjectName
    marksObtained
  }
}
}
 */
