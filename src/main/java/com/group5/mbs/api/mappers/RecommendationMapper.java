package com.group5.mbs.api.mappers;

import com.group5.mbs.api.dtos.Recommendation;
import com.group5.mbs.entities.RecommendationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecommendationMapper {

    RecommendationMapper INSTANCE = Mappers.getMapper(RecommendationMapper .class);

    Recommendation recommendationEntityToRecommendationDTO(RecommendationEntity recommendationEntity);

    RecommendationEntity recommendationDTOToRecommendationEntity(Recommendation recommendation);

}