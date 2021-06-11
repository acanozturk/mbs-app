package com.group5.mbs.repositories;

import com.group5.mbs.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

    StudentEntity findStudentEntityById(Integer studentId);

    StudentEntity findByEmailAndPassword(String email, String password);

    List<StudentEntity> findAllByAdvisorEntityId(Integer advisorId);

    List<StudentEntity> findByAdvisorEntityIsNull();

}
