package com.group5.mbs.api.dtos;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class BaseDTO {

    private Integer id;

}
