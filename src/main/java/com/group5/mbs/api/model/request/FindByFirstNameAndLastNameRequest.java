package com.group5.mbs.api.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FindByFirstNameAndLastNameRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

}
