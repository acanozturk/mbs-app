package com.group5.mbs.api.model.response;

import com.group5.mbs.api.dtos.InstituteBoardOfDirectorsRepresentative;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class IbdrResponse extends BaseResponse {

    private InstituteBoardOfDirectorsRepresentative instituteBoardOfDirectorsRepresentative;

}
