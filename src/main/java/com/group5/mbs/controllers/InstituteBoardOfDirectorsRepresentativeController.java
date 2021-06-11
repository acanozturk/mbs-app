package com.group5.mbs.controllers;

import com.group5.mbs.api.dtos.InstituteBoardOfDirectorsRepresentative;
import com.group5.mbs.api.model.request.IbdrEvaluateRequest;
import com.group5.mbs.api.model.response.IbdrEvaluateResponse;
import com.group5.mbs.api.model.response.IbdrListResponse;
import com.group5.mbs.api.model.response.IbdrResponse;
import com.group5.mbs.services.interfaces.InstituteBoardOfDirectorsRepresentativeService;
import com.group5.mbs.services.interfaces.MailService;
import com.group5.mbs.services.interfaces.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class InstituteBoardOfDirectorsRepresentativeController {

    private final InstituteBoardOfDirectorsRepresentativeService instituteBoardOfDirectorsRepresentativeService;
    private final StudentService studentService;
    private final MailService mailService;

    @GetMapping("/mbs/ibdrs")
    public IbdrListResponse getAllIbdrs() {
        final List<InstituteBoardOfDirectorsRepresentative> ibdrList =
                instituteBoardOfDirectorsRepresentativeService.getAllIbdrs();

        final IbdrListResponse response = new IbdrListResponse();

        response.setInstituteBoardOfDirectorsRepresentativeList(ibdrList);

        return response;
    }

    @GetMapping("/mbs/ibdrs/{ibdrId}")
    public IbdrResponse getIbdrById(@PathVariable("ibdrId") final Integer ibdrId) {
        final InstituteBoardOfDirectorsRepresentative ibdr =
                instituteBoardOfDirectorsRepresentativeService.getIbdrById(ibdrId);

        final IbdrResponse response = new IbdrResponse();

        response.setInstituteBoardOfDirectorsRepresentative(ibdr);

        return response;
    }

    @PutMapping("/mbs/ibdrs/evaluateAdvisorAndThesisTopic")
    public IbdrEvaluateResponse evaluateAdvisorAndThesisTopic(@RequestBody final IbdrEvaluateRequest request) {
        final Integer studentId = request.getStudentId();

        instituteBoardOfDirectorsRepresentativeService.evaluateAdvisorAndThesisTopic(studentId);

        final IbdrEvaluateResponse response = new IbdrEvaluateResponse();

        response.setSuccessMessage("Thesis topic evaluation successful.");

        mailService.sendMail("ceng316group5mbs@gmail.com", "Thesis Topic Approved",
                "Thesis topic approved for " + studentService.getStudentNameByStudentId(studentId) + ".");

        return response;
    }

    @PutMapping("/mbs/ibdrs/evaluateTssJury")
    public IbdrEvaluateResponse evaluateTssJury(@RequestBody final IbdrEvaluateRequest request) {
        final Integer studentId = request.getStudentId();

        instituteBoardOfDirectorsRepresentativeService.evaluateTssJury(studentId);

        final IbdrEvaluateResponse response = new IbdrEvaluateResponse();

        response.setSuccessMessage("Tss jury evaluation successful.");

        mailService.sendMail("ceng316group5mbs@gmail.com", "TSS Jury Approved",
                "TSS jury approved for " + studentService.getStudentNameByStudentId(studentId) + ".");

        return response;
    }

}
