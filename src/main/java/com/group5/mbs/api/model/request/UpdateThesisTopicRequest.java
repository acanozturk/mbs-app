package com.group5.mbs.api.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UpdateThesisTopicRequest {

    private Integer studentId;

    @NotBlank
    private String thesisTopic;

}
