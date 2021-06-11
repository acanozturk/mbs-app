package com.group5.mbs.api.mappers;

import com.group5.mbs.api.dtos.SubmitThesis;
import com.group5.mbs.entities.SubmitThesisEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubmitThesisMapper {

    SubmitThesisMapper INSTANCE = Mappers.getMapper(SubmitThesisMapper.class);

    SubmitThesis submitThesisEntityToSubmitThesis(SubmitThesisEntity submitThesisEntity);

    SubmitThesisEntity submitThesisToSubmitThesisEntity(SubmitThesis submitThesis);

}
