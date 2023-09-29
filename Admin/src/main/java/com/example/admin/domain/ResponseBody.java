package com.example.admin.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseBody {
    private String code;
    private String desc;
    private ResponseData data;
    private String signature;
}
