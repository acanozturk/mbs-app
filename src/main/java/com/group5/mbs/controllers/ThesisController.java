package com.group5.mbs.controllers;

import com.group5.mbs.api.dtos.SubmitThesis;
import com.group5.mbs.api.dtos.Thesis;
import com.group5.mbs.api.model.request.DeadlineExtensionRequest;
import com.group5.mbs.api.model.request.SubmitThesisRequest;
import com.group5.mbs.api.model.request.UpdateThesisTopicRequest;
import com.group5.mbs.api.model.response.*;
import com.group5.mbs.services.interfaces.MailService;
import com.group5.mbs.services.interfaces.StudentService;
import com.group5.mbs.services.interfaces.SubmitThesisService;
import com.group5.mbs.services.interfaces.ThesisService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class ThesisController {

    private final ThesisService thesisService;
    private final StudentService studentService;
    private final SubmitThesisService submitThesisService;
    private final MailService mailService;

    @PostMapping("mbs/thesis/upload/{studentId}")
    public FileUploadResponse uploadThesis(@PathVariable("studentId") final Integer studentId,
            @RequestParam("file") final MultipartFile file) {

        thesisService.storeThesis(studentId, file);

        final FileUploadResponse response = new FileUploadResponse();

        response.setSuccessMessage("Thesis uploaded successfully.");

        mailService.sendMail("ceng316group5mbs@gmail.com", "Thesis Uploaded",
                "Thesis uploaded for " + studentService.getStudentNameByStudentId(studentId) + ".");

        return response;
    }

    @GetMapping("mbs/thesis/getByStudent/{studentId}")
    public ThesisResponse getThesisByStudentId(@PathVariable("studentId") final Integer studentId) {
        final Thesis thesis = thesisService.getThesisByStudentId(studentId);

        final String url = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/mbs/thesis/getThesis/")
                .path(thesis.getId().toString())
                .toUriString();

        final ThesisResponse response = new ThesisResponse();

        response.setFileName(thesis.getFileName());
        response.setThesisTopic(studentService.getStudentById(studentId).getThesisTopic());
        response.setDeadline(thesis.getDeadline());
        response.setUrl(url);

        return response;
    }

    @GetMapping("mbs/thesis/getThesis/{thesisId}")
    private ResponseEntity<byte[]> getThesisByThesisId(@PathVariable("thesisId") final Integer thesisId) {
        final Thesis thesis = thesisService.getThesisByThesisId(thesisId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(thesis.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + thesis.getFileName() + "\"")
                .body(thesis.getThesis());
    }

    @DeleteMapping("mbs/thesis/delete/{studentId}")
    public DeleteResponse deleteThesisByStudentId(@PathVariable("studentId") final Integer studentId) {
        thesisService.deleteThesisByStudentId(studentId);

        final DeleteResponse response = new DeleteResponse();

        response.setSuccessMessage("Thesis deleted successfully.");

        mailService.sendMail("ceng316group5mbs@gmail.com", "Thesis Deleted",
                "Thesis deleted for " + studentService.getStudentNameByStudentId(studentId) + ".");

        return response;
    }

    @PutMapping("/mbs/thesis/updateThesisTopic")
    public UpdateThesisTopicResponse updateThesisTopic(@Valid @RequestBody final UpdateThesisTopicRequest request) {
        final Integer studentId = request.getStudentId();
        final String thesisTopic = request.getThesisTopic();

        thesisService.updateThesisTopic(studentId, thesisTopic);

        final UpdateThesisTopicResponse response = new UpdateThesisTopicResponse();

        response.setSuccessMessage("Thesis topic has been updated to " + thesisTopic);

        mailService.sendMail("ceng316group5mbs@gmail.com", "Thesis Topic Updated",
                "Thesis topic updated for " + studentService.getStudentNameByStudentId(studentId) + ".");

        return response;
    }

    @PutMapping("/mbs/thesis/extendDeadline")
    public DeadlineExtensionResponse extendDeadline(@RequestBody final DeadlineExtensionRequest request) {
        final Integer studentId = request.getStudentId();

        thesisService.extendDeadline(studentId);

        final DeadlineExtensionResponse response = new DeadlineExtensionResponse();

        response.setSuccessMessage("Deadline due date is extended 1 week.");

        mailService.sendMail("ceng316group5mbs@gmail.com", "Deadline Extended",
                "Deadline extended for " + studentService.getStudentNameByStudentId(studentId) + ".");

        return response;
    }

    @PostMapping("/mbs/thesis/submitThesis")
    public SubmitThesisResponse submitThesis(@RequestBody final SubmitThesisRequest request) {
        final Integer studentId = request.getStudentId();

        submitThesisService.submitThesis(studentId);

        final SubmitThesisResponse response = new SubmitThesisResponse();

        response.setSuccessMessage("Thesis submitted.");

        mailService.sendMail("ceng316group5mbs@gmail.com", "Thesis Submitted",
                "Thesis submitted for " + studentService.getStudentNameByStudentId(studentId) + ".");

        return response;
    }

    @GetMapping("/mbs/thesis/getAllSubmissions")
    public AdvisorGetAllSubmissionsResponse getAllSubmissions() {
        final List<SubmitThesis> submissions = submitThesisService.getAllSubmissions();

        final AdvisorGetAllSubmissionsResponse response = new AdvisorGetAllSubmissionsResponse();

        response.setSubmissionList(submissions);

        return response;
    }

    @GetMapping("/mbs/thesis/getSubmittedThesis")
    public SubmittedThesisResponse getSubmittedThesisByStudentId(@RequestBody final SubmitThesisRequest request) {
        final Integer studentId = request.getStudentId();

        final SubmitThesis submitThesis = submitThesisService.getSubmittedThesisByStudentId(studentId);

        final SubmittedThesisResponse response = new SubmittedThesisResponse();

        response.setSubmitThesis(submitThesis);

        return response;
    }

}
