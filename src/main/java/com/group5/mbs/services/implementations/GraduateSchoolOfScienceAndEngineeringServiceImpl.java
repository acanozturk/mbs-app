package com.group5.mbs.services.implementations;

import com.group5.mbs.api.dtos.GraduateSchoolOfScienceAndEngineering;
import com.group5.mbs.api.dtos.TssJury;
import com.group5.mbs.entities.*;
import com.group5.mbs.repositories.FormRepository;
import com.group5.mbs.repositories.GraduateSchoolOfScienceAndEngineeringRepository;
import com.group5.mbs.repositories.StudentRepository;
import com.group5.mbs.services.interfaces.GraduateSchoolOfScienceAndEngineeringService;
import com.group5.mbs.services.interfaces.TssJuryService;
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
public class GraduateSchoolOfScienceAndEngineeringServiceImpl implements GraduateSchoolOfScienceAndEngineeringService {

    private final GraduateSchoolOfScienceAndEngineeringRepository graduateSchoolOfScienceAndEngineeringRepository;
    private final StudentRepository studentRepository;
    private final FormRepository formRepository;

    private final TssJuryService tssJuryService;

    @Override
    public List<GraduateSchoolOfScienceAndEngineering> getAllGses() {

        return graduateSchoolOfScienceAndEngineeringRepository
                .findAll()
                .stream()
                .map(this::fillGsesResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GraduateSchoolOfScienceAndEngineering getGsesById(final Integer gsesId) {
        final GraduateSchoolOfScienceAndEngineeringEntity gsesEntity = graduateSchoolOfScienceAndEngineeringRepository
                        .findGraduateSchoolOfScienceAndEngineeringEntityById(gsesId);

        if(gsesEntity == null) {
            log.error(GSES_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(GSES_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return fillGsesResponse(gsesEntity);
    }

    @Override
    public GraduateSchoolOfScienceAndEngineering getGsesByEmailAndPassword(final String gsesMail,
            final String password) {

        final GraduateSchoolOfScienceAndEngineeringEntity gsesEntity =
                graduateSchoolOfScienceAndEngineeringRepository.findByEmailAndPassword(gsesMail, password);

        if(gsesEntity == null) {
            log.error(INPUT_MISMATCH_EXCEPTION_MESSAGE);
            throw new InputMismatchException(INPUT_MISMATCH_EXCEPTION_MESSAGE);
        }

       return fillGsesResponse(gsesEntity);
    }

    @Override
    public void gsesManageStudent(final Integer studentId) {
        final StudentEntity studentEntity = studentRepository.findStudentEntityById(studentId);
        final ThesisEntity thesisEntity = studentEntity.getThesisEntity();
        final FormEntity formGD = formRepository.findFormEntityByStudentEntityIdAndFormType(studentId, FormType.GD);
        final FormEntity formES = formRepository.findFormEntityByStudentEntityIdAndFormType(studentId, FormType.ES);

        if(thesisEntity == null || formGD == null || formES == null) {
            log.error(GSES_FORM_EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(GSES_FORM_EXCEPTION_MESSAGE);
        }

        if(studentEntity.getCpga() < 3.00 ) {
            log.error(GSES_CGPA_EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(GSES_CGPA_EXCEPTION_MESSAGE);
        }

        final List<TssJury> tssJuries = tssJuryService.getTssJuryByStudentId(studentId);

        tssJuries.forEach(jury -> {
            if(jury == null || jury.getThesisTssStatus() != ThesisTssStatus.ACCEPTED) {
                log.error(GSES_JURY_EXCEPTION_MESSAGE);
                throw new IllegalArgumentException(GSES_JURY_EXCEPTION_MESSAGE);
            }
        });

        if(studentEntity.getGraduationStatus() == GraduationStatus.APPROVED) {
            log.error(GSES_GRADUATED_EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(GSES_GRADUATED_EXCEPTION_MESSAGE);
        }

        studentEntity.setGraduationStatus(GraduationStatus.APPROVED);
        studentRepository.save(studentEntity);
    }

    private GraduateSchoolOfScienceAndEngineering fillGsesResponse(
            final GraduateSchoolOfScienceAndEngineeringEntity gsesEntity) {

        final GraduateSchoolOfScienceAndEngineering gses = new GraduateSchoolOfScienceAndEngineering();

        gses.setId(gsesEntity.getId());
        gses.setUserType(gsesEntity.getUserType());
        gses.setFirstName(gsesEntity.getFirstName());
        gses.setLastName(gsesEntity.getLastName());

        return gses;
    }
}
