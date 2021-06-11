package com.group5.mbs.api.model.response;

import com.group5.mbs.entities.ProgramPlanFormStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProgramPlanFormResponse extends BaseResponse {

    private String fileName;
    private ProgramPlanFormStatus programPlanFormStatus;
    private String url;

}
