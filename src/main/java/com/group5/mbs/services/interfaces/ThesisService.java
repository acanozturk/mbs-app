package com.group5.mbs.services.interfaces;

import com.group5.mbs.api.dtos.Thesis;
import org.springframework.web.multipart.MultipartFile;

public interface ThesisService {

    void storeThesis(Integer studentId, MultipartFile file);

    Thesis getThesisByStudentId(Integer studentId);

    Thesis getThesisByThesisId(Integer thesisId);

    void deleteThesisByStudentId(Integer studentId);

    void updateThesisTopic(Integer studentId, String thesisTopic);

    void extendDeadline(Integer studentId);

}
