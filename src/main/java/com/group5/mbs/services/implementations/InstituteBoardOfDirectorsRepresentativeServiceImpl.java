package com.group5.mbs.services.implementations;

import com.group5.mbs.api.dtos.InstituteBoardOfDirectorsRepresentative;
import com.group5.mbs.entities.*;
import com.group5.mbs.repositories.FormRepository;
import com.group5.mbs.repositories.InstituteBoardOfDirectorsRepresentativeRepository;
import com.group5.mbs.repositories.StudentRepository;
import com.group5.mbs.repositories.TssJuryRepository;
import com.group5.mbs.services.interfaces.InstituteBoardOfDirectorsRepresentativeService;
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
public class InstituteBoardOfDirectorsRepresentativeServiceImpl
        implements InstituteBoardOfDirectorsRepresentativeService {

    private final InstituteBoardOfDirectorsRepresentativeRepository instituteBoardOfDirectorsRepresentativeRepository;
    private final StudentRepository studentRepository;
    private final TssJuryRepository tssJuryRepository;
    private final FormRepository formRepository;

    @Override
    public List<InstituteBoardOfDirectorsRepresentative> getAllIbdrs() {

        return instituteBoardOfDirectorsRepresentativeRepository
                .findAll()
                .stream()
                .map(this::fillIbdrResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InstituteBoardOfDirectorsRepresentative getIbdrById(final Integer ibdrId) {
        final InstituteBoardOfDirectorsRepresentativeEntity ibdrEntity =
                instituteBoardOfDirectorsRepresentativeRepository
                        .findInstituteBoardOfDirectorsRepresentativeEntityById(ibdrId);

        if(ibdrEntity == null) {
            log.error(IBDR_NOT_FOUND_EXCEPTION_MESSAGE);
           throw new ResourceNotFoundException(IBDR_NOT_FOUND_EXCEPTION_MESSAGE);
       }

       return fillIbdrResponse(ibdrEntity);
    }

    @Override
    public InstituteBoardOfDirectorsRepresentative getIbdrByEmailAndPassword(final String ibdrMail,
            final String password) {

        final InstituteBoardOfDirectorsRepresentativeEntity ibdrEntity =
                instituteBoardOfDirectorsRepresentativeRepository.findByEmailAndPassword(ibdrMail, password);

        if(ibdrEntity == null) {
            log.error(INPUT_MISMATCH_EXCEPTION_MESSAGE);
            throw new InputMismatchException(INPUT_MISMATCH_EXCEPTION_MESSAGE);
        }

        return fillIbdrResponse(ibdrEntity);
    }

    @Override
    public void evaluateAdvisorAndThesisTopic(final Integer studentId) {
        final StudentEntity studentEntity = studentRepository.findStudentEntityById(studentId);
        final ProgramPlanFormEntity programPlanFormEntity = studentEntity.getProgramPlanFormEntity();
        final String thesisTopic = studentEntity.getThesisTopic();
        final ThesisTopicStatus thesisTopicStatus = studentEntity.getThesisTopicStatus();

        final FormEntity formTD = formRepository.findFormEntityByStudentEntityIdAndFormType(studentId, FormType.TD);

        if(thesisTopic == null || thesisTopicStatus == ThesisTopicStatus.APPROVED) {
            log.error(IBDR_THESIS_EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(IBDR_THESIS_EXCEPTION_MESSAGE);
        }

        if(programPlanFormEntity == null ||
                programPlanFormEntity.getProgramPlanFormStatus() == ProgramPlanFormStatus.NOT_COMPLETED) {

            log.error(IBDR_PPF_EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(IBDR_PPF_EXCEPTION_MESSAGE);
        }

        if(formTD == null) {
            log.error(FORM_TD_EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(FORM_TD_EXCEPTION_MESSAGE);
        }

        studentEntity.setThesisTopicStatus(ThesisTopicStatus.APPROVED);
        studentRepository.save(studentEntity);
    }

    @Override
    public void evaluateTssJury(final Integer studentId) {
        final StudentEntity studentEntity = studentRepository.findStudentEntityById(studentId);
        final List<TssJuryEntity> tssJuryEntities = tssJuryRepository.findAllByStudentEntityId(studentId);

        final ThesisEntity thesisEntity = studentEntity.getThesisEntity();

        final FormEntity formTJ = formRepository.findFormEntityByStudentEntityIdAndFormType(studentId, FormType.TJ);
        final FormEntity formTJA = formRepository.findFormEntityByStudentEntityIdAndFormType(studentId, FormType.TJA);
        final FormEntity formDM = formRepository.findFormEntityByStudentEntityIdAndFormType(studentId, FormType.DM);

        if(tssJuryEntities.size() < 3 || studentEntity.getTssJuryStatus() == TssJuryStatus.APPROVED) {
            log.error(IBDR_TSS_EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(IBDR_TSS_EXCEPTION_MESSAGE);
        }

        if(thesisEntity == null || formTJ == null || formTJA == null || formDM == null) {
            log.error(IBDR_FORM_EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(IBDR_FORM_EXCEPTION_MESSAGE);
        }

        studentEntity.setTssJuryStatus(TssJuryStatus.APPROVED);
        studentRepository.save(studentEntity);
    }

    private InstituteBoardOfDirectorsRepresentative fillIbdrResponse(
            final InstituteBoardOfDirectorsRepresentativeEntity ibdrEntity) {

        final InstituteBoardOfDirectorsRepresentative ibdr = new InstituteBoardOfDirectorsRepresentative();

        ibdr.setId(ibdrEntity.getId());
        ibdr.setUserType(ibdrEntity.getUserType());
        ibdr.setFirstName(ibdrEntity.getFirstName());
        ibdr.setLastName(ibdrEntity.getLastName());

        return ibdr;
    }

}
