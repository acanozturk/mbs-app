package com.group5.mbs.controllers;

import com.group5.mbs.api.dtos.JuryMember;
import com.group5.mbs.api.dtos.TssJury;
import com.group5.mbs.api.model.request.RejectStudentRequest;
import com.group5.mbs.api.model.request.TssEvaluateThesisRequest;
import com.group5.mbs.api.model.response.*;
import com.group5.mbs.services.implementations.JuryMemberServiceImpl;
import com.group5.mbs.services.interfaces.MailService;
import com.group5.mbs.services.interfaces.StudentService;
import com.group5.mbs.services.interfaces.TssJuryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class JuryMemberController {

    private final JuryMemberServiceImpl juryMemberService;
    private final TssJuryService tssJuryService;
    private final StudentService studentService;
    private final MailService mailService;

    @GetMapping("/mbs/juryMembers")
    public JuryMemberListResponse getAllJuryMembers() {
        final List<JuryMember> allJuryMembers = juryMemberService.getAllJuryMembers();

        final JuryMemberListResponse response = new JuryMemberListResponse();

        response.setJuryMemberList(allJuryMembers);

        return response;
    }

    @GetMapping("/mbs/juryMembers/{juryMemberId}")
    public JuryMemberResponse getJuryMemberById(@PathVariable("juryMemberId") final Integer juryMemberId) {
        final JuryMember juryMember = juryMemberService.getJuryMemberById(juryMemberId);

        final JuryMemberResponse response = new JuryMemberResponse();

        response.setJuryMember(juryMember);

        return response;
    }

    @GetMapping("mbs/juryMembers/getTssJury/{juryMemberId}")
    public JuryMemberTssJuryResponse getTssJuryByJuryId(@PathVariable("juryMemberId") final Integer juryMemberId) {
        final List<TssJury> tssJury = tssJuryService.getTssJuryByJuryMemberId(juryMemberId);

        final JuryMemberTssJuryResponse response = new JuryMemberTssJuryResponse();

        response.setTssJuryList(tssJury);

        return response;
    }

    @DeleteMapping("mbs/juryMembers/rejectStudent/{juryMemberId}")
    public DeleteResponse rejectStudent(@PathVariable("juryMemberId") final Integer juryMemberId,
            @RequestBody final RejectStudentRequest request) {

        final Integer studentId = request.getStudentId();

        tssJuryService.tssJuryMemberRejectStudent(juryMemberId, studentId);

        final DeleteResponse response = new DeleteResponse();

        response.setSuccessMessage("Student rejected.");

        mailService.sendMail("ceng316group5mbs@gmail.com", "Jury Member Rejection",
                "A jury member rejected  " + studentService.getStudentNameByStudentId(studentId) + ".");

        return response;
    }

    @PostMapping("mbs/juryMembers/evaluateThesis/{juryMemberId}")
    public TssEvaluateThesisResponse tssEvaluateThesis(@PathVariable("juryMemberId") final Integer juryMemberId,
            @RequestBody final TssEvaluateThesisRequest request) {

        final Integer studentId = request.getStudentId();
        final String status = request.getStatus();

        tssJuryService.tssJuryMemberEvaluateThesis(juryMemberId, studentId, status);

        final TssEvaluateThesisResponse response = new TssEvaluateThesisResponse();

        response.setSuccessMessage("Thesis evaluated as " + status);

        mailService.sendMail("ceng316group5mbs@gmail.com", "TSS Member Thesis Evaluation",
                "A TSS member evaluated thesis for  " + studentService.getStudentNameByStudentId(studentId) + ".");

        return response;
    }

}
