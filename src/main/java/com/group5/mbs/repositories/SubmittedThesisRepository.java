package com.group5.mbs.repositories;

import com.group5.mbs.entities.SubmitThesisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmittedThesisRepository extends JpaRepository<SubmitThesisEntity, Integer> {

    SubmitThesisEntity findSubmitThesisEntityByStudentEntityId(Integer studentId);
}
