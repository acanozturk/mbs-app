package com.group5.mbs.api.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SubmitThesis extends BaseDTO {

    private Student student;

}
