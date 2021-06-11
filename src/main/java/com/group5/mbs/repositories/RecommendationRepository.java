package com.group5.mbs.repositories;

import com.group5.mbs.entities.RecommendationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecommendationRepository extends JpaRepository<RecommendationEntity, Integer> {

    List<RecommendationEntity> findRecommendationEntityByStudentEntityId(Integer studentId);

    List<RecommendationEntity> findAllByStudentEntityId(Integer studentId);

    RecommendationEntity
        findRecommendationEntityByAdvisorEntityIdAndStudentEntityId(Integer advisorId, Integer studentId);
}