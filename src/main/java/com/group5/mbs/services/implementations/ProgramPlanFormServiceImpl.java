package com.group5.mbs.services.implementations;

import com.group5.mbs.api.dtos.ProgramPlanForm;
import com.group5.mbs.api.mappers.ProgramPlanFormMapper;
import com.group5.mbs.entities.ProgramPlanFormEntity;
import com.group5.mbs.entities.ProgramPlanFormStatus;
import com.group5.mbs.entities.StudentEntity;
import com.group5.mbs.repositories.ProgramPlanFormRepository;
import com.group5.mbs.repositories.StudentRepository;
import com.group5.mbs.services.interfaces.ProgramPlanFormService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.group5.mbs.constants.Constants.*;

@Service
@AllArgsConstructor
@Slf4j
public class ProgramPlanFormServiceImpl implements ProgramPlanFormService {

    private final ProgramPlanFormRepository programPlanFormRepository;
    private final StudentRepository studentRepository;

    private final ProgramPlanFormMapper programPlanFormMapper;

    @Override
    public void storeProgramPlanForm(final Integer studentId, final MultipartFile file) {
        final StudentEntity studentEntity = studentRepository.findStudentEntityById(studentId);

        if(studentEntity.getProgramPlanFormEntity() != null) {
            log.error(FORM_EXISTS_EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(FORM_EXISTS_EXCEPTION_MESSAGE);
        }

        final ProgramPlanFormEntity formEntity = new ProgramPlanFormEntity();

        try {
            formEntity.setFileName(file.getOriginalFilename());
            formEntity.setContentType(file.getContentType());
            formEntity.setProgramPlanFormStatus(ProgramPlanFormStatus.NOT_COMPLETED);
            formEntity.setForm(file.getBytes());
            programPlanFormRepository.save(formEntity);

            studentEntity.setProgramPlanFormEntity(formEntity);
            studentRepository.save(studentEntity);
        } catch (IOException e) {
            log.error(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public ProgramPlanForm getProgramPlanFormByStudentId(final Integer studentId) {
        final StudentEntity studentEntity = studentRepository.findStudentEntityById(studentId);
        final ProgramPlanFormEntity formEntity = studentEntity.getProgramPlanFormEntity();

        if(formEntity == null) {
            log.error(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return programPlanFormMapper.programPlanFormEntityToProgramPlanFormDTO(formEntity);
    }

    @Override
    public ProgramPlanForm getProgramPlanFormByFormId(final Integer formId) {
        final ProgramPlanFormEntity formEntity = programPlanFormRepository.findProgramPlanFormEntityById(formId);

        if(formEntity == null) {
            log.error(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return programPlanFormMapper.programPlanFormEntityToProgramPlanFormDTO(formEntity);
    }

    @Override
    public void deleteProgramPlanFormByStudentId(final Integer studentId) {
        final StudentEntity studentEntity = studentRepository.findStudentEntityById(studentId);
        final ProgramPlanFormEntity programPlanFormEntity = studentEntity.getProgramPlanFormEntity();

        if(programPlanFormEntity == null) {
            log.error(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        studentEntity.setProgramPlanFormEntity(null);
        studentRepository.save(studentEntity);

        programPlanFormRepository.delete(programPlanFormEntity);
    }

    @Override
    public void updateProgramPlanForm(final Integer studentId, final MultipartFile file) {
        final StudentEntity studentEntity = studentRepository.findStudentEntityById(studentId);

        if(studentEntity == null) {
            log.error(STUDENT_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(STUDENT_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        final ProgramPlanFormEntity programPlanFormEntity = studentEntity.getProgramPlanFormEntity();

        if(programPlanFormEntity == null) {
            log.error(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        if(programPlanFormEntity.getProgramPlanFormStatus() == ProgramPlanFormStatus.COMPLETED) {
            log.error(STATUS_COMPLETED_EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(STATUS_COMPLETED_EXCEPTION_MESSAGE);
        }

        deleteProgramPlanFormByStudentId(studentId);
        storeProgramPlanForm(studentId, file);
    }

    @Override
    public void completeProgramPlanForm(final Integer studentId) {
        final StudentEntity studentEntity = studentRepository.findStudentEntityById(studentId);

        if(studentEntity == null) {
            log.error(STUDENT_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(STUDENT_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        final ProgramPlanFormEntity programPlanFormEntity = studentEntity.getProgramPlanFormEntity();

        if(programPlanFormEntity == null) {
            log.error(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        if(programPlanFormEntity.getProgramPlanFormStatus() == ProgramPlanFormStatus.COMPLETED) {
            log.error(STATUS_COMPLETED_EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(STATUS_COMPLETED_EXCEPTION_MESSAGE);
        }

        programPlanFormEntity.setProgramPlanFormStatus(ProgramPlanFormStatus.COMPLETED);
        programPlanFormRepository.save(programPlanFormEntity);

        studentEntity.setProgramPlanFormEntity(programPlanFormEntity);
        studentRepository.save(studentEntity);
    }

}
