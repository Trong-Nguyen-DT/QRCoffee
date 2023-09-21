package com.example.customer.remote;


import com.example.customer.domain.User;
import com.example.customer.entity.ProductEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class RemoteService {

//    public List<User> getProductFromOtherClient() {
//        //Url
//        String url =  UriComponentsBuilder.newInstance()
//                .scheme("http")
//                .host("api-merchant.payos.vn")
//                .port(8080)
//                .path("v2/payment-requests")
//                .build()
//                .toUriString();
//        //Call API
//
//        RestOperations restOperations = new RestTemplate();
//        return (List<User>)restOperations.getForObject(url, List.class);
//    }
}
