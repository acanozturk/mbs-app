package com.group5.mbs.api.model.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FormResponse extends BaseResponse {

    private String fileName;
    private String url;

}
