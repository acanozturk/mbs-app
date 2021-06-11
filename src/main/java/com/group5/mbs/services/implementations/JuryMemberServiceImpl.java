package com.group5.mbs.services.implementations;

import com.group5.mbs.api.dtos.JuryMember;
import com.group5.mbs.entities.JuryMemberEntity;
import com.group5.mbs.repositories.JuryMemberRepository;
import com.group5.mbs.services.interfaces.JuryMemberService;
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
public class JuryMemberServiceImpl implements JuryMemberService {

    private final JuryMemberRepository juryMemberRepository;

    @Override
    public List<JuryMember> getAllJuryMembers() {

        return juryMemberRepository
                .findAll()
                .stream()
                .map(this::fillJuryMemberResponse)
                .collect(Collectors.toList());
    }

    @Override
    public JuryMember getJuryMemberById(final Integer juryMemberId) {
        final JuryMemberEntity juryMemberEntity = juryMemberRepository.findJuryMemberEntityById(juryMemberId);

        if(juryMemberEntity == null) {
            log.error(JURY_NOT_FOUND_EXCEPTION_MESSAGE);
            throw new ResourceNotFoundException(JURY_NOT_FOUND_EXCEPTION_MESSAGE);
        }

        return fillJuryMemberResponse(juryMemberEntity);
    }

    @Override
    public JuryMember getJuryMemberByEmailAndPassword(final String juryMemberMail, final String password) {
        final JuryMemberEntity juryMemberEntity = juryMemberRepository.findByEmailAndPassword(juryMemberMail, password);

        if(juryMemberEntity == null) {
            log.error(INPUT_MISMATCH_EXCEPTION_MESSAGE);
            throw new InputMismatchException(INPUT_MISMATCH_EXCEPTION_MESSAGE);
        }

        return fillJuryMemberResponse(juryMemberEntity);
    }

    private JuryMember fillJuryMemberResponse(final JuryMemberEntity juryMemberEntity) {
        final JuryMember juryMember = new JuryMember();

        juryMember.setId(juryMemberEntity.getId());
        juryMember.setUserType(juryMemberEntity.getUserType());
        juryMember.setFirstName(juryMemberEntity.getFirstName());
        juryMember.setLastName(juryMemberEntity.getLastName());

        return juryMember;
    }
}
