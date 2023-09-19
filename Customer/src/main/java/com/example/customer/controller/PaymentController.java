package com.example.customer.controller;



import com.example.customer.domain.Product;
import com.example.customer.domain.User;
import com.example.customer.entity.ProductEntity;
import com.example.customer.remote.RemoteService;
import com.example.customer.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    private ProductService productService;

//    @GetMapping("/payment")
//    public String getPayData() {
//        String clientId = "9f9afe18-099b-4cd0-81c1-fc229326dccf";
//        String apiKey = "f13eb249-fa4d-4a01-95f6-3656f8d81406";
//        String checksumKey = "YOUR_CHECKSUM_KEY";
//        String apiUrl = "https://api-merchant.payos.vn/v2/payment-requests";
//
//        HttpClient client = HttpClient.newHttpClient();
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(apiUrl))
//                .header("Client-Id", clientId)
//                .header("Api-Key", apiKey)
//                .header(HttpHeaders.CONTENT_TYPE, "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
//                .build();
//
//        try {
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            return "Response: " + response.body();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Error: " + e.getMessage();
//        }
//    }

}
