package com.group5.mbs.api.model.response;

import com.group5.mbs.api.dtos.SubmitThesis;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdvisorGetAllSubmissionsResponse extends BaseResponse {

    private List<SubmitThesis> submissionList;

}
