package com.group5.mbs.services.interfaces;

import com.group5.mbs.api.dtos.SubmitThesis;

import java.util.List;

public interface SubmitThesisService {

    void submitThesis(Integer studentId);

    List<SubmitThesis> getAllSubmissions();

    SubmitThesis getSubmittedThesisByStudentId(Integer studentId);

}
