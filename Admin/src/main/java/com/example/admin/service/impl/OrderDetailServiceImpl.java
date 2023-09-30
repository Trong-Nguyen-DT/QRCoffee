package com.example.admin.service.impl;

import com.example.admin.convertor.OrderDetailConvertor;
import com.example.admin.convertor.ProductConvertor;
import com.example.admin.domain.OrderDetail;
import com.example.admin.domain.Product;
import com.example.admin.entity.*;
import com.example.admin.repository.*;
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
    private OrderHistoryRepository orderHistoryRepository;

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

    @Override
    public List<Product> getTop4Product() {
        List<Object[]> products = orderDetailHistoryRepository.findTop4SellingProductsLimited();
        List<Product> productList = productRepository.findAll().stream().map(ProductConvertor::toModel).toList();
        List<Product> topProducts = new ArrayList<>();
        for (Object[] productData : products) {
            Long productId = (Long) productData[0];

            for (Product prod:productList) {
                if (prod.getId() == productId) {
                    Product product = new Product();
                    product.setId(productId);
                    product.setTitle(prod.getTitle());
                    product.setPrice(prod.getPrice());
                    topProducts.add(product);
                }
            }

        }
        return topProducts;
    }

    @Override
    public void saveOrderDetail(OrderEntity orderEntity, List<OrderDetail> orderDetails) {
        for (OrderDetail orderDetail : orderDetails) {
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setOrderEntity(orderEntity);
            ProductEntity productEntity = productRepository.findById(orderDetail.getProduct_id())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm với id: " + orderDetail.getProduct_id()));
            orderDetailEntity.setProductEntity(productEntity);
            orderDetailEntity.setQuantity(orderDetail.getQuantity());
            orderDetailRepository.save(orderDetailEntity);
        }
    }

    @Override
    public void saveOrderDetailHistory(OrderEntity orderEntity, OrderHistoryEntity orderHistoryEntity) {
        for (OrderDetailEntity orderDetailEntity : orderEntity.getOrderDetails()) {
            OrderDetailHistoryEntity orderDetailHistoryEntity = new OrderDetailHistoryEntity();
            orderDetailHistoryEntity.setOrderHistoryEntity(orderHistoryEntity);

            orderDetailHistoryEntity.setId(orderDetailEntity.getId());

            ProductEntity productEntity = productRepository.findById(orderDetailEntity.getProductEntity().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy sản phẩm với id: " + orderDetailEntity.getProductEntity().getId()));
            orderDetailHistoryEntity.setProductId(productEntity.getId());
            orderDetailHistoryEntity.setProductTitle(productEntity.getTitle());
            orderDetailHistoryEntity.setProductPrice(productEntity.getPrice());
            orderDetailHistoryEntity.setQuantity(orderDetailEntity.getQuantity());
            orderDetailHistoryRepository.save(orderDetailHistoryEntity);
        }
    }

    @Override
    public List<OrderDetailHistoryEntity> getAllDetailByOrderId(Long orderID) {

        return orderDetailHistoryRepository.findAllByOrderHistoryEntity
                (orderHistoryRepository.findById(orderID).orElseThrow());
    }
}
