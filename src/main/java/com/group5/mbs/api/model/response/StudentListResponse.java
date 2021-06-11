package com.group5.mbs.api.model.response;

import com.group5.mbs.api.dtos.Student;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentListResponse extends BaseResponse {

    private List<Student> studentList;

}
