package com.group5.mbs.services.interfaces;

import com.group5.mbs.api.dtos.Proposal;

import java.util.List;

public interface ProposalService {

    void proposeToAdvisors(Integer studentId, List<Integer> advisorIds);

    List<Proposal> getProposalsByAdvisorId(Integer advisorId);

    void advisorApproveStudents(Integer advisorId, List<Integer> studentIds);

}
