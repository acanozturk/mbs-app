package com.group5.mbs.repositories;

import com.group5.mbs.entities.FormEntity;
import com.group5.mbs.entities.FormType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<FormEntity, Integer> {

    FormEntity findFormEntityByStudentEntityIdAndFormType(Integer studentId, FormType formType);

    FormEntity findFormEntityById(Integer formId);

}
