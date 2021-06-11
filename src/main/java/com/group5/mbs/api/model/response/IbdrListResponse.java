package com.group5.mbs.api.model.response;

import com.group5.mbs.api.dtos.InstituteBoardOfDirectorsRepresentative;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class IbdrListResponse extends BaseResponse {

    private List<InstituteBoardOfDirectorsRepresentative> instituteBoardOfDirectorsRepresentativeList;

}
