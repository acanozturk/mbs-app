package com.group5.mbs.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@Table(name = "student_entities")
@Proxy(lazy = false)
public class StudentEntity extends UserEntity {

    @ManyToOne
    @JoinColumn(name = "advisor_id")
    @JsonIgnoreProperties({"studentEntities"})
    private AdvisorEntity advisorEntity;

    @Column(name = "cgpa")
    private double cpga;

    @OneToOne
    @JoinColumn(name = "program_plan_form_id")
    private ProgramPlanFormEntity programPlanFormEntity;

    @Column(name = "thesis_topic")
    private String thesisTopic;

    @Column(name = "thesis_topic_status")
    @Enumerated(EnumType.STRING)
    private ThesisTopicStatus thesisTopicStatus;

    @OneToOne
    @JoinColumn(name = "thesis_id")
    private ThesisEntity thesisEntity;

    @Column(name = "tss_jury_status")
    @Enumerated(EnumType.STRING)
    private TssJuryStatus tssJuryStatus;

    @Column(name = "graduation_status")
    @Enumerated(EnumType.STRING)
    private GraduationStatus graduationStatus;

}
