package com.group5.mbs.api.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Type;

import javax.persistence.Lob;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@JsonIgnoreProperties({"contentType"})
public class Thesis extends BaseDTO {

    private String fileName;
    private String contentType;
    private Integer plagiarismRatio;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;

    //@Lob
    @Type(type = "org.hibernate.type.BinaryType")
    private byte[] thesis;

}
