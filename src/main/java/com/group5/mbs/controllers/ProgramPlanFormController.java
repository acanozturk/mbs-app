package com.group5.mbs.controllers;

import com.group5.mbs.api.dtos.ProgramPlanForm;
import com.group5.mbs.api.model.response.CompleteProgramPlanFormResponse;
import com.group5.mbs.api.model.response.DeleteResponse;
import com.group5.mbs.api.model.response.FileUploadResponse;
import com.group5.mbs.api.model.response.ProgramPlanFormResponse;
import com.group5.mbs.services.interfaces.MailService;
import com.group5.mbs.services.interfaces.ProgramPlanFormService;
import com.group5.mbs.services.interfaces.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@AllArgsConstructor
public class ProgramPlanFormController {

    private final ProgramPlanFormService programPlanFormService;
    private final StudentService studentService;
    private final MailService mailService;

    @PostMapping("mbs/programPlanForm/upload/{studentId}")
    public FileUploadResponse uploadProgramPlanForm(@PathVariable("studentId") final Integer studentId,
            @RequestParam("file") final MultipartFile file) {

        programPlanFormService.storeProgramPlanForm(studentId, file);

        final FileUploadResponse response = new FileUploadResponse();

        response.setSuccessMessage("Program plan form uploaded successfully.");

        mailService.sendMail("ceng316group5mbs@gmail.com", "Program Plan Form Uploaded",
                "Program plan form uploaded for " + studentService.getStudentNameByStudentId(studentId) + ".");

        return response;
    }

    @GetMapping("mbs/programPlanForm/getByStudent/{studentId}")
    public ProgramPlanFormResponse getProgramPlanFormByStudentId(@PathVariable("studentId") final Integer studentId) {
        final ProgramPlanForm programPlanForm = programPlanFormService.getProgramPlanFormByStudentId(studentId);

        final String url = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/mbs/programPlanForm/getForm/")
                .path(programPlanForm.getId().toString())
                .toUriString();

        final ProgramPlanFormResponse response = new ProgramPlanFormResponse();

        response.setFileName(programPlanForm.getFileName());
        response.setProgramPlanFormStatus(programPlanForm.getProgramPlanFormStatus());
        response.setUrl(url);

        return response;
    }

    @GetMapping("mbs/programPlanForm/getForm/{formId}")
    private ResponseEntity<byte[]> getProgramPlanFormByFormId(@PathVariable("formId") final Integer formId) {
        final ProgramPlanForm form = programPlanFormService.getProgramPlanFormByFormId(formId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(form.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + form.getFileName() + "\"")
                .body(form.getForm());
    }

    @PutMapping("/mbs/programPlanForm/update/{studentId}")
    public FileUploadResponse updateProgramPlanForm(@PathVariable("studentId") final Integer studentId,
            @RequestParam("file") final MultipartFile file) {

        programPlanFormService.updateProgramPlanForm(studentId, file);

        final FileUploadResponse response = new FileUploadResponse();

        response.setSuccessMessage("Program plan form updated.");

        mailService.sendMail("ceng316group5mbs@gmail.com", "Program Plan Form Updated",
                "Program plan form updated for " + studentService.getStudentNameByStudentId(studentId) + ".");

        return response;
    }

    @DeleteMapping("mbs/programPlanForm/delete/{studentId}")
    public DeleteResponse deleteProgramPlanFormByStudentId(@PathVariable("studentId") final Integer studentId) {
        programPlanFormService.deleteProgramPlanFormByStudentId(studentId);

        final DeleteResponse response = new DeleteResponse();

        response.setSuccessMessage("Form deleted successfully.");

        mailService.sendMail("ceng316group5mbs@gmail.com", "Program Plan Form Deleted",
                "Program plan form deleted for " + studentService.getStudentNameByStudentId(studentId) + ".");

        return response;
    }

    @PutMapping("/mbs/programPlanForm/complete/{studentId}")
    public CompleteProgramPlanFormResponse completeProgramPlanForm(@PathVariable("studentId") final Integer studentId) {
        programPlanFormService.completeProgramPlanForm(studentId);

        final CompleteProgramPlanFormResponse response = new CompleteProgramPlanFormResponse();

        response.setSuccessMessage("Program plan form status is set to completed.");

        mailService.sendMail("ceng316group5mbs@gmail.com", "Program Plan Form Completed",
                "Program plan form completed for " + studentService.getStudentNameByStudentId(studentId) + ".");

        return response;
    }

}
