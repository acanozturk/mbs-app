package com.group5.mbs.repositories;

import com.group5.mbs.entities.DepartmentalBoardRepresentativeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentalBoardRepresentativeRepository
        extends JpaRepository<DepartmentalBoardRepresentativeEntity, Integer> {

    DepartmentalBoardRepresentativeEntity findDepartmentalBoardRepresentativeEntityById(Integer dbrId);

    DepartmentalBoardRepresentativeEntity findByEmailAndPassword(String email, String password);

}
