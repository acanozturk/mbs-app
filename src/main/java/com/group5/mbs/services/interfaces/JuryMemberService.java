package com.group5.mbs.services.interfaces;

import com.group5.mbs.api.dtos.JuryMember;

import java.util.List;

public interface JuryMemberService {

    List<JuryMember> getAllJuryMembers();

    JuryMember getJuryMemberById(Integer juryMemberId);

    JuryMember getJuryMemberByEmailAndPassword(String juryMemberMail, String password);
}
