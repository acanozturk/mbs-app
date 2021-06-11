package com.group5.mbs.services.implementations;

import com.group5.mbs.api.dtos.Advisor;
import com.group5.mbs.api.dtos.DepartmentalBoardRepresentative;
import com.group5.mbs.api.dtos.Student;
import com.group5.mbs.entities.DepartmentalBoardRepresentativeEntity;
import com.group5.mbs.repositories.DepartmentalBoardRepresentativeRepository;
import com.group5.mbs.services.interfaces.AdvisorService;
import com.group5.mbs.services.interfaces.DepartmentalBoardRepresentativeService;
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
public class DepartmentalBoardRepresentativeServiceImpl implements DepartmentalBoardRepresentativeService {

    private final DepartmentalBoardRepresentativeRepository departmentalBoardRepresentativeRepository;

    private final StudentService studentService;
    private final AdvisorService advisorService;

    @Override
    public List<DepartmentalBoardRepresentative> getAllDbrs() {

        return departmentalBoardRepresentativeRepository
                .findAll()
                .stream()
                .map(this::fillDbrResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentalBoardRepresentative getDbrById(final Integer dbrId) {
        final DepartmentalBoardRepresentativeEntity dbrEntity =
                departmentalBoardRepresentativeRepository.findDepartmentalBoardRepresentativeEntityById(dbrId);

        if(dbrEntity == null) {
            log.error(DBR_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(DBR_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return fillDbrResponse(dbrEntity);
    }

    @Override
    public DepartmentalBoardRepresentative getDbrByEmailAndPassword(final String dbrMail, final String password) {
        final DepartmentalBoardRepresentativeEntity dbrEntity =
                departmentalBoardRepresentativeRepository.findByEmailAndPassword(dbrMail, password);

        if(dbrEntity == null) {
            log.error(INPUT_MISMATCH_EXCEPTION_MESSAGE);
            throw new InputMismatchException(INPUT_MISMATCH_EXCEPTION_MESSAGE);
        }

        return fillDbrResponse(dbrEntity);
    }

    @Override
    public List<Student> findStudentsWithNoAdvisor() {

        return studentService.getAllStudentsWithNoAdvisor();
    }

    @Override
    public List<Advisor> getAdvisors() {

        return advisorService.getAllAdvisors();
    }

    private DepartmentalBoardRepresentative fillDbrResponse(final DepartmentalBoardRepresentativeEntity dbrEntity) {
        final DepartmentalBoardRepresentative dbr = new DepartmentalBoardRepresentative();

        dbr.setId(dbrEntity.getId());
        dbr.setUserType(dbrEntity.getUserType());
        dbr.setFirstName(dbrEntity.getFirstName());
        dbr.setLastName(dbrEntity.getLastName());

        return dbr;
    }
}
