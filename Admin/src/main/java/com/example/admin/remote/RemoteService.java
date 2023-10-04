package com.example.admin.remote;

import com.example.admin.domain.Product;
import com.example.admin.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class RemoteService {

    public List<Product> getProductFromOtherClient() {
        //Url
        String url =  UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("192.168.101.67")
                .port(8080)
                .path("test")
                .build()
                .toUriString();
        //Call API

        RestOperations restOperations = new RestTemplate();
        return (List<Product>)restOperations.getForObject(url, List.class);
    }
}
