package com.group5.mbs.services.implementations;

import com.group5.mbs.api.dtos.Form;
import com.group5.mbs.api.mappers.FormMapper;
import com.group5.mbs.entities.FormEntity;
import com.group5.mbs.entities.FormType;
import com.group5.mbs.entities.StudentEntity;
import com.group5.mbs.repositories.FormRepository;
import com.group5.mbs.repositories.StudentRepository;
import com.group5.mbs.services.interfaces.FormService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;

import static com.group5.mbs.constants.Constants.*;

@Service
@AllArgsConstructor
@Slf4j
public class FormServiceImpl implements FormService {

    private final FormRepository formRepository;
    private final StudentRepository studentRepository;

    private final FormMapper formMapper;

    @Override
    public void storeForm(final Integer studentId, final MultipartFile file, final String type) {
        final StudentEntity studentEntity = studentRepository.findStudentEntityById(studentId);
        final FormType formType = FormType.valueOf(type);
        final FormEntity form = formRepository.findFormEntityByStudentEntityIdAndFormType(studentId, formType);

        if(form != null) {
            formRepository.delete(form);
        }

        final FormEntity formEntity = new FormEntity();

        try {
            formEntity.setStudentEntity(studentEntity);
            formEntity.setFormName(file.getOriginalFilename());
            formEntity.setContentType(file.getContentType());
            formEntity.setFormType(formType);
            formEntity.setForm(file.getBytes());
            formRepository.save(formEntity);
        } catch (IOException e) {
            log.error(UPLOAD_FAILED_EXCEPTION_MESSAGE);
            throw new InputMismatchException(UPLOAD_FAILED_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public Form getFormByStudentIdAndType(final Integer studentId, final String type) {
        final FormType formType = FormType.valueOf(type);
        final FormEntity formEntity = formRepository.findFormEntityByStudentEntityIdAndFormType(studentId,formType);

        if(formEntity == null) {
            log.error(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return formMapper.formEntityToFormDTO(formEntity);
    }

    @Override
    public Form getFormById(final Integer formId) {
        final FormEntity formEntity = formRepository.findFormEntityById(formId);

        if(formEntity == null) {
            log.error(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return formMapper.formEntityToFormDTO(formEntity);
    }

    @Override
    public File downloadForm(final String formName) {

        return new File(DOWNLOAD_DIR + formName);
    }
}
