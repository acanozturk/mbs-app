package com.group5.mbs.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@Table(name = "ibdr_entities")
public class InstituteBoardOfDirectorsRepresentativeEntity extends UserEntity {

}
