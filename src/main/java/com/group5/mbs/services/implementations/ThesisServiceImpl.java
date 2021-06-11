package com.group5.mbs.services.implementations;

import com.group5.mbs.api.dtos.Thesis;
import com.group5.mbs.api.mappers.ThesisMapper;
import com.group5.mbs.entities.*;
import com.group5.mbs.repositories.StudentRepository;
import com.group5.mbs.repositories.ThesisRepository;
import com.group5.mbs.repositories.TssJuryRepository;
import com.group5.mbs.services.interfaces.ThesisService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.group5.mbs.constants.Constants.*;

@Service
@AllArgsConstructor
@Slf4j
public class ThesisServiceImpl implements ThesisService {

    private final ThesisRepository thesisRepository;
    private final StudentRepository studentRepository;
    private final TssJuryRepository tssJuryRepository;

    private final ThesisMapper thesisMapper;

    @Override
    public void storeThesis(final Integer studentId, final MultipartFile file) {
        final StudentEntity studentEntity = studentRepository.findStudentEntityById(studentId);

        if(studentEntity.getThesisEntity() != null) {
            log.error(THESIS_EXISTS_EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(THESIS_EXISTS_EXCEPTION_MESSAGE);
        }

        final ThesisEntity thesisEntity = new ThesisEntity();

        try {
            thesisEntity.setFileName(file.getOriginalFilename());
            thesisEntity.setContentType(file.getContentType());
            thesisEntity.setDeadline(LocalDate.of(2021, 6, 15));
            thesisEntity.setThesis(file.getBytes());
            thesisEntity.setPlagiarismRatio(getPlagiarismRatio());
            thesisRepository.save(thesisEntity);

            studentEntity.setThesisEntity(thesisEntity);
            studentEntity.setTssJuryStatus(TssJuryStatus.NOT_APPROVED);
            studentRepository.save(studentEntity);
        } catch (IOException e) {
            log.error(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public Thesis getThesisByStudentId(final Integer studentId) {
        final StudentEntity studentEntity = studentRepository.findStudentEntityById(studentId);
        final ThesisEntity thesisEntity = studentEntity.getThesisEntity();

        if(thesisEntity == null) {
            log.error(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return thesisMapper.thesisEntityToThesisDTO(thesisEntity);
    }

    @Override
    public Thesis getThesisByThesisId(final Integer thesisId) {
        final ThesisEntity thesisEntity = thesisRepository.findThesisEntityById(thesisId);

        if(thesisEntity == null) {
            log.error(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return thesisMapper.thesisEntityToThesisDTO(thesisEntity);
    }

    @Override
    public void deleteThesisByStudentId(Integer studentId) {
        final StudentEntity studentEntity = studentRepository.findStudentEntityById(studentId);
        final ThesisEntity thesisEntity = studentEntity.getThesisEntity();

        if(thesisEntity == null) {
            log.error(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        studentEntity.setThesisEntity(null);
        studentRepository.save(studentEntity);

        thesisRepository.delete(thesisEntity);
    }

    @Override
    public void updateThesisTopic(final Integer studentId, final String thesisTopic) {
        final StudentEntity studentEntity = studentRepository.findStudentEntityById(studentId);

        if(studentEntity == null) {
            log.error(STUDENT_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(STUDENT_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        studentEntity.setThesisTopic(thesisTopic);
        studentEntity.setThesisTopicStatus(ThesisTopicStatus.NOT_APPROVED);
        studentRepository.save(studentEntity);
    }

    @Override
    public void extendDeadline(final Integer studentId) {
        final StudentEntity studentEntity = studentRepository.findStudentEntityById(studentId);

        if(studentEntity == null) {
            log.error(STUDENT_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(STUDENT_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        final ThesisEntity thesisEntity = studentEntity.getThesisEntity();

        if(thesisEntity == null) {
            log.error(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(FILE_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        thesisEntity.setDeadline(thesisEntity.getDeadline().plusDays(7L));
        thesisRepository.save(thesisEntity);

        studentEntity.setThesisEntity(thesisEntity);
        studentRepository.save(studentEntity);

        final List<TssJuryEntity> tssJuryEntities = tssJuryRepository.findAllByStudentEntityId(studentId);

        tssJuryEntities.forEach(juryEntity -> juryEntity.setDate(studentEntity.getThesisEntity().getDeadline()));
        tssJuryRepository.saveAll(tssJuryEntities);
    }

    private Integer getPlagiarismRatio() {

        return ThreadLocalRandom.current().nextInt(1, 22);
    }

}
