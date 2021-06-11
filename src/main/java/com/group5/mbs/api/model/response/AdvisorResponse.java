package com.group5.mbs.api.model.response;

import com.group5.mbs.api.dtos.Advisor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdvisorResponse extends BaseResponse {

    private Advisor advisor;

}
