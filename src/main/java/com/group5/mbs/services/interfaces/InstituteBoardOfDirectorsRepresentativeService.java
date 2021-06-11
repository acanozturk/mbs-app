package com.group5.mbs.services.interfaces;

import com.group5.mbs.api.dtos.InstituteBoardOfDirectorsRepresentative;

import java.util.List;

public interface InstituteBoardOfDirectorsRepresentativeService {

    List<InstituteBoardOfDirectorsRepresentative> getAllIbdrs();

    InstituteBoardOfDirectorsRepresentative getIbdrById(Integer ibdrId);

    InstituteBoardOfDirectorsRepresentative getIbdrByEmailAndPassword(String ibdrMail, String password);

    void evaluateAdvisorAndThesisTopic(Integer studentId);

    void evaluateTssJury(Integer studentId);
}
