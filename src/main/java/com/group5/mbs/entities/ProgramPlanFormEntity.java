package com.group5.mbs.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
@Table(name = "program_plan_form_entities")
public class ProgramPlanFormEntity extends BaseEntity {

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "program_plan_form_status")
    @Enumerated(EnumType.STRING)
    private ProgramPlanFormStatus programPlanFormStatus;

    @Column(name = "form")
    //@Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] form;

}
