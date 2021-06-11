package com.group5.mbs.api.model.response;

import com.group5.mbs.api.dtos.JuryMember;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JuryMemberResponse extends BaseResponse {

    private JuryMember juryMember;

}
