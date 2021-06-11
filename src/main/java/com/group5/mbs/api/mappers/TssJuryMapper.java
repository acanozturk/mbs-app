package com.group5.mbs.api.mappers;

import com.group5.mbs.api.dtos.TssJury;
import com.group5.mbs.entities.TssJuryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TssJuryMapper {

    TssJuryMapper INSTANCE = Mappers.getMapper(TssJuryMapper.class);

    TssJury tssJuryEntityToTssJury(TssJuryEntity tssJuryEntity);

    TssJuryEntity tssJuryToTssJuryEntity(TssJury tssJury);

}
