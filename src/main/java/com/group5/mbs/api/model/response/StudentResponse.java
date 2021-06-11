package com.group5.mbs.api.model.response;

import com.group5.mbs.api.dtos.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentResponse extends BaseResponse {

    private Student student;

}
