package com.example.graphqlclient.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetStudentResponse {

    private DataWrapper data;


    @Data
    public static class DataWrapper{
        private StudentResponse getStudent;
    }
}
