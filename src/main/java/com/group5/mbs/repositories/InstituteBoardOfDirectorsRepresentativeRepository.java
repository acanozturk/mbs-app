package com.group5.mbs.repositories;

import com.group5.mbs.entities.InstituteBoardOfDirectorsRepresentativeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstituteBoardOfDirectorsRepresentativeRepository
        extends JpaRepository<InstituteBoardOfDirectorsRepresentativeEntity, Integer> {

    InstituteBoardOfDirectorsRepresentativeEntity findInstituteBoardOfDirectorsRepresentativeEntityById(Integer ibdrId);

    InstituteBoardOfDirectorsRepresentativeEntity findByEmailAndPassword(String email, String password);

}
