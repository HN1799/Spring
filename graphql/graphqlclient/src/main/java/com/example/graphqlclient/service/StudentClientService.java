package com.example.graphqlclient.service;


import com.example.graphqlclient.request.CreateStudentRequest;
import com.example.graphqlclient.response.GetStudentResponse;
import com.example.graphqlclient.response.StudentResponse;
import com.example.graphqlclient.response.SubjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class StudentClientService {


    private final WebClient webClient;

    public StudentClientService(WebClient webClient) {
        this.webClient = webClient;
    }


    public StudentResponse fetchStudent(int id, String subName) {

        String query = """
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
                """;


        Map<String, Object> variables = Map.of("id", id, "subName", subName);
        Map<String, Object> requestBody = Map.of("query", query, "variables", variables);

        try {
            GetStudentResponse response = webClient.post().bodyValue(requestBody)
                    .retrieve().bodyToMono(GetStudentResponse.class)
                    .block();

            if (response != null && response.getData() != null) {
                return response.getData().getGetStudent();
            } else {
                log.warn("No Student data found for id: {}", id);
                return null;
            }


        } catch (WebClientResponseException e) {
            log.error("Graphql API error: {}", e.getResponseBodyAsString(), e);
            throw  new RuntimeException("Graphql API error");

        } catch (Exception e) {
            log.error("Unexcpected error while calling Graphql Api", e);
            throw new RuntimeException("Unexcpected error", e);

        }


    }


    public StudentResponse createStudent(CreateStudentRequest createStudentRequest){
        Map<String, Object> variables = new HashMap<>();
        variables.put("createStudentRequest", createStudentRequest);

        String mutationStr = """
                mutation createStudent($createStudentRequest: CreateStudentRequest){
                    createStudent(createStudentRequest: $createStudentRequest ) {
                      id
                      firstName
                      lastName
                      email
                      street
                      city
                      fullName
                      learningSubjects(subjectNameFilter: All) {
                        id
                        subjectName
                        marksObtained
                      }
                    }
                  }
                
                """;

        Map<String, Object> body = Map.of("query",mutationStr,
                "variables", variables);


        Map<String, Object> response =  webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        Map<String, Object> data = (Map<String, Object>)((Map<String, Object>) response.get("data")).get("createStudent");

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setEmail((String) data.get("email"));
        Object idObj = data.get("id");
        Long id = (idObj instanceof Number)
                ? ((Number) idObj).longValue()
                : Long.parseLong(idObj.toString());

        studentResponse.setId(id);

        studentResponse.setFirstName((String)data.get("firstName"));
        studentResponse.setLastName((String) data.get("lastName"));
        studentResponse.setStreet((String) data.get("street"));
        studentResponse.setCity((String) data.get("city"));
        studentResponse.setFullName((String)data.get("fullName"));


        List<Map<String, Object>> subjects = (List<Map<String, Object>>) data.get("learningSubjects");
        List<SubjectResponse> subjectResponses = subjects.stream().map( sub-> {
            SubjectResponse subject = new SubjectResponse();
//            subject.setId(((Number) sub.get("id")).longValue());

           Object subIdObj = sub.get("id");
            Long suBid = (subIdObj instanceof Number)
                    ? ((Number) subIdObj).longValue()
                    : Long.parseLong(subIdObj.toString());
            subject.setId(suBid);


            subject.setSubjectName((String) sub.get("subjectName"));

            Object marksObj = sub.get("marksObtained");
            if (marksObj instanceof Number) {
                subject.setMarksObtained(((Number) marksObj).doubleValue());
            } else {
                subject.setMarksObtained(Double.parseDouble(marksObj.toString()));
            }
            return  subject;
        }).toList();


        studentResponse.setLearningSubjects(subjectResponses);
         return studentResponse;
    }
}
