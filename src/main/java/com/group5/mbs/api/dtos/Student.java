package com.group5.mbs.api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.group5.mbs.entities.GraduationStatus;
import com.group5.mbs.entities.ThesisTopicStatus;
import com.group5.mbs.entities.TssJuryStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Student extends User {

    @JsonInclude
    private double cpga;

    @JsonInclude
    private Advisor advisor;

    @JsonInclude
    @JsonIgnoreProperties({"form"})
    private ProgramPlanForm programPlanForm;

    @JsonInclude
    private String thesisTopic;

    @JsonInclude
    private ThesisTopicStatus thesisTopicStatus;

    @JsonInclude
    @JsonIgnoreProperties({"thesis"})
    private Thesis thesis;

    @JsonInclude
    private TssJuryStatus tssJuryStatus;

    @JsonInclude
    private GraduationStatus graduationStatus;
}
