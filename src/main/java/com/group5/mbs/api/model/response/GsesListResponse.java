package com.group5.mbs.api.model.response;

import com.group5.mbs.api.dtos.GraduateSchoolOfScienceAndEngineering;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GsesListResponse extends BaseResponse {

    private List<GraduateSchoolOfScienceAndEngineering> graduateSchoolOfScienceAndEngineeringList;

}
