package com.example.customer.service.Impl;

import com.example.customer.domain.OrderDetail;
import com.example.customer.entity.*;
import com.example.customer.repository.OrderDetailRepository;
import com.example.customer.repository.ProductRepository;
import com.example.customer.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

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
            orderDetailRepository.save(orderDetailEntity);
        }
    }
}
