package com.group5.mbs.api.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group5.mbs.entities.ProgramPlanFormStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.Lob;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties({"contentType"})
public class ProgramPlanForm extends BaseDTO {

    private String fileName;
    private String contentType;
    private ProgramPlanFormStatus programPlanFormStatus;

    //@Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] form;

}
