package com.group5.mbs.api.mappers;

import com.group5.mbs.api.dtos.InstituteBoardOfDirectorsRepresentative;
import com.group5.mbs.entities.InstituteBoardOfDirectorsRepresentativeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InstituteBoardOfDirectorsRepresentativeMapper {

    InstituteBoardOfDirectorsRepresentativeMapper INSTANCE =
            Mappers.getMapper(InstituteBoardOfDirectorsRepresentativeMapper.class);

    InstituteBoardOfDirectorsRepresentative ibdrEntityToIbdrDTO(InstituteBoardOfDirectorsRepresentativeEntity
                                                                        instituteBoardOfDirectorsRepresentativeEntity);

    InstituteBoardOfDirectorsRepresentativeEntity ibdrDTOToIbdrEntity(InstituteBoardOfDirectorsRepresentative
                                                                              instituteBoardOfDirectorsRepresentative);

}
