package com.ps.graphql_demo.request;

import lombok.Data;

@Data
public class CreateSubjectRequest {

    private String subjectName;

    private Double marksObtained;
}
