package com.group5.mbs.api.mappers;

import com.group5.mbs.api.dtos.Form;
import com.group5.mbs.entities.FormEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FormMapper {

    FormMapper INSTANCE = Mappers.getMapper(FormMapper.class);

    Form formEntityToFormDTO(FormEntity formEntity);

    FormEntity formDTOToForm(Form form);

}
