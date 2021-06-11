package com.group5.mbs.services.interfaces;

import com.group5.mbs.api.dtos.TssJury;

import java.util.List;

public interface TssJuryService {

    void setTssJuryMembers(Integer studentId, List<Integer> juryMemberIds);

    List<TssJury> getTssJuryByStudentId(Integer studentId);

    List<TssJury> getTssJuryByJuryMemberId(Integer juryMemberId);

    void tssJuryMemberRejectStudent(Integer juryMemberId, Integer studentId);

    void tssJuryMemberEvaluateThesis(Integer juryMemberId, Integer studentId, String status);
}
