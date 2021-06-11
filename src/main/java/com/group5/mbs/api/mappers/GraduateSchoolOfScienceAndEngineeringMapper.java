package com.group5.mbs.api.mappers;

import com.group5.mbs.api.dtos.GraduateSchoolOfScienceAndEngineering;
import com.group5.mbs.entities.GraduateSchoolOfScienceAndEngineeringEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GraduateSchoolOfScienceAndEngineeringMapper {

    GraduateSchoolOfScienceAndEngineeringMapper INSTANCE =
            Mappers.getMapper(GraduateSchoolOfScienceAndEngineeringMapper.class);

    GraduateSchoolOfScienceAndEngineering gsesEntityToGsesDTO(GraduateSchoolOfScienceAndEngineeringEntity
                                                                      graduateSchoolOfScienceAndEngineeringEntity);

    GraduateSchoolOfScienceAndEngineeringEntity gsesDTOToGsesEntity(GraduateSchoolOfScienceAndEngineering
                                                                            graduateSchoolOfScienceAndEngineeringDTO);

}
