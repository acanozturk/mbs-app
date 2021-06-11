package com.group5.mbs.api.mappers;

import com.group5.mbs.api.dtos.Thesis;
import com.group5.mbs.entities.ThesisEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ThesisMapper {

    ThesisMapper INSTANCE = Mappers.getMapper(ThesisMapper.class);

    Thesis thesisEntityToThesisDTO(ThesisEntity thesisEntity);

    ThesisEntity thesisDTOToThesisEntity(Thesis thesis);

}
