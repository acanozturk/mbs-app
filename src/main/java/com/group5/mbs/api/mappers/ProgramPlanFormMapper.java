package com.group5.mbs.api.mappers;

import com.group5.mbs.api.dtos.ProgramPlanForm;
import com.group5.mbs.entities.ProgramPlanFormEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProgramPlanFormMapper {

    ProgramPlanFormMapper INSTANCE = Mappers.getMapper(ProgramPlanFormMapper.class);

    ProgramPlanForm programPlanFormEntityToProgramPlanFormDTO(ProgramPlanFormEntity programPlanFormEntity);

    ProgramPlanFormEntity programPlanFormDTOToProgramPlanFormEntity(ProgramPlanForm programPlanForm);

}
