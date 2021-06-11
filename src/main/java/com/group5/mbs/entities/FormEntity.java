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
@Table(name = "form_entities")
public class FormEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "studentId")
    private StudentEntity studentEntity;

    @Column(name = "form_name")
    private String formName;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "form_type")
    @Enumerated(EnumType.STRING)
    private FormType formType;

    @Column(name = "form")
    //@Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] form;

}
