package com.group5.mbs.api.mappers;

import com.group5.mbs.api.dtos.Proposal;
import com.group5.mbs.entities.ProposalEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProposalMapper {

    ProposalMapper INSTANCE = Mappers.getMapper(ProposalMapper.class);

    Proposal proposalEntityToProposalDTO(ProposalEntity proposalEntity);

    ProposalEntity proposalDTOToProposalEntity(Proposal proposal);

}
