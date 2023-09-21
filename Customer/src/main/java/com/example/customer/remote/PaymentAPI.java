package com.example.customer.remote;


import com.example.customer.entity.ProductEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class PaymentAPI {

    public List<ProductEntity> getProductFromOtherClient() {
        //Url
        String url =  UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api-merchant.payos.vn/")
                .port(8080)
                .path("v2/payment-requests")
                .build()
                .toUriString();
        //Call API

        RestOperations restOperations = new RestTemplate();
        return (List<ProductEntity>)restOperations.getForObject(url, List.class);

//        clientId
//        9f9afe18-099b-4cd0-81c1-fc229326dccf
//        Api Key
//        f13eb249-fa4d-4a01-95f6-3656f8d81406
//        Checksum Key
//        22ee21ab306b80fac1782bb426e6140498bc4b5b9f483f30d4883f320731e29e
    }
}
