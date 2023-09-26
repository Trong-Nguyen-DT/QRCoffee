package com.example.admin.service.impl;

import com.example.admin.convertor.OrderDetailConvertor;
import com.example.admin.convertor.ProductConvertor;
import com.example.admin.domain.OrderDetail;
import com.example.admin.domain.Product;
import com.example.admin.entity.OrderDetailEntity;
import com.example.admin.entity.ProductEntity;
import com.example.admin.repository.OrderDetailHistoryRepository;
import com.example.admin.repository.OrderDetailRepository;
import com.example.admin.repository.OrderRepository;
import com.example.admin.repository.ProductRepository;
import com.example.admin.service.OrderDetailService;
import com.example.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailHistoryRepository orderDetailHistoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<OrderDetail> getItemsById(Long id) {
        return orderDetailRepository.findOrderDetailEntitiesByOrderEntity(orderRepository.findById(id)).stream().map(OrderDetailConvertor::toModel).toList();
    }

    @Override
    public List<ProductEntity> getAllProduct() {

        return orderDetailRepository.findAll().stream().map(OrderDetailEntity::getProductEntity).toList();
    }

//    @Override
//    public List<Product> getTop4Product() {
//        List<Object[]> products = orderDetailHistoryRepository.findTop4SellingProductsLimited();
//        List<Product> productList = productRepository.findAll().stream().map(ProductConvertor::toModel).toList();
//        List<Product> topProducts = new ArrayList<>();
//        for (Object[] productData : products) {
//            Long productId = (Long) productData[0];
//
//            for (Product prod:productList) {
//                if (prod.getId() == productId) {
//                    Product product = new Product();
//                    product.setId(productId);
//                    product.setTitle(prod.getTitle());
//                    product.setPrice(prod.getPrice());
//                    topProducts.add(product);
//                }
//            }
//
//        }
//        return topProducts;
//    }
}
