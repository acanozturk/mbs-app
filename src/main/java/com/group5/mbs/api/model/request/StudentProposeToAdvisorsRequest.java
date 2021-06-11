package com.group5.mbs.api.model.request;

import lombok.Data;

import java.util.List;

@Data
public class StudentProposeToAdvisorsRequest {

    private Integer studentId;
    private List<Integer> advisorIds;

}
