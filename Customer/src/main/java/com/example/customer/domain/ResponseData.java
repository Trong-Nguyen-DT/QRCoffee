package com.example.customer.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData {
    private String bin;
    private String accountNumber;
    private String accountName;
    private Long amount;
    private String description;
    private Integer orderCode;
    private String paymentLinkId;
    private String status;
    private String checkoutUrl;
    private String qrCode;
}
