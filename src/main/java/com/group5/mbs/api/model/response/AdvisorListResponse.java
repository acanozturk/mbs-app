package com.group5.mbs.api.model.response;

import com.group5.mbs.api.dtos.Advisor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdvisorListResponse extends BaseResponse {

    private List<Advisor> advisorList;

}
