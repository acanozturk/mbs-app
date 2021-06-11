package com.group5.mbs.repositories;

import com.group5.mbs.entities.ProposalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProposalRepository extends JpaRepository<ProposalEntity, Integer> {

    List<ProposalEntity> findAllByAdvisorEntityId(Integer advisorId);

    List<ProposalEntity> findAllByStudentEntityId(Integer studentId);

    ProposalEntity findProposalEntityByAdvisorEntityIdAndStudentEntityId(Integer advisorId, Integer studentId);

}
