package com.group5.mbs.api.model.response;

import com.group5.mbs.api.dtos.SubmitThesis;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SubmittedThesisResponse extends BaseResponse {

    private SubmitThesis submitThesis;

}
