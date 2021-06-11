package com.group5.mbs.repositories;

import com.group5.mbs.entities.JuryMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JuryMemberRepository extends JpaRepository<JuryMemberEntity, Integer> {

    JuryMemberEntity findJuryMemberEntityById(Integer juryMemberId);

    JuryMemberEntity findByEmailAndPassword(String email, String password);

}
