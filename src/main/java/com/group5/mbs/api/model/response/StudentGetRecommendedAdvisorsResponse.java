package com.group5.mbs.api.model.response;

import com.group5.mbs.api.dtos.Recommendation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentGetRecommendedAdvisorsResponse extends BaseResponse {

    private List<Recommendation> recommendations;

}
