package com.group5.mbs.api.model.response;

import com.group5.mbs.api.dtos.JuryMember;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class JuryMemberListResponse extends BaseResponse {

    private List<JuryMember> juryMemberList;

}
