package com.group5.mbs.repositories;

import com.group5.mbs.entities.AdvisorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvisorRepository extends JpaRepository<AdvisorEntity, Integer> {

    AdvisorEntity findAdvisorEntityById(Integer advisorId);

    AdvisorEntity findByEmailAndPassword(String email, String password);

}
