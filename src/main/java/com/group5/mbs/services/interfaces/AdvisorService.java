package com.group5.mbs.services.interfaces;

import com.group5.mbs.api.dtos.Advisor;

import java.util.List;

public interface AdvisorService {

    List<Advisor> getAllAdvisors();

    Advisor getAdvisorById(Integer advisorId);

    Advisor getAdvisorByEmailAndPassword(String advisorMail, String password);

}
