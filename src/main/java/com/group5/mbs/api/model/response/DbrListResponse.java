package com.group5.mbs.api.model.response;

import com.group5.mbs.api.dtos.DepartmentalBoardRepresentative;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class DbrListResponse extends BaseResponse {

    private List<DepartmentalBoardRepresentative> departmentalBoardRepresentativeList;

}
