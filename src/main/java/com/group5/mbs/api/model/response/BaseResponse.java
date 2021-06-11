package com.group5.mbs.api.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseResponse {

    private boolean success = true;
    private String successMessage;
    private String errorMessage;

}
