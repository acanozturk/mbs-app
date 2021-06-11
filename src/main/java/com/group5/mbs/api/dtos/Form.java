package com.group5.mbs.api.dtos;

import com.group5.mbs.entities.FormType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.Lob;

@EqualsAndHashCode(callSuper = true)
@Data
public class Form extends BaseDTO {

    private Student student;
    private String formName;
    private String contentType;
    private FormType formType;

    //@Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] form;

}
