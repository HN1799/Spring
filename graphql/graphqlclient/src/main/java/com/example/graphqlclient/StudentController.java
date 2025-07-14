package com.example.graphqlclient;


import com.example.graphqlclient.request.CreateStudentRequest;
import com.example.graphqlclient.response.StudentResponse;
import com.example.graphqlclient.service.StudentClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/students")
public class StudentController {


    private final StudentClientService studentClientService;

    public StudentController (StudentClientService studentClientService){
        this.studentClientService = studentClientService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable int id){
        StudentResponse studentResponse = studentClientService.fetchStudent(id,"All");
        return studentResponse !=null ? ResponseEntity.ok(studentResponse):
                ResponseEntity.notFound().build();
    }


    @PostMapping("/create")
    public ResponseEntity<StudentResponse> createStudent(@RequestBody CreateStudentRequest request)
    {
        StudentResponse response = studentClientService.createStudent(request);
        return  ResponseEntity.ok(response);
    }

}
