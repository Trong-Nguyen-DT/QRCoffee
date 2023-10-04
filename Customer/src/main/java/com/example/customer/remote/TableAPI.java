package com.example.customer.remote;

import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class TableAPI {

    public void callStaffHome() {
        //Url
        String url =  UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("localhost")
                .port(8081)
                .path("table-api")
                .build()
                .toUriString();
        //Call API
    }
}
