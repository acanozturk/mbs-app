package com.group5.mbs.api.mappers;

import com.group5.mbs.api.dtos.JuryMember;
import com.group5.mbs.entities.JuryMemberEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JuryMemberMapper {

    JuryMemberMapper INSTANCE = Mappers.getMapper(JuryMemberMapper.class);

    JuryMember juryMemberEntityToJuryMemberDTO(JuryMemberEntity juryMemberEntity);

    JuryMemberEntity juryMemberDTOToJuryMemberEntity(JuryMember juryMember);

}
