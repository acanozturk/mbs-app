package com.group5.mbs.services.implementations;

import com.group5.mbs.api.dtos.Student;
import com.group5.mbs.api.mappers.AdvisorMapper;
import com.group5.mbs.api.mappers.ProgramPlanFormMapper;
import com.group5.mbs.api.mappers.ThesisMapper;
import com.group5.mbs.entities.StudentEntity;
import com.group5.mbs.repositories.StudentRepository;
import com.group5.mbs.services.interfaces.StudentService;
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
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final AdvisorMapper advisorMapper;
    private final ThesisMapper thesisMapper;
    private final ProgramPlanFormMapper programPlanFormMapper;

    @Override
    public List<Student> getAllStudents() {

        return studentRepository
                .findAll()
                .stream()
                .map(this::fillStudentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Student getStudentById(final Integer studentId) {
        final StudentEntity studentEntity = studentRepository.findStudentEntityById(studentId);

        if(studentEntity == null) {
            log.error(ADVISOR_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(ADVISOR_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return fillStudentResponse(studentEntity);
    }

    @Override
    public Student getStudentByEmailAndPassword(final String studentMail, final String password) {
        final StudentEntity studentEntity = studentRepository.findByEmailAndPassword(studentMail, password);

        if(studentEntity == null) {
            log.error(INPUT_MISMATCH_EXCEPTION_MESSAGE);
            throw new InputMismatchException(INPUT_MISMATCH_EXCEPTION_MESSAGE);
        }

        return fillStudentResponse(studentEntity);
    }

    @Override
    public List<Student> getAllStudentsWithNoAdvisor() {
        final List<StudentEntity> studentEntities = studentRepository.findByAdvisorEntityIsNull();

        return studentEntities
                .stream()
                .map(this::fillStudentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<Student> getGetApprovedStudentsByTheAdvisor(final Integer advisorId) {
        final List<StudentEntity> studentEntities = studentRepository.findAllByAdvisorEntityId(advisorId);

        return studentEntities
                .stream()
                .map(this::fillStudentResponse)
                .collect(Collectors.toList());
    }

    @Override
    public String getStudentNameByStudentId(final Integer studentId) {

        return studentRepository.findStudentEntityById(studentId).getFirstName()
                + " " + studentRepository.findStudentEntityById(studentId).getLastName();
    }

    @Override
    public String getStudentNamesByStudentIds(final List<Integer> studentIds) {
        final List<String> namesList = studentIds
                .stream()
                .map(studentId -> studentRepository.findStudentEntityById(studentId).getFirstName()
                        + " " + studentRepository.findStudentEntityById(studentId).getLastName())
                .collect(Collectors.toList());

        return String.join(", ", namesList);
    }

    private Student fillStudentResponse(final StudentEntity studentEntity) {
        final Student student = new Student();

        student.setId(studentEntity.getId());
        student.setUserType(studentEntity.getUserType());
        student.setFirstName(studentEntity.getFirstName());
        student.setLastName(studentEntity.getLastName());
        student.setAdvisor(advisorMapper.advisorEntityToAdvisorDTO(studentEntity.getAdvisorEntity()));
        student.setCpga(studentEntity.getCpga());
        student.setThesisTopic(studentEntity.getThesisTopic());
        student.setThesisTopicStatus(studentEntity.getThesisTopicStatus());
        student.setThesis(thesisMapper.thesisEntityToThesisDTO(studentEntity.getThesisEntity()));
        student.setProgramPlanForm(programPlanFormMapper.programPlanFormEntityToProgramPlanFormDTO(
                studentEntity.getProgramPlanFormEntity()));
        student.setTssJuryStatus(studentEntity.getTssJuryStatus());
        student.setGraduationStatus(studentEntity.getGraduationStatus());

        return student;
    }

}
