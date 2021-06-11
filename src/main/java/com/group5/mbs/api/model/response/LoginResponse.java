package com.group5.mbs.api.model.response;

import com.group5.mbs.api.dtos.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponse extends BaseResponse {

    private User user;

}
