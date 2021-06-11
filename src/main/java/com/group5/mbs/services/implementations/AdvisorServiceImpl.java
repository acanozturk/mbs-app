package com.group5.mbs.services.implementations;

import com.group5.mbs.api.dtos.Advisor;
import com.group5.mbs.entities.*;
import com.group5.mbs.repositories.*;
import com.group5.mbs.services.interfaces.AdvisorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

import static com.group5.mbs.constants.Constants.*;

@Service
@AllArgsConstructor
@Slf4j
public class AdvisorServiceImpl implements AdvisorService {

    private final AdvisorRepository advisorRepository;

    @Override
    public List<Advisor> getAllAdvisors() {

        return advisorRepository
                .findAll()
                .stream()
                .map(this::fillAdvisorResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Advisor getAdvisorById(final Integer advisorId) {
        final AdvisorEntity advisorEntity = advisorRepository.findAdvisorEntityById(advisorId);

        if(advisorEntity == null) {
            log.error(ADVISOR_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(ADVISOR_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return fillAdvisorResponse(advisorEntity);
    }

    @Override
    public Advisor getAdvisorByEmailAndPassword(final String advisorMail, final String password) {
        final AdvisorEntity advisorEntity = advisorRepository.findByEmailAndPassword(advisorMail, password);

        if(advisorEntity == null) {
            log.error(INPUT_MISMATCH_EXCEPTION_MESSAGE);
            throw new InputMismatchException(INPUT_MISMATCH_EXCEPTION_MESSAGE);
        }

        return fillAdvisorResponse(advisorEntity);
    }

    private Advisor fillAdvisorResponse(final AdvisorEntity advisorEntity) {
        final Advisor advisor = new Advisor();

        advisor.setId(advisorEntity.getId());
        advisor.setUserType(advisorEntity.getUserType());
        advisor.setFirstName(advisorEntity.getFirstName());
        advisor.setLastName(advisorEntity.getLastName());

        return advisor;
    }
}
