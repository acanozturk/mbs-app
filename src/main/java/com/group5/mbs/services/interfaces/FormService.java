package com.group5.mbs.services.interfaces;

import com.group5.mbs.api.dtos.Form;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FormService {

    void storeForm(Integer studentId, MultipartFile file, String type);

    Form getFormByStudentIdAndType(Integer studentId, String type);

    Form getFormById(Integer formId);

    File downloadForm(String formName);

}
