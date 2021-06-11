package com.group5.mbs.api.dtos;

import com.group5.mbs.entities.ThesisTssStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TssJury extends BaseDTO {

    private JuryMember juryMember;
    private Student student;
    private String date;
    private ThesisTssStatus thesisTssStatus;

}
