package com.group5.mbs.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@Table(name = "proposal_entities")
public class ProposalEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnoreProperties({"advisorEntities"})
    private StudentEntity studentEntity;

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    @JsonIgnoreProperties({"studentEntities"})
    private AdvisorEntity advisorEntity;

}
