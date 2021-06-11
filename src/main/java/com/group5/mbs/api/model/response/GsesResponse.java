package com.group5.mbs.api.model.response;

import com.group5.mbs.api.dtos.GraduateSchoolOfScienceAndEngineering;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GsesResponse extends BaseResponse {

    private GraduateSchoolOfScienceAndEngineering graduateSchoolOfScienceAndEngineering;

}
