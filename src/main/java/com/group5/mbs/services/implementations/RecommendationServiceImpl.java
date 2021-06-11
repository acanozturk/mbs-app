package com.group5.mbs.services.implementations;

import com.group5.mbs.api.dtos.Recommendation;
import com.group5.mbs.api.mappers.AdvisorMapper;
import com.group5.mbs.entities.AdvisorEntity;
import com.group5.mbs.entities.RecommendationEntity;
import com.group5.mbs.entities.StudentEntity;
import com.group5.mbs.repositories.AdvisorRepository;
import com.group5.mbs.repositories.RecommendationRepository;
import com.group5.mbs.repositories.StudentRepository;
import com.group5.mbs.services.interfaces.RecommendationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.group5.mbs.constants.Constants.RECOMMENDATION_EXCEPTION_MESSAGE;

@Service
@AllArgsConstructor
@Slf4j
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private final StudentRepository studentRepository;
    private final AdvisorRepository advisorRepository;

    private final AdvisorMapper advisorMapper;

    @Override
    public void recommendAdvisorsToStudent(final Integer studentId, final List<Integer> advisorIds) {
        if(recommendationRepository.findRecommendationEntityByStudentEntityId(studentId).size() >= 3 ||
                advisorIds.size() > 3) {

            log.error(RECOMMENDATION_EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(RECOMMENDATION_EXCEPTION_MESSAGE);
        }

        advisorIds.forEach(advisorId -> {
            if(recommendationRepository
                    .findRecommendationEntityByAdvisorEntityIdAndStudentEntityId(advisorId, studentId) != null ||
                    recommendationRepository.findRecommendationEntityByStudentEntityId(studentId).size() >= 3) {

                log.error(RECOMMENDATION_EXCEPTION_MESSAGE);
                throw new IllegalArgumentException(RECOMMENDATION_EXCEPTION_MESSAGE);
            }

            addRecommendation(studentId, advisorId);
        });
    }

    @Override
    public List<Recommendation> getRecommendationsByStudentId(final Integer studentId) {
        final List<RecommendationEntity> recommendations = recommendationRepository.findAllByStudentEntityId(studentId);

        return recommendations
                .stream()
                .map(this::fillRecommendationResponse)
                .collect(Collectors.toList());
    }

    private void addRecommendation(final Integer studentId, final Integer advisorId) {
        final StudentEntity studentEntity = studentRepository.findStudentEntityById(studentId);
        final AdvisorEntity advisorEntity = advisorRepository.findAdvisorEntityById(advisorId);

        final RecommendationEntity recommendationEntity = new RecommendationEntity();

        recommendationEntity.setAdvisorEntity(advisorEntity);
        recommendationEntity.setStudentEntity(studentEntity);

        recommendationRepository.save(recommendationEntity);
    }

    private Recommendation fillRecommendationResponse(final RecommendationEntity recommendationEntity) {
        final Recommendation recommendation = new Recommendation();

        recommendation.setId(recommendationEntity.getId());
        recommendation.setAdvisor(advisorMapper.advisorEntityToAdvisorDTO(recommendationEntity.getAdvisorEntity()));

        return recommendation;
    }
}
