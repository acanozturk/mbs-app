package com.group5.mbs.api.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.group5.mbs.entities.UserType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.MappedSuperclass;

@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends BaseDTO {

    private UserType userType;
    private String firstName;
    private String lastName;

}
