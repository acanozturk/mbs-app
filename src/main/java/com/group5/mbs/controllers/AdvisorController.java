package com.group5.mbs.controllers;

import com.group5.mbs.api.dtos.Advisor;
import com.group5.mbs.api.dtos.Proposal;
import com.group5.mbs.api.dtos.Student;
import com.group5.mbs.api.model.request.AdvisorApproveStudentsRequest;
import com.group5.mbs.api.model.request.AdvisorSetTssJuryRequest;
import com.group5.mbs.api.model.response.*;
import com.group5.mbs.services.interfaces.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AdvisorController {

    private final AdvisorService advisorService;
    private final StudentService studentService;
    private final ProposalService proposalService;
    private final TssJuryService tssJuryService;
    private final MailService mailService;

    @GetMapping("/mbs/advisors")
    public AdvisorListResponse getAllAdvisors() {
        final List<Advisor> allAdvisors = advisorService.getAllAdvisors();

        final AdvisorListResponse response = new AdvisorListResponse();

        response.setAdvisorList(allAdvisors);

        return response;
    }

    @GetMapping("/mbs/advisors/{advisorId}")
    public AdvisorResponse getAdvisorById(@PathVariable("advisorId") final Integer advisorId) {
        final Advisor advisor = advisorService.getAdvisorById(advisorId);

        final AdvisorResponse response = new AdvisorResponse();

        response.setAdvisor(advisor);

        return response;
    }

    @GetMapping("/mbs/advisors/getProposals/{advisorId}")
    public AdvisorGetProposalsResponse getProposals(@PathVariable("advisorId") final Integer advisorId) {
        final List<Proposal> proposals = proposalService.getProposalsByAdvisorId(advisorId);

        final AdvisorGetProposalsResponse response = new AdvisorGetProposalsResponse();

        response.setProposals(proposals);

        return response;
    }

    @PutMapping("/mbs/advisors/approveStudents/{advisorId}")
    public AdvisorApproveStudentsResponse approveStudents(@PathVariable("advisorId") final Integer advisorId,
            @RequestBody final AdvisorApproveStudentsRequest request) {

        final List<Integer> studentIds = request.getStudentIds();

        proposalService.advisorApproveStudents(advisorId, studentIds);

        final AdvisorApproveStudentsResponse response = new AdvisorApproveStudentsResponse();

        response.setSuccessMessage("Approved students.");

        mailService.sendMail("ceng316group5mbs@gmail.com", "Proposal Approved",
                "Approved proposals for " + studentService.getStudentNamesByStudentIds(studentIds) + ".");

        return response;
    }

    @GetMapping(("/mbs/advisors/getApprovedStudents/{advisorId}"))
    public StudentListResponse getApprovedStudents(@PathVariable("advisorId") final Integer advisorId) {
        final List<Student> students = studentService.getGetApprovedStudentsByTheAdvisor(advisorId);

        final StudentListResponse response = new StudentListResponse();

        response.setStudentList(students);

        return response;
    }

    @PostMapping("/mbs/advisors/setTssJury")
    public AdvisorSetTssJuryResponse setTssJury(@RequestBody final AdvisorSetTssJuryRequest request) {
        final Integer studentId = request.getStudentId();
        final List<Integer> juryMemberIds = request.getJuryMemberIds();

        tssJuryService.setTssJuryMembers(studentId, juryMemberIds);

        final AdvisorSetTssJuryResponse response = new AdvisorSetTssJuryResponse();

        response.setSuccessMessage("TSS jury has been set.");

        mailService.sendMail("ceng316group5mbs@gmail.com", "TSS Jury Set",
            "TSS jury set for " + studentService.getStudentNameByStudentId(studentId) + ".");

        return response;
    }

}
