package com.group5.mbs.api.model.response;

import com.group5.mbs.api.dtos.DepartmentalBoardRepresentative;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DbrResponse extends BaseResponse {

    private DepartmentalBoardRepresentative departmentalBoardRepresentative;

}
