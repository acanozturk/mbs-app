package com.group5.mbs.services.implementations;

import com.group5.mbs.api.dtos.User;
import com.group5.mbs.entities.UserType;
import com.group5.mbs.services.interfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final AdvisorService advisorService;
    private final DepartmentalBoardRepresentativeService departmentalBoardRepresentativeService;
    private final GraduateSchoolOfScienceAndEngineeringService graduateSchoolOfScienceAndEngineeringService;
    private final InstituteBoardOfDirectorsRepresentativeService instituteBoardOfDirectorsRepresentativeService;
    private final JuryMemberService juryMemberService;
    private final StudentService studentService;

    public User userLoginRequest(final String email, final String password, final UserType userType) {
        final User user;

        switch (userType) {
            case ADVISOR:
                user = advisorService.getAdvisorByEmailAndPassword(email, password);

                break;
            case DBR:
                user = departmentalBoardRepresentativeService.getDbrByEmailAndPassword(email, password);

                break;
            case GSES:
                user = graduateSchoolOfScienceAndEngineeringService.getGsesByEmailAndPassword(email, password);

                break;
            case IBDR:
                user = instituteBoardOfDirectorsRepresentativeService.getIbdrByEmailAndPassword(email, password);

                break;
            case JURY:
                user = juryMemberService.getJuryMemberByEmailAndPassword(email, password);

                break;
            case STUDENT:
                user = studentService.getStudentByEmailAndPassword(email, password);

                break;
            default:
                user = null;
        }

        return user;
    }

}
