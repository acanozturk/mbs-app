package com.group5.mbs.services.implementations;

import com.group5.mbs.api.dtos.Proposal;
import com.group5.mbs.entities.AdvisorEntity;
import com.group5.mbs.entities.ProposalEntity;
import com.group5.mbs.entities.RecommendationEntity;
import com.group5.mbs.entities.StudentEntity;
import com.group5.mbs.repositories.AdvisorRepository;
import com.group5.mbs.repositories.ProposalRepository;
import com.group5.mbs.repositories.RecommendationRepository;
import com.group5.mbs.repositories.StudentRepository;
import com.group5.mbs.services.interfaces.ProposalService;
import com.group5.mbs.services.interfaces.StudentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.group5.mbs.constants.Constants.*;

@Service
@AllArgsConstructor
@Slf4j
public class ProposalServiceImpl implements ProposalService {

    private final ProposalRepository proposalRepository;
    private final AdvisorRepository advisorRepository;
    private final StudentRepository studentRepository;
    private final RecommendationRepository recommendationRepository;

    private final StudentService studentService;

    @Override
    public void proposeToAdvisors(final Integer studentId, final List<Integer> advisorIds) {
        if(proposalRepository.findAllByStudentEntityId(studentId).size() >= 3 || advisorIds.size() > 3) {
            log.error(PROPOSAL_EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(PROPOSAL_EXCEPTION_MESSAGE);
        }

        advisorIds.forEach(advisorId -> {
            if(proposalRepository.findProposalEntityByAdvisorEntityIdAndStudentEntityId(advisorId, studentId) != null ||
                    proposalRepository.findAllByStudentEntityId(studentId).size() >= 3) {

                log.error(PROPOSAL_EXCEPTION_MESSAGE);
                throw new IllegalArgumentException(PROPOSAL_EXCEPTION_MESSAGE);
            }

            addProposal(studentId, advisorId);

            final RecommendationEntity recommendationEntity = recommendationRepository
                    .findRecommendationEntityByAdvisorEntityIdAndStudentEntityId(advisorId, studentId);

            recommendationRepository.delete(recommendationEntity);
        });
    }

    @Override
    public List<Proposal> getProposalsByAdvisorId(final Integer advisorId) {
        final List<ProposalEntity> proposalEntities = proposalRepository.findAllByAdvisorEntityId(advisorId);

        return proposalEntities
                .stream()
                .map(this::fillProposalResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void advisorApproveStudents(final Integer advisorId, final List<Integer> studentIds) {
        final AdvisorEntity advisorEntity = advisorRepository.findAdvisorEntityById(advisorId);

        if(advisorEntity == null) {
            log.error(ADVISOR_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(ADVISOR_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        final List<StudentEntity> studentEntities = studentIds
                .stream()
                .map(studentRepository::findStudentEntityById)
                .collect(Collectors.toList());

        studentEntities.forEach(studentEntity -> {
            if(studentEntity.getProgramPlanFormEntity() == null) {
                log.error(PROGRAM_PLAN_FORM_DOES_NOT_EXIST);
                throw new IllegalArgumentException(PROGRAM_PLAN_FORM_DOES_NOT_EXIST);
            }
            if(studentEntity.getAdvisorEntity() != null) {
                log.error(ADVISOR_EXISTS_EXCEPTION_MESSAGE);
                throw new IllegalArgumentException(ADVISOR_EXISTS_EXCEPTION_MESSAGE);
            }

            studentEntity.setAdvisorEntity(advisorEntity);
        });

        advisorEntity.setStudentEntities(studentEntities);

        advisorRepository.save(advisorEntity);
        studentRepository.saveAll(studentEntities);

        studentIds.forEach(studentId -> {
            final List<ProposalEntity> proposalEntities = proposalRepository.findAllByStudentEntityId(studentId);

            final List<RecommendationEntity> recommendationEntities =
                    recommendationRepository.findRecommendationEntityByStudentEntityId(studentId);

            proposalRepository.deleteAll(proposalEntities);
            recommendationRepository.deleteAll(recommendationEntities);
        });
    }

    private void addProposal(final Integer studentId, final Integer advisorId) {
        final StudentEntity studentEntity = studentRepository.findStudentEntityById(studentId);
        final AdvisorEntity advisorEntity = advisorRepository.findAdvisorEntityById(advisorId);

        final ProposalEntity proposalEntity = new ProposalEntity();

        proposalEntity.setAdvisorEntity(advisorEntity);
        proposalEntity.setStudentEntity(studentEntity);

        proposalRepository.save(proposalEntity);
    }

    private Proposal fillProposalResponse(final ProposalEntity proposalEntity) {
        final Proposal proposal = new Proposal();

        proposal.setId(proposalEntity.getId());
        proposal.setStudent(studentService.getStudentById(proposalEntity.getStudentEntity().getId()));

        return proposal;
    }

}
