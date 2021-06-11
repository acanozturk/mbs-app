package com.group5.mbs.api.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class ThesisResponse extends BaseResponse {

    private String fileName;

    private String thesisTopic;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;

    private String url;

}
