package com.group5.mbs.controllers;

import com.group5.mbs.api.dtos.Advisor;
import com.group5.mbs.api.dtos.DepartmentalBoardRepresentative;
import com.group5.mbs.api.dtos.Student;
import com.group5.mbs.api.model.request.DbrRecommendAdvisorRequest;
import com.group5.mbs.api.model.response.*;
import com.group5.mbs.services.interfaces.DepartmentalBoardRepresentativeService;
import com.group5.mbs.services.interfaces.MailService;
import com.group5.mbs.services.interfaces.RecommendationService;
import com.group5.mbs.services.interfaces.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DepartmentalBoardRepresentativeController {

    private final DepartmentalBoardRepresentativeService departmentalBoardRepresentativeService;
    private final StudentService studentService;
    private final RecommendationService recommendationService;
    private final MailService mailService;

    @GetMapping("/mbs/dbrs")
    public DbrListResponse getAllDbrs() {
        final List<DepartmentalBoardRepresentative> allDbrs = departmentalBoardRepresentativeService.getAllDbrs();

        final DbrListResponse response = new DbrListResponse();

        response.setDepartmentalBoardRepresentativeList(allDbrs);

        return response;
    }

    @GetMapping("/mbs/dbrs/{dbrId}")
    public DbrResponse getDbrById(@PathVariable("dbrId") final Integer dbrId) {
        final DepartmentalBoardRepresentative dbr = departmentalBoardRepresentativeService.getDbrById(dbrId);

        final DbrResponse response = new DbrResponse();

        response.setDepartmentalBoardRepresentative(dbr);

        return response;
    }

    @GetMapping("/mbs/dbrs/getStudentWithNoAdvisor")
    public StudentListResponse getStudentWithNoAdvisor() {
        final List<Student> studentsWithNoAdvisor = departmentalBoardRepresentativeService.findStudentsWithNoAdvisor();

        final StudentListResponse response = new StudentListResponse();

        response.setStudentList(studentsWithNoAdvisor);

        return response;
    }

    @GetMapping("/mbs/dbrs/getAdvisors")
    public AdvisorListResponse getAdvisors() {
        final List<Advisor> advisors = departmentalBoardRepresentativeService.getAdvisors();

        final AdvisorListResponse response = new AdvisorListResponse();

        response.setAdvisorList(advisors);

        return response;
    }

    @PutMapping("/mbs/dbrs/recommendAdvisor")
    public DbrRecommendAdvisorsResponse recommend(@RequestBody final DbrRecommendAdvisorRequest request) {
        final Integer studentId = request.getStudentId();
        final List<Integer> advisorIds = request.getAdvisorIds();

        final DbrRecommendAdvisorsResponse response = new DbrRecommendAdvisorsResponse();

        recommendationService.recommendAdvisorsToStudent(studentId, advisorIds);

        response.setSuccessMessage("Recommendation successful.");

        mailService.sendMail("ceng316group5mbs@gmail.com", "Recommended Advisors",
                "Recommended advisors to " + studentService.getStudentNameByStudentId(studentId) + ".");

        return response;
    }

}
