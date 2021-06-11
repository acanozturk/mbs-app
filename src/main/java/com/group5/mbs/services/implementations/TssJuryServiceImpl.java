package com.group5.mbs.services.implementations;

import com.group5.mbs.api.dtos.TssJury;
import com.group5.mbs.api.mappers.JuryMemberMapper;
import com.group5.mbs.entities.*;
import com.group5.mbs.repositories.JuryMemberRepository;
import com.group5.mbs.repositories.StudentRepository;
import com.group5.mbs.repositories.TssJuryRepository;
import com.group5.mbs.services.interfaces.StudentService;
import com.group5.mbs.services.interfaces.SubmitThesisService;
import com.group5.mbs.services.interfaces.TssJuryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static com.group5.mbs.constants.Constants.*;

@Service
@AllArgsConstructor
@Slf4j
public class TssJuryServiceImpl implements TssJuryService {

    private final TssJuryRepository tssJuryRepository;
    private final StudentRepository studentRepository;
    private final JuryMemberRepository juryMemberRepository;

    private final JuryMemberMapper juryMemberMapper;

    private final StudentService studentService;
    private final SubmitThesisService submitThesisService;

    @Override
    public void setTssJuryMembers(final Integer studentId, final List<Integer> juryMemberIds) {
        if(submitThesisService.getSubmittedThesisByStudentId(studentId) == null) {
            log.error(TSS_THESIS_EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(TSS_THESIS_EXCEPTION_MESSAGE);
        }

        if(tssJuryRepository.findAllByStudentEntityId(studentId).size() >= 3 || juryMemberIds.size() > 3) {
            log.error(TSS_JURY_EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(TSS_JURY_EXCEPTION_MESSAGE);
        }

        juryMemberIds.forEach(juryMemberId -> {
            if(tssJuryRepository
                    .findTssJuryEntityByJuryMemberEntityIdAndStudentEntityId(juryMemberId, studentId) != null ||
                        tssJuryRepository.findAllByStudentEntityId(studentId).size() > 3) {

                log.error(TSS_JURY_EXCEPTION_MESSAGE);
                throw new IllegalArgumentException(TSS_JURY_EXCEPTION_MESSAGE);
            }

            addJuryMember(studentId, juryMemberId);
        });
    }

    @Override
    public List<TssJury> getTssJuryByStudentId(final Integer studentId) {
        final List<TssJuryEntity> tssJuryEntities = tssJuryRepository.findAllByStudentEntityId(studentId);

        return tssJuryEntities
                .stream()
                .map(this::fillTssJuryResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<TssJury> getTssJuryByJuryMemberId(final Integer juryMemberId) {
        final List<TssJuryEntity> tssJuryEntities = tssJuryRepository.findAllByJuryMemberEntityId(juryMemberId);

        return tssJuryEntities
                .stream()
                .map(this::fillTssJuryResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void tssJuryMemberRejectStudent(final Integer juryMemberId, final Integer studentId) {
        final TssJuryEntity tssJuryEntity =
                tssJuryRepository.findTssJuryEntityByJuryMemberEntityIdAndStudentEntityId(juryMemberId, studentId);

        if(tssJuryEntity == null) {
            log.error(STUDENT_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(STUDENT_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        tssJuryRepository.delete(tssJuryEntity);
    }

    @Override
    public void tssJuryMemberEvaluateThesis(final Integer juryMemberId, final Integer studentId, final String status) {
        final TssJuryEntity tssJuryEntity =
                tssJuryRepository.findTssJuryEntityByJuryMemberEntityIdAndStudentEntityId(juryMemberId, studentId);

        if(tssJuryEntity == null) {
            log.error(STUDENT_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(STUDENT_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        final TssJuryStatus tssJuryStatus = studentRepository.findStudentEntityById(studentId).getTssJuryStatus();

        if(tssJuryStatus == TssJuryStatus.NOT_APPROVED) {
            log.error(TSS_NOT_APPROVED_EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(TSS_NOT_APPROVED_EXCEPTION_MESSAGE);
        }

        final ThesisTssStatus thesisTssStatus = ThesisTssStatus.valueOf(status);

        if(tssJuryEntity.getThesisTssStatus() == ThesisTssStatus.ACCEPTED
                || tssJuryEntity.getThesisTssStatus() == ThesisTssStatus.REJECTED) {

            log.error(SUBMISSION_EXISTS_EXCEPTION_MESSAGE);
            throw new IllegalArgumentException(SUBMISSION_EXISTS_EXCEPTION_MESSAGE);
        }

        tssJuryEntity.setThesisTssStatus(thesisTssStatus);
        tssJuryRepository.save(tssJuryEntity);
    }

    private void addJuryMember(final Integer studentId, final Integer juryMemberId) {
        final StudentEntity studentEntity = studentRepository.findStudentEntityById(studentId);
        final JuryMemberEntity juryMemberEntity = juryMemberRepository.findJuryMemberEntityById(juryMemberId);

        final TssJuryEntity tssJuryEntity = new TssJuryEntity();
        final LocalDate date = studentEntity.getThesisEntity().getDeadline();

        tssJuryEntity.setStudentEntity(studentEntity);
        tssJuryEntity.setJuryMemberEntity(juryMemberEntity);
        tssJuryEntity.setDate(date);
        tssJuryEntity.setThesisTssStatus(ThesisTssStatus.NOT_EVALUATED);
        tssJuryRepository.save(tssJuryEntity);
    }

    private TssJury fillTssJuryResponse(final TssJuryEntity tssJuryEntity) {
        final TssJury tssJury = new TssJury();

        tssJury.setId(tssJuryEntity.getId());
        tssJury.setStudent(studentService.getStudentById(tssJuryEntity.getStudentEntity().getId()));
        tssJury.setJuryMember(juryMemberMapper.juryMemberEntityToJuryMemberDTO(tssJuryEntity.getJuryMemberEntity()));
        tssJury.setDate(tssJuryEntity.getDate().toString());
        tssJury.setThesisTssStatus(tssJuryEntity.getThesisTssStatus());

        return tssJury;
    }
}
