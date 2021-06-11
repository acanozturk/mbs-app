package com.group5.mbs.api.mappers;

import com.group5.mbs.api.dtos.Student;
import com.group5.mbs.entities.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student studentEntityToStudentDTO(StudentEntity studentEntity);

    StudentEntity studentDTOToStudentEntity(Student student);

}
