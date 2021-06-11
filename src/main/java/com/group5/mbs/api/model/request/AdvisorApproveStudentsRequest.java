package com.group5.mbs.api.model.request;

import lombok.Data;

import java.util.List;

@Data
public class AdvisorApproveStudentsRequest {

    private List<Integer> studentIds;

}
