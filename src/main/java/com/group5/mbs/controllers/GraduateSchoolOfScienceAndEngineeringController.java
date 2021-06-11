package com.group5.mbs.controllers;

import com.group5.mbs.api.dtos.GraduateSchoolOfScienceAndEngineering;
import com.group5.mbs.api.model.request.GsesEvaluateRequest;
import com.group5.mbs.api.model.response.GsesEvaluateResponse;
import com.group5.mbs.api.model.response.GsesListResponse;
import com.group5.mbs.api.model.response.GsesResponse;
import com.group5.mbs.services.implementations.GraduateSchoolOfScienceAndEngineeringServiceImpl;
import com.group5.mbs.services.interfaces.MailService;
import com.group5.mbs.services.interfaces.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class GraduateSchoolOfScienceAndEngineeringController {

    private final GraduateSchoolOfScienceAndEngineeringServiceImpl graduateSchoolOfScienceAndEngineeringService;
    private final StudentService studentService;
    private final MailService mailService;

    @GetMapping("/mbs/gses")
    public GsesListResponse getAllGses() {
        final List<GraduateSchoolOfScienceAndEngineering> gsesList =
                graduateSchoolOfScienceAndEngineeringService.getAllGses();

        final GsesListResponse response = new GsesListResponse();

        response.setGraduateSchoolOfScienceAndEngineeringList(gsesList);

        return response;
    }

    @GetMapping("/mbs/gses/{gsesId}")
    public GsesResponse getGsesById(@PathVariable("gsesId") final Integer gsesId) {
        final GraduateSchoolOfScienceAndEngineering gses =
                graduateSchoolOfScienceAndEngineeringService.getGsesById(gsesId);

        final GsesResponse response = new GsesResponse();

        response.setGraduateSchoolOfScienceAndEngineering(gses);

        return response;
    }

    @PutMapping("/mbs/gses/manageStudent")
    public GsesEvaluateResponse gsesManageStudent(@RequestBody final GsesEvaluateRequest request) {
        final Integer studentId = request.getStudentId();

        graduateSchoolOfScienceAndEngineeringService.gsesManageStudent(studentId);

        final GsesEvaluateResponse response = new GsesEvaluateResponse();

        response.setSuccessMessage("Evaluation successful.");

        mailService.sendMail("ceng316group5mbs@gmail.com", "Graduation Accepted",
                "Graduation accepted for " + studentService.getStudentNameByStudentId(studentId) + ".");

        return response;
    }

}
