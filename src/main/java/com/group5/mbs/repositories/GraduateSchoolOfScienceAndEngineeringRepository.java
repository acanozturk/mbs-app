package com.group5.mbs.repositories;

import com.group5.mbs.entities.GraduateSchoolOfScienceAndEngineeringEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GraduateSchoolOfScienceAndEngineeringRepository
        extends JpaRepository<GraduateSchoolOfScienceAndEngineeringEntity, Integer> {

    GraduateSchoolOfScienceAndEngineeringEntity findGraduateSchoolOfScienceAndEngineeringEntityById(Integer gsesId);

    GraduateSchoolOfScienceAndEngineeringEntity findByEmailAndPassword(String email, String password);

}
