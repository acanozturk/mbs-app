package com.group5.mbs.controllers;

import com.group5.mbs.api.dtos.Form;
import com.group5.mbs.api.model.request.GetStudentFormRequest;
import com.group5.mbs.api.model.response.FileUploadResponse;
import com.group5.mbs.api.model.response.FormResponse;
import com.group5.mbs.services.interfaces.FormService;
import com.group5.mbs.services.interfaces.MailService;
import com.group5.mbs.services.interfaces.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;

@RestController
@AllArgsConstructor
public class FormController {

    private final FormService formService;
    private final StudentService studentService;
    private final MailService mailService;

    @PostMapping("mbs/forms/uploadForm/{type}/{studentId}")
    public FileUploadResponse uploadForm(@PathVariable("studentId") final Integer studentId,
            @PathVariable("type") final String type, @RequestParam("file") final MultipartFile file) {

        formService.storeForm(studentId, file, type.toUpperCase());

        final FileUploadResponse response = new FileUploadResponse();

        response.setSuccessMessage("Form uploaded successfully.");

        mailService.sendMail("ceng316group5mbs@gmail.com", "Form Uploaded",
                "Form " + type + " uploaded for " + studentService.getStudentNameByStudentId(studentId) + ".");

        return response;
    }
    
    @PostMapping("mbs/forms/getForm")
    public FormResponse getFormByStudentIdAndType(@RequestBody final GetStudentFormRequest request) {
        final Integer studentId = request.getStudentId();
        final String type = request.getType().toUpperCase();
        
        final Form form = formService.getFormByStudentIdAndType(studentId, type);

        final String url = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/mbs/forms/getForm/")
                .path(form.getId().toString())
                .toUriString();
        
        final FormResponse response = new FormResponse();
        
        response.setFileName(form.getFormName());
        response.setUrl(url);
        
        return response;
    }

    @GetMapping("mbs/forms/getForm/{formId}")
    public ResponseEntity<byte[]> getForm(@PathVariable final Integer formId) {
        final Form form = formService.getFormById(formId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(form.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + form.getFormName() + "\"")
                .body(form.getForm());
    }

    @GetMapping("mbs/forms/download/{formName}")
    public ResponseEntity<FileSystemResource> downloadForm(@PathVariable("formName") final String formName) {
        final File file = formService.downloadForm(formName + ".docx");

        final FileSystemResource resource = new FileSystemResource(file);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + formName + ".docx" + "\"")
                .body(resource);
    }
}
