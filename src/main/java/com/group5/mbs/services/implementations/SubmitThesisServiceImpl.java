package com.group5.mbs.services.implementations;

import com.group5.mbs.api.dtos.SubmitThesis;
import com.group5.mbs.entities.StudentEntity;
import com.group5.mbs.entities.SubmitThesisEntity;
import com.group5.mbs.entities.ThesisTopicStatus;
import com.group5.mbs.repositories.StudentRepository;
import com.group5.mbs.repositories.SubmittedThesisRepository;
import com.group5.mbs.services.interfaces.StudentService;
import com.group5.mbs.services.interfaces.SubmitThesisService;
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
public class SubmitThesisServiceImpl implements SubmitThesisService {

    private final SubmittedThesisRepository submittedThesisRepository;
    private final StudentRepository studentRepository;

    private final StudentService studentService;

    @Override
    public void submitThesis(final Integer studentId) {
        final StudentEntity studentEntity = studentRepository.findStudentEntityById(studentId);

        if(studentEntity.getThesisEntity() == null ||
                studentEntity.getThesisTopicStatus() == ThesisTopicStatus.NOT_APPROVED) {

            log.error(THESIS_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(THESIS_EXCEPTION_MESSAGE);
        }

        if(submittedThesisRepository.findSubmitThesisEntityByStudentEntityId(studentId) != null) {
            log.error(SUBMISSION_EXISTS_EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(SUBMISSION_EXISTS_EXCEPTION_MESSAGE);
        }

        final SubmitThesisEntity submitThesisEntity = new SubmitThesisEntity();

        submitThesisEntity.setStudentEntity(studentEntity);
        submittedThesisRepository.save(submitThesisEntity);
    }

    @Override
    public List<SubmitThesis> getAllSubmissions() {

        return submittedThesisRepository
                .findAll()
                .stream()
                .map(this::fillSubmittedThesisResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SubmitThesis getSubmittedThesisByStudentId(final Integer studentId) {
        final SubmitThesisEntity thesis = submittedThesisRepository.findSubmitThesisEntityByStudentEntityId(studentId);

        if(thesis == null) {
            log.error(TSS_THESIS_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(TSS_THESIS_EXCEPTION_MESSAGE);
        }

        return fillSubmittedThesisResponse(thesis);
    }

    private SubmitThesis fillSubmittedThesisResponse(final SubmitThesisEntity thesisEntity) {
        final SubmitThesis submitThesis = new SubmitThesis();

        submitThesis.setId(thesisEntity.getId());
        submitThesis.setStudent(studentService.getStudentById(thesisEntity.getStudentEntity().getId()));

        return submitThesis;
    }
}
