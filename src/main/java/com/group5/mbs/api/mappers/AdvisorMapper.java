package com.group5.mbs.api.mappers;

import com.group5.mbs.api.dtos.Advisor;
import com.group5.mbs.entities.AdvisorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdvisorMapper {

    AdvisorMapper INSTANCE = Mappers.getMapper(AdvisorMapper.class);

    Advisor advisorEntityToAdvisorDTO(AdvisorEntity advisorEntity);

    AdvisorEntity advisorDTOToAdvisorEntity(Advisor advisor);

}
