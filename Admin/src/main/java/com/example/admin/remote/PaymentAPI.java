package com.example.admin.remote;


import com.example.admin.domain.Order;
import com.example.admin.domain.ResponseBody;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class PaymentAPI {

    public String getQrFromOtherClient(Order requestBody) {
        //Url
        String url =  UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api-merchant.payos.vn")
                .path("v2/payment-requests")
                .build()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-client-id", "9f9afe18-099b-4cd0-81c1-fc229326dccf");
        headers.set("x-api-key", "f13eb249-fa4d-4a01-95f6-3656f8d81406");


        HttpEntity<Order> requestEntity = new HttpEntity<>(requestBody, headers);

        RestOperations restOperations = new RestTemplate();

        ResponseBody x = restOperations.postForEntity(url, requestEntity, ResponseBody.class).getBody();
        assert x != null;
        return x.getData().getCheckoutUrl();
    }
}

//        clientId
//        9f9afe18-099b-4cd0-81c1-fc229326dccf
//        Api Key
//        f13eb249-fa4d-4a01-95f6-3656f8d81406
//        Checksum Key
//        22ee21ab306b80fac1782bb426e6140498bc4b5b9f483f30d4883f320731e29e


//    public static void main(String[] args) {
//
//        RequestBody requestBody = new RequestBody();
//        requestBody.setOrderCode(111222);
//        requestBody.setAmount(20000L);
//        requestBody.setDescription("suongmuahang");
//        requestBody.setBuyerName("Suong");
//        requestBody.setBuyerAddress("Quang Nam");
//        requestBody.setBuyerPhone("0707125903");
//        requestBody.setBuyerEmail("suong@gmail.com");
//        requestBody.setCancelUrl("cancelURL");
//        requestBody.setSignature("6a6b6f9f8e7aad3bd8418308c6c6a9be05a46ef071ffe315e7e2dd1e56adcc0e");
//        requestBody.setReturnUrl("returnUrl");
//
//        RequestItem item = new RequestItem();
//        item.setName("coffee den");
//        item.setPrice(20000L);
//        item.setQuantity(1);
//
//        requestBody.setItems(List.of(item));
//
//
//        PaymentAPI api = new PaymentAPI();
//        api.getProductFromOtherClient(requestBody);
//    }

