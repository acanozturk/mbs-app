package com.group5.mbs.api.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.group5.mbs.api.dtos.TssJury;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentTssJuryResponse extends BaseResponse {

    @JsonIgnoreProperties({"student"})
    private List<TssJury> tssJuryList;
}
