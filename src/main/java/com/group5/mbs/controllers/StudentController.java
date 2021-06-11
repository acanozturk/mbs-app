package com.group5.mbs.controllers;

import com.group5.mbs.api.dtos.Recommendation;
import com.group5.mbs.api.dtos.Student;
import com.group5.mbs.api.dtos.TssJury;
import com.group5.mbs.api.model.request.StudentProposeToAdvisorsRequest;
import com.group5.mbs.api.model.response.*;
import com.group5.mbs.services.interfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final RecommendationService recommendationService;
    private final ProposalService proposalService;
    private final TssJuryService tssJuryService;
    private final MailService mailService;

    @GetMapping("/mbs/students")
    public StudentListResponse getAllStudents() {
        final List<Student> allStudents = studentService.getAllStudents();

        final StudentListResponse response = new StudentListResponse();

        response.setStudentList(allStudents);

        return response;
    }

    @GetMapping("/mbs/students/{studentId}")
    public StudentResponse getStudentById(@PathVariable("studentId") final Integer studentId) {
        final Student student = studentService.getStudentById(studentId);

        final StudentResponse response = new StudentResponse();

        response.setStudent(student);

        return response;
    }

    @GetMapping("/mbs/students/getRecommendedAdvisors/{studentId}")
    public StudentGetRecommendedAdvisorsResponse getRecommendedAdvisors(
            @PathVariable("studentId") final Integer studentId) {

        final List<Recommendation> recommendations = recommendationService.getRecommendationsByStudentId(studentId);

        final StudentGetRecommendedAdvisorsResponse response = new StudentGetRecommendedAdvisorsResponse();

        response.setRecommendations(recommendations);

        return response;
    }

    @PutMapping("/mbs/students/proposeAdvisors/{studentId}")
    public StudentProposeToAdvisorsResponse propose(@RequestBody final StudentProposeToAdvisorsRequest request) {
        final Integer studentId = request.getStudentId();
        final List<Integer> advisorIds = request.getAdvisorIds();

        proposalService.proposeToAdvisors(studentId, advisorIds);

        final StudentProposeToAdvisorsResponse response = new StudentProposeToAdvisorsResponse();

        response.setSuccessMessage("Proposal successful.");

        mailService.sendMail("ceng316group5mbs@gmail.com", "New Proposal",
                "A new proposal made by " + studentService.getStudentNameByStudentId(studentId) + ".");

        return response;
    }

    @GetMapping("mbs/students/getTssJury/{studentId}")
    public StudentTssJuryResponse getTssJuryByStudentId(@PathVariable("studentId") final Integer studentId) {
        final List<TssJury> tssJury = tssJuryService.getTssJuryByStudentId(studentId);

        final StudentTssJuryResponse response = new StudentTssJuryResponse();

        response.setTssJuryList(tssJury);

        return response;
    }
}
