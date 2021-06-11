package com.group5.mbs.api.mappers;

import com.group5.mbs.api.dtos.DepartmentalBoardRepresentative;
import com.group5.mbs.entities.DepartmentalBoardRepresentativeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentalBoardRepresentativeMapper {

    DepartmentalBoardRepresentativeMapper INSTANCE = Mappers.getMapper(DepartmentalBoardRepresentativeMapper.class);

    DepartmentalBoardRepresentative dbrEntityToDbrDTO(DepartmentalBoardRepresentativeEntity
                                                              departmentalBoardRepresentativeEntity);

    DepartmentalBoardRepresentativeEntity dbrDTOToDbrEntity(DepartmentalBoardRepresentative
                                                                    departmentalBoardRepresentative);

}
