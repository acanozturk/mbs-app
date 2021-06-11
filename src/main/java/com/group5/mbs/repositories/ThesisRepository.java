package com.group5.mbs.repositories;

import com.group5.mbs.entities.ThesisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThesisRepository extends JpaRepository<ThesisEntity, Integer> {

    ThesisEntity findThesisEntityById(Integer thesisId);

}
