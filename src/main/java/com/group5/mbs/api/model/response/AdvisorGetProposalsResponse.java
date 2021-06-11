package com.group5.mbs.api.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group5.mbs.api.dtos.Proposal;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdvisorGetProposalsResponse extends BaseResponse {

    @JsonIgnoreProperties({"advisor"})
    private List<Proposal> proposals;

}
