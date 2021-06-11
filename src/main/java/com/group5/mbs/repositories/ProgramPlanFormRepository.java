package com.group5.mbs.repositories;

import com.group5.mbs.entities.ProgramPlanFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramPlanFormRepository extends JpaRepository<ProgramPlanFormEntity, Integer> {

    ProgramPlanFormEntity findProgramPlanFormEntityById(Integer formId);

}
