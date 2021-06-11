package com.group5.mbs.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@Table(name = "submit_thesis_entities")
public class SubmitThesisEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "student_id")
    private StudentEntity studentEntity;

}
