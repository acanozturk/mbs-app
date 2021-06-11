package com.group5.mbs.repositories;

import com.group5.mbs.entities.TssJuryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TssJuryRepository extends JpaRepository<TssJuryEntity, Integer> {

    List<TssJuryEntity> findAllByStudentEntityId(Integer studentId);

    List<TssJuryEntity> findAllByJuryMemberEntityId(Integer juryMemberId);

    TssJuryEntity findTssJuryEntityByJuryMemberEntityIdAndStudentEntityId(Integer juryMemberId, Integer studentId);
}
