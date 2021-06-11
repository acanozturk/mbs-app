package com.group5.mbs.services.interfaces;

import com.group5.mbs.api.dtos.ProgramPlanForm;
import org.springframework.web.multipart.MultipartFile;

public interface ProgramPlanFormService {

    void storeProgramPlanForm(Integer studentId, MultipartFile file);

    ProgramPlanForm getProgramPlanFormByStudentId(Integer studentId);

    ProgramPlanForm getProgramPlanFormByFormId(Integer formId);

    void deleteProgramPlanFormByStudentId(Integer studentId);

    void updateProgramPlanForm(Integer studentId, MultipartFile file);

    void completeProgramPlanForm(Integer studentId);

}
